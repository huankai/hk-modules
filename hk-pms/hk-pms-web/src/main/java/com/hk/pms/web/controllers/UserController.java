package com.hk.pms.web.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.core.authentication.shiro.encrypt.ShiroPasswordEncrypt;
import com.hk.pms.core.domain.User;
import com.hk.pms.core.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("save")
	public @ResponseBody User save() {
		User user = new User();
		user.setOrgId("1");
		user.setNickName("sadmin_nicke");
		user.setBrithday(LocalDate.now());
		user.setEmail("semails@xx.com");
		user.setUserName("admin");
		user.setPassWord(new ShiroPasswordEncrypt().asSha512HashToBase64("admin", user.getUserName()));
		user.setPhone("13805s0");
		user.setSex(0);
		user.setAaaa(LocalDateTime.now());
		user.setUserStatus(1);
		user.setIsProtected(true);
		user.setUserType(0);
		
		user = userService.saveOrUpdate(user);
//		System.out.println(JsonUtils.toJSONString(user));
		return user;
	}
}
