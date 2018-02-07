package com.hk.core.authentication.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * @author huangkai
 * @date 2017年12月18日下午5:26:46
 */
@Configuration
@EnableWebSecurity
public class SecurityWebAutoConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * 
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	/**
	 * 
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/resources/**","/wechat/**").permitAll() //过滤静态资源，与微信相关接口
        .anyRequest().authenticated()//任意请求都需要认证
        .and().formLogin().loginPage("/login").failureUrl("/login?error").permitAll() //登陆请求
        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").invalidateHttpSession(true).permitAll();//退出请求

		// http.authorizeRequests().antMatchers("/","/login").permitAll() // / /login
		// 不需要认证
		// .anyRequest().authenticated() // 任意请求都需要认证
		// .and().formLogin().loginPage("/login").defaultSuccessUrl("/index").failureForwardUrl("/login?error")
		// .permitAll()// 设置默认登录页、成功跳转页、登陆失败跳转页
		// // .and().rememberMe().tokenValiditySeconds(30 * 60 * 60).key("")//
		// // 开启cookie保存用户数据、设置cookie有效期、设置cookie的私钥
		// .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll(); //
		// 默认注销行为为logout，可以通过下面的方式来修改、设置注销成功后跳转页面，默认是跳转到登录页面
	}
}
