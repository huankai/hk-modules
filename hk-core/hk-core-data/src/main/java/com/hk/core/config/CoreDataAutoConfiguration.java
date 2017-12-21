package com.hk.core.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

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

	@Bean
	public JdbcSession jdbcSession(Dialect dialect, JdbcTemplate jdbcTemplate,
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		JdbcSession jdbcSession = new JdbcSession();
		jdbcSession.setJdbcTemplate(jdbcTemplate);
		jdbcSession.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
		jdbcSession.setDialect(dialect);
		return jdbcSession;
	}

	@Bean
	@ConditionalOnMissingBean
	public Dialect Dialect() {
		return new MySQLDialect();
	}

	/* ************ audit ************ */

	/**
	 * jpa audit功能
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnClass(value = { SecurityContext.class })
	@ConditionalOnMissingBean(value = AuditorAware.class)
	public AuditorAware<?> userIdAuditor(SecurityContext securityContext) {
		UserIdAuditor userIdAuditor = new UserIdAuditor();
		userIdAuditor.setSecurityContext(securityContext);
		return userIdAuditor;
	}
}
