package com.hk.core.authentication.shiro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hk.core.authentication.shiro.ShiroSecurityContext;
import com.hk.core.authentication.shiro.encrypt.ShiroPasswordEncrypt;

/**
 * 
 * @author huangkai
 *
 */
@Configuration
public class ShiroAutoConfiguration {

	/**
	 * 
	 * @return
	 */
	@Bean
	public ShiroSecurityContext securityContext() {
		return new ShiroSecurityContext();
	}

	/**
	 * 密码加密
	 * 
	 * @return
	 */
	@Bean
	public ShiroPasswordEncrypt passwordEncrypt() {
		return new ShiroPasswordEncrypt();
	}
}
