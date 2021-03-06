package com.hk.core.query.jdbc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hk.commons.util.StringUtils;
import com.hk.core.query.MatchMode;
import com.hk.core.query.Operator;

/**
 * @author huangkai
 * @date 2017年12月20日下午2:43:59
 */
public class SimpleCondition implements Condition {

	private String field;

	private Operator operator;

	private Object compareValue;

	private static Map<Operator, ComparableCondition> conditions;

	static {
		conditions = new HashMap<>();
		ComparableCondition c = new BinaryCondition();
		conditions.put(Operator.EQ, c);
		conditions.put(Operator.LT, c);
		conditions.put(Operator.LTE, c);
		conditions.put(Operator.GT, c);
		conditions.put(Operator.GTE, c);

		c = new InCondition();
		conditions.put(Operator.IN, c);
		conditions.put(Operator.NOTIN, c);

		conditions.put(Operator.NE, new NotEqCondition());
		conditions.put(Operator.ISNULL, new IsNullCondition());
		conditions.put(Operator.ISNOTNULL, new IsNotNullCondition());
		conditions.put(Operator.LIKE, new LikeExactCondition());
		conditions.put(Operator.LIKESTART, new LikeStartCondition());
		conditions.put(Operator.LIKEEND, new LikeEndCondition());
		conditions.put(Operator.LIKEANYWHERE, new LikeAnywhereCondition());

		conditions.put(Operator.BETWEEN, new BetweenCondition());
	}

	/**
	 * @param field
	 * @param compareValue
	 */
	public SimpleCondition(String field, Object compareValue) {
		this(field, Operator.EQ, compareValue);
	}

	/**
	 * @param field
	 * @param operator
	 * @param compareValue
	 */
	public SimpleCondition(String field, Operator operator, Object compareValue) {
		this.field = field;
		this.operator = operator;
		this.compareValue = compareValue;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @return the operator
	 */
	public Operator getOperator() {
		return operator;
	}

	/**
	 * @return the compareValue
	 */
	public Object getCompareValue() {
		return compareValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hk.core.query.jdbc.Condition#toSqlString(java.util.List)
	 */
	@Override
	public String toSqlString(List<Object> parameters) {
		ComparableCondition c = getSubCondition(operator);
		if (c != null) {
			return c.toSqlString(field, operator, compareValue, parameters);
		}
		return null;
	}

	private ComparableCondition getSubCondition(Operator operator) {
		if (null == operator) {
			operator = Operator.EQ;
		}
		return conditions.get(operator);
	}

	static abstract class ComparableCondition {

		abstract String toSqlString(String field, Operator operator, Object compareValue, List<Object> parameters);
	}

	static class BetweenCondition extends ComparableCondition {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.hk.core.query.jdbc.SimpleCondition.ComparableCondition#toSqlString(java.
		 * lang.String, com.hk.core.query.Operator, java.lang.Object, java.util.List)
		 */
		@Override
		String toSqlString(String field, Operator operator, Object compareValue, List<Object> parameters) {
			if (null == compareValue || !(compareValue instanceof Object[])) {
				return null;
			}
			Object[] value = (Object[]) compareValue;
			if (value.length != 2) {
				return null;
			}
			parameters.add(value[0]);
			parameters.add(value[1]);
			return String.format("(%s BETWEEN ? AND ?)", field);
		}

	}

	static class InCondition extends ComparableCondition {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.hk.core.query.jdbc.SimpleCondition.ComparableCondition#toSqlString(java.
		 * lang.String, com.hk.core.query.Operator, java.lang.Object, java.util.List)
		 */
		@Override
		String toSqlString(String field, Operator operator, Object compareValue, List<Object> parameters) {
			Iterable<?> iterable = null;
			if (compareValue instanceof Object[]) {
				Object[] array = (Object[]) compareValue;
				iterable = Arrays.asList(array);
			} else if (compareValue instanceof Iterable<?>) {
				iterable = (Iterable<?>) compareValue;
			}
			if (iterable == null) {
				return null;
			}
			StringBuilder sb = new StringBuilder();
			int index = 0;
			for (Object value : iterable) {
				if (index++ > 0) {
					sb.append(",");
				}
				sb.append("?");
				parameters.add(value);
			}
			String inWhich = sb.toString();
			if (inWhich.length() == 0) {
				// 空集合
				if (operator == Operator.IN) {
					// xx in () 会报错；in一个空集合的结果应该是空，所以加一个1=2的条件
					return "(1 = 2)";
				} else {
					// not in 空集合，返回null，表示该条件不起作用。
					return null;
				}
			}
			return String.format("(%s %s (%s))", field, operator == Operator.IN ? "IN" : "NOT IN", inWhich);
		}

	}

