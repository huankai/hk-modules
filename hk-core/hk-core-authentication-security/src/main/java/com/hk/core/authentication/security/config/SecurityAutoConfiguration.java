package com.hk.core.authentication.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.hk.core.authentication.security.DefaultUserDetailServiceImpl;
import com.hk.core.authentication.security.SpringSecurityContext;

/**
 * 
 * @author huangkai
 * @date 2017年12月18日下午5:26:46
 */
@Configuration
public class SecurityAutoConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * 默认的认证
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(value = { UserDetailsService.class })
	public UserDetailsService userDetails() {
		return new DefaultUserDetailServiceImpl();
	}

	@Bean("hkspringSecurityContext")
	public SpringSecurityContext springSecurityContext() {
		return new SpringSecurityContext();
	}

	/**
	 * 
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetails());
	}

	/**
	 * 
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated() // 任意请求都需要认证
				.and().formLogin().loginPage("/login").defaultSuccessUrl("/index").failureForwardUrl("/login?error")
				.permitAll()// 设置默认登录页、成功跳转页、登陆失败跳转页
				.and().rememberMe().tokenValiditySeconds(30 * 60 * 60).key("")// 开启cookie保存用户数据、设置cookie有效期、设置cookie的私钥
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll(); // 默认注销行为为logout，可以通过下面的方式来修改、设置注销成功后跳转页面，默认是跳转到登录页面
	}
}
