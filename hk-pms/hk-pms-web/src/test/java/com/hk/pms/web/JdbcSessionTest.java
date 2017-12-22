package com.hk.pms.web;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.hk.commons.fastjson.JsonUtils;
import com.hk.core.query.AndOr;
import com.hk.core.query.Operator;
import com.hk.core.query.Order;
import com.hk.core.query.jdbc.CompositeCondition;
import com.hk.core.query.jdbc.JdbcSession;
import com.hk.core.query.jdbc.ListResult;
import com.hk.core.query.jdbc.LogicalCondition;
import com.hk.core.query.jdbc.SelectArguments;
import com.hk.core.query.jdbc.SimpleCondition;
import com.hk.pms.PmsApplication;
import com.hk.pms.core.domain.User;

/**
 * JDBC查询测试
 * 
 * @author huangkai
 * @date 2017年12月22日下午1:28:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { PmsApplication.class })
public class JdbcSessionTest {

	@Autowired
	private JdbcSession jdbcSession;

	/**
	 * SELECT DISTINCT org_id,id FROM sys_user WHERE (user_name = ?)
	 */
	@Test
	public void simpleTest() {
		SelectArguments arguments = new SelectArguments();
		arguments.setDistinct(true);
		arguments.setFields("id", "org_id");
		arguments.setFrom("sys_user");
		arguments.getConditions().addConditions(new SimpleCondition("user_name", "admin"));
		ListResult<Map<String, Object>> queryResult = jdbcSession.queryForList(arguments, false);
		System.out.println(JsonUtils.toJSONString(queryResult));
	}

	/**
	 * <pre>
	 * 分页查询
	 * SELECT org_id,id FROM sys_user WHERE (user_name = ?) LIMIT 0,10
	 * SELECT count(*) FROM sys_user WHERE (user_name = ?)
	 * </pre>
	 */
	@Test
	public void pageTest() {
		SelectArguments arguments = new SelectArguments();
		arguments.setPageSize(10);
		arguments.setFields("id", "org_id");
		arguments.setFrom("sys_user");
		arguments.getConditions().addConditions(new SimpleCondition("user_name", "admin"));
		ListResult<Map<String, Object>> queryResult = jdbcSession.queryForList(arguments, true);
		System.out.println(JsonUtils.toJSONString(queryResult));
	}

	/**
	 * <pre>
	 * 多条件分页查询
	 * SELECT org_id,id FROM sys_user WHERE (user_name = ?) AND (email LIKE ?) LIMIT 0,10
	 * SELECT count(*) FROM sys_user WHERE (user_name = ?) AND (email LIKE ?)
	 * </pre>
	 */
	@Test
	public void manyConditonTest() {
		SelectArguments arguments = new SelectArguments();
		arguments.setPageSize(10);
		arguments.setFields("id", "org_id");
		arguments.setFrom("sys_user");
		arguments.getConditions().addConditions(new SimpleCondition("user_name", "admin"),
				new SimpleCondition("email", Operator.LIKEANYWHERE, "@"));
		ListResult<Map<String, Object>> queryResult = jdbcSession.queryForList(arguments, true);
		System.out.println(JsonUtils.toJSONString(queryResult));
	}

	/**
	 * <pre>
	 * 多条件AND/OR分页查询
	 * SELECT org_id,id FROM sys_user WHERE ((email LIKE ?) OR (phone = ?)) AND (user_name = ?) LIMIT 0,10
	 * SELECT count(*) FROM sys_user WHERE ((email LIKE ?) OR (phone = ?)) AND (user_name = ?)
	 * </pre>
	 */
	@Test
	public void manyCompositeConditonTest() {
		SelectArguments arguments = new SelectArguments();
		arguments.setPageSize(10);
		arguments.setFields("id", "org_id");
		arguments.setFrom("sys_user");
		CompositeCondition conditions = arguments.getConditions();
		conditions.setAndOr(AndOr.AND);
		LogicalCondition logicalCondition = new LogicalCondition(AndOr.OR, Lists.newArrayList(
				new SimpleCondition("email", Operator.LIKE, "@"), new SimpleCondition("phone", "18820136090")));
		conditions.addConditions(logicalCondition, new SimpleCondition("user_name", "admin"));
		ListResult<Map<String, Object>> queryResult = jdbcSession.queryForList(arguments, true);
		System.out.println(JsonUtils.toJSONString(queryResult));
	}

	/**
	 * <pre>
	 * 查询返回对象
	 * </pre>
	 */
	@Test
	public void queryObjectTest() {
		SelectArguments arguments = new SelectArguments();
		arguments.setPageSize(10);
		arguments.setFields("id", "org_id");
		arguments.setFrom("sys_user");
		CompositeCondition conditions = arguments.getConditions();
		conditions.setAndOr(AndOr.AND);
		LogicalCondition logicalCondition = new LogicalCondition(AndOr.OR, Lists.newArrayList(
				new SimpleCondition("email", Operator.LIKE, "@"), new SimpleCondition("phone", "18820136090")));
		conditions.addConditions(logicalCondition, new SimpleCondition("user_name", "admin"));
		arguments.setOrders(Lists.newArrayList(Order.asc("created_date")));
		ListResult<User> queryResult = jdbcSession.queryForList(arguments, true, User.class);
		System.out.println(JsonUtils.toJSONString(queryResult));
	}

}
