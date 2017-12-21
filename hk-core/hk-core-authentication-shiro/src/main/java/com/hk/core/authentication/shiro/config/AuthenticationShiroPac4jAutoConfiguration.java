package com.hk.core.authentication.shiro.config;

import java.util.List;

import org.pac4j.core.client.Client;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.session.SessionStore;
import org.pac4j.core.engine.CallbackLogic;
import org.pac4j.core.engine.DefaultCallbackLogic;
import org.pac4j.core.engine.DefaultSecurityLogic;
import org.pac4j.core.engine.SecurityLogic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.shiro.ShiroSecurityContext;
import com.hk.core.authentication.shiro.encrypt.ShiroPasswordEncrypt;

import io.buji.pac4j.context.ShiroSessionStore;
import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.SecurityFilter;

/**
 * Shiro pac4j configuration
 * 
 * @author huangkai
 * @date 2017年12月21日下午12:38:36
 */
@Configuration
@SuppressWarnings("rawtypes")
public class AuthenticationShiroPac4jAutoConfiguration {

	/* ************Core shiro configuation ************ */

	/**
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(value = SecurityContext.class)
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

	/* ************Core pac4j configuation ************ */
	@Bean
	public Config config(List<Client> clientList) {
		Config config = new Config(clientList);
		config.setSessionStore(sessionStore());
		return config;
	}

	@Bean
	@ConditionalOnMissingBean(value = Clients.class)
	public Clients clients(List<Client> clientList) {
		Clients clients = new Clients();
		clients.setCallbackUrl("/callback");
		clients.setClients(clientList);
		return clients;
	}

	/*
	 * 需要每个应用配置
	 * 
	 * @return
	 * 
	 * @Bean
	 * 
	 * @ConditionalOnMissingBean(value = Client.class) public List<Client>
	 * defaultClient() { Authenticator<UsernamePasswordCredentials> authenticator =
	 * new LocalCachingAuthenticator<>(); Client<UsernamePasswordCredentials,
	 * CommonProfile> client = new FormClient(null, authenticator); return
	 * Lists.newArrayList(client); }
	 */

	@Bean
	@ConditionalOnMissingBean(value = SessionStore.class)
	public SessionStore sessionStore() {
		return new ShiroSessionStore();
	}

	/**
	 *
	 * 
	 * @param config
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(value = SecurityFilter.class)
	public SecurityFilter securityFilter(Config config) {
		SecurityFilter filter = new SecurityFilter();
		filter.setConfig(config);
		filter.setClients("form");
		filter.setSecurityLogic(defaultSecurityLogic());
		return filter;
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(value = SecurityLogic.class)
	private SecurityLogic<Object, J2EContext> defaultSecurityLogic() {
		return new DefaultSecurityLogic<>();
	}

	/**
	 * 
	 * @param config
	 * @param callbackLogic
	 * @return
	 */
	@Bean
	public CallbackFilter callbackFilter(Config config, CallbackLogic<Object, J2EContext> callbackLogic) {
		CallbackFilter filter = new CallbackFilter();
		filter.setConfig(config);
		filter.setCallbackLogic(callbackLogic);
		return filter;
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(value = CallbackLogic.class)
	public CallbackLogic<Object, J2EContext> defaultCallbackLogic() {
		return new DefaultCallbackLogic<>();
	}

}
