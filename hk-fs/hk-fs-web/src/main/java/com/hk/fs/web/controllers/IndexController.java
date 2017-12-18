package com.hk.fs.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hk.commons.fastjson.JsonUtils;
import com.hk.core.authentication.api.SecurityContext;
import com.hk.core.authentication.api.UserPrincipal;

/**
 * 
 * @author huangkai
 * @date 2017年10月23日上午11:00:09
 */
@Controller
public class IndexController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private SecurityContext securityContext;

	/**
	 * 首页
	 * 
	 * @return
	 */
	@GetMapping({ "/", "/index" })
	public String index() {
		boolean authenticated = securityContext.isAuthenticated();
		System.out.println(authenticated);
		UserPrincipal principal = securityContext.getPrincipal();
		System.out.println(JsonUtils.toJSONString(principal));
		return "/index";
	}

	/**
	 * 登陆页面
	 * 
	 * @return
	 */
	@RequestMapping("login")
	public String login() {
		// 如果已登陆的用户，再将访问时，重定向到首页
		// if(SecurityUtils.isAuthenticated()) {
		// return "redirect:/index";
		// }
		return "/login";
	}

	/**
	 * 用户访问未授权的请求时
	 * 
	 * @return
	 */
	@GetMapping("unauthorized")
	public String unauthorized() {
		// UserPrincipal principal = SecurityUtils.getPrincipal();
		// if(logger.isWarnEnabled()) {
		// logger.warn("用户 {} 访问未授权的请求，请求URL为 {}",
		// principal.getUserName(),Webs.getHttpServletRequest().getRequestURL());
		// }
		return "/unauthorized";
	}

}
