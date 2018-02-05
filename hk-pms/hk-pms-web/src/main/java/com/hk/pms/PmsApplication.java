package com.hk.pms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

import com.hk.pms.core.service.UserService;

/**
 * 
 * @author huangkai
 *
 */
@ServletComponentScan(basePackages = { "com.hk.core" })
@SpringBootApplication()
@EnableCaching() // 启动缓存
//@EnableJpaAuditing() // 启动审计功能
public class PmsApplication /* extends SpringBootServletInitializer */ {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(PmsApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

	// @Override
	// protected SpringApplicationBuilder configure(SpringApplicationBuilder
	// builder) {
	// return builder.sources(PmsApplication.class).bannerMode(Mode.OFF);
	// }

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	
	/* ******************************Shiro configuration start ************************************* */
	
//	@Bean("jdbcRealm")
//	public Realm realm() {
//		JdbcRealm jdbcRealm = new JdbcRealm();
//		jdbcRealm.setUserService(userService);
//		jdbcRealm.setCachingEnabled(true);
//		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher(Sha512Hash.ALGORITHM_NAME);
//		credentialsMatcher.setStoredCredentialsHexEncoded(false); // 是否使用Hex，这里不使用，密码加密@see
//																	// com.hk.commons.shiro.CryptosUtils.asSha512HashToBase64(Object,
//																	// Object)
//		jdbcRealm.setCredentialsMatcher(credentialsMatcher);
//		return jdbcRealm;
//	}
//
//	@Bean
//	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//		DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//		// chainDefinition.addPathDefinition("/login", "authc"); // need to accept POSTs
//		// from the login form
//		// chainDefinition.addPathDefinition("/login", "anon");
//		chainDefinition.addPathDefinition("/resources/**", "anon");// 静态资源过滤
//		chainDefinition.addPathDefinition("/favicon.ico", "anon");// ico过滤，不配置可能会出现登陆成功后下载ico的问题
//		chainDefinition.addPathDefinition("/kaptcha.jpg", "anon"); // 验证码过滤
//		// chainDefinition.addPathDefinition("/logout", "logout");// logout 过滤器
//		chainDefinition.addPathDefinition("/**", "authc");// 其它都需要验证
//		return chainDefinition;
//	}
	
	/* ******************************Shiro configuration end ************************************* */
	
	
//	@Bean
//	@SuppressWarnings("rawtypes")
//	public List<Client> clientList() {
//		Authenticator<UsernamePasswordCredentials> authenticator = new LocalCachingAuthenticator<>();
//		Client<UsernamePasswordCredentials, CommonProfile> client = new FormClient(null, authenticator);
//		return Lists.newArrayList(client);
//	}
}
