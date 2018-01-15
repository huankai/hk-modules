package com.hk.award.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.hk.award.domain.User;
import com.hk.award.service.UserService;
import com.hk.award.util.AwardLevel;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(path = { "/", "/index" })
	public String index(ModelMap model) {
		List<User> userList = userService.getAllUserList();
		model.put("allUserList", userList);
		return "/index";
	}
	
	/**
	 * 中奖
	 * 
	 * @param phone
	 * @param level
	 * @return
	 */
	@PostMapping("/award")
	public ResponseEntity<String> award(@RequestParam String phone, @RequestParam String level) {
		AwardLevel awardLevel = AwardLevel.THREE;
		if ("1".equals(level)) {
			awardLevel = AwardLevel.ONE;
		} else if ("2".equals(level)) {
			awardLevel = AwardLevel.TWO;
		}
		User user = userService.awardUser(phone, awardLevel);
		return ResponseEntity.ok(JSON.toJSONString(user));
	}
}
