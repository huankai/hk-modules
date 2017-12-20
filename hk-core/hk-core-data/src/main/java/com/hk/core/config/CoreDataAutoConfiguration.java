package com.hk.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.hk.commons.util.StringUtils;
import com.hk.core.audit.UserIdAuditor;
import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.query.jdbc.JdbcSession;
import com.hk.core.query.jdbc.dialect.Dialect;
import com.hk.core.query.jdbc.dialect.MySQLDialect;

/**
 * Core Dao 自动配置
 * 
 * @author huangkai
 *
 */
@Configuration
public class CoreDataAutoConfiguration {

	/**
	 * 配置使用的数据库，默认为 Mysql
	 */
	@Value("${hk.jdbc.dialect:MYSQL}")
	private String jdbcDialect;

	@Autowired
	private SecurityContext securityContext;

	@Bean
	public JdbcSession jdbcSession(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		JdbcSession jdbcSession = new JdbcSession();
		jdbcSession.setJdbcTemplate(jdbcTemplate);
		jdbcSession.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
		jdbcSession.setDialect(dialect());
		return jdbcSession;
	}

	private Dialect dialect() {
		if (StringUtils.equalsIgnoreCase(jdbcDialect, "MYSQL")) {
			return new MySQLDialect();
		}
		throw new RuntimeException("不能识别数据库" + jdbcDialect);
	}

	/* ************audit************ */

	/**
	 * jpa audit功能
	 * 
	 * @return
	 */
	@Bean
	public UserIdAuditor userIdAuditor() {
		UserIdAuditor userIdAuditor = new UserIdAuditor();
		userIdAuditor.setSecurityContext(securityContext);
		return userIdAuditor;
	}
}
