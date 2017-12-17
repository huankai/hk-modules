package com.hk.core.authentication.shiro.config;

import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hk.core.authentication.shiro.filters.AjaxFormAuthenticationFilter;



/**
 * 
 * @author huangkai
 * @date 2017年10月24日下午3:25:54
 */
@Configuration
public class ShiroWebFilterAutoConfiguration extends ShiroWebFilterConfiguration {

	@Bean
	@Override
	protected ShiroFilterFactoryBean shiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = super.shiroFilterFactoryBean();
		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
		AjaxFormAuthenticationFilter ajaxFormAuthenticationFilter = new AjaxFormAuthenticationFilter();
		ajaxFormAuthenticationFilter.setSaveRequestedUrlEnabled(false);
		filters.put("authc", ajaxFormAuthenticationFilter);
		return shiroFilterFactoryBean;
	}
}