	static abstract class BaseLikeCondition extends ComparableCondition {

		protected abstract MatchMode getLikeMatchMode();

		@Override
		protected String toSqlString(String field, Operator operator, Object compareValue, List<Object> parameters) {
			if (compareValue == null) {
				return null;
			}
			String value = String.valueOf(compareValue);
			if (StringUtils.isEmpty(value)) {
				return null;
			}
			parameters.add(getLikeMatchMode().toMatchString(value));
			return String.format("(%s LIKE ?)", field);
		}
	}

	static class LikeStartCondition extends BaseLikeCondition {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.hk.core.query.jdbc.SimpleCondition.BaseLikeCondition#getLikeMatchMode()
		 */
		@Override
		protected MatchMode getLikeMatchMode() {
			return MatchMode.START;
		}

	}

	static class LikeEndCondition extends BaseLikeCondition {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.hk.core.query.jdbc.SimpleCondition.BaseLikeCondition#getLikeMatchMode()
		 */
		@Override
		protected MatchMode getLikeMatchMode() {
			return MatchMode.END;
		}

	}

	static class LikeAnywhereCondition extends BaseLikeCondition {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.hk.core.query.jdbc.SimpleCondition.BaseLikeCondition#getLikeMatchMode()
		 */
		@Override
		protected MatchMode getLikeMatchMode() {
			return MatchMode.ANYWHERE;
		}

	}

	static class LikeExactCondition extends BaseLikeCondition {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.hk.core.query.jdbc.SimpleCondition.BaseLikeCondition#getLikeMatchMode()
		 */
		@Override
		protected MatchMode getLikeMatchMode() {
			return MatchMode.EXACT;
		}

	}

	static final class BinaryCondition extends ComparableCondition {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.hk.core.query.jdbc.SimpleCondition.ComparableCondition#toSqlString(java.
		 * lang.String, com.hk.core.query.Operator, java.lang.Object, java.util.List)
		 */
		@Override
		String toSqlString(String field, Operator operator, Object compareValue, List<Object> parameters) {
			String sqlCompare = null;
			switch (operator) {
			case EQ:
				sqlCompare = "=";
				break;
			case LT:
				sqlCompare = "<";
				break;
			case LTE:
				sqlCompare = "<=";
				break;
			case GT:
				sqlCompare = ">";
				break;
			case GTE:
				sqlCompare = ">=";
				break;
			default:
				break;
			}
			if (sqlCompare == null) {
				return null;
			}
			parameters.add(compareValue);
			return String.format("(%s %s ?)", field, sqlCompare);
		}

	}

	static class NotEqCondition extends ComparableCondition {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.hk.core.query.jdbc.SimpleCondition.ComparableCondition#toSqlString(java.
		 * lang.String, com.hk.core.query.Operator, java.lang.Object, java.util.List)
		 */
		@Override
		String toSqlString(String field, Operator operator, Object compareValue, List<Object> parameters) {
			parameters.add(compareValue);
			return String.format("(%1$s <> ? OR (%1$s IS NULL))", field);
		}

	}

	static class IsNullCondition extends ComparableCondition {

		@Override
		String toSqlString(String field, Operator operator, Object compareValue, List<Object> parameters) {
			return String.format("(%1$s IS NULL OR %1$s = '')", field);
		}
	}

	static class IsNotNullCondition extends ComparableCondition {

		@Override
		String toSqlString(String field, Operator operator, Object compareValue, List<Object> parameters) {
			return String.format("(%s IS NOT NULL)", field);
		}
	}

}
