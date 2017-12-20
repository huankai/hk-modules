package com.hk.core.query;

import java.io.Serializable;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.data.domain.Sort.Direction;

import com.hk.core.query.jpa.PathUtils;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1396042259445080717L;

	private String field;

	private boolean desc;

	public Order() {
	}

	public Order(String field, boolean desc) {
		this.field = field;
		this.desc = desc;
	}

	public static Order asc(String field) {
		return new Order(field, false);
	}

	public static Order desc(String field) {
		return new Order(field, true);
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public boolean isDesc() {
		return desc;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return toSqlString();
	}

	public String toSqlString() {
		return String.format(" %s %s", getField(), desc ? Direction.DESC.name() : Direction.ASC.name());
	}

	@SuppressWarnings("rawtypes")
	public OrderImpl toJpaOrder(Root<?> root) {
		Path expression = PathUtils.getPath(root, field);
		return new OrderImpl(expression, !desc);
	}
}
