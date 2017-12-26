package com.hk.pms.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.hk.commons.fastjson.JsonUtils;
import com.hk.core.authentication.shiro.encrypt.ShiroPasswordEncrypt;
import com.hk.pms.PmsApplication;
import com.hk.pms.core.domain.User;
import com.hk.pms.core.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { PmsApplication.class })
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test()
	@Transactional(readOnly = true)
	public void findUniqueByLoginNameTest() {
		Optional<User> user = userRepository.findUniqueByLoginName("admin");
		if(user.isPresent()) {
			System.out.println(user.get().getEmail());
			System.out.println(JsonUtils.toJSONString(user.get()));
		}
	}
	
	@Test()
	@Transactional(readOnly = true)
	public void queryTest() {
//		userRepository.query();
	}
	
	@Test()
	@Transactional(readOnly = true)
	public void findByUserTypeTest() {
		Stream<User> users = userRepository.findByUserType(0);
		List<User> userList = users.collect(Collectors.toList());
		userList.forEach(user -> System.out.println(JsonUtils.toJSONString(user)));
	}
	
	@Test()
	public void findByOrgIdTest() {
//		List<User> users = userRepository.findByOrgId("1");
//		users.forEach(user -> System.out.println(JsonUtils.toJSONString(user)));
	}
	@Test()
	public void exampleTest() {
		User user = new User();
//		user.setOrgId("1");
		List<User> users = userRepository.findAll(Example.of(user));
		System.out.println(JsonUtils.toJSONString(users));
		System.err.println("--------------");
		users = userRepository.findAll(Example.of(user, ExampleMatcher.matching().withIgnorePaths("orgId").withIgnoreNullValues()));
		System.out.println(JsonUtils.toJSONString(users));
		System.err.println("--------------");
		Page<User> page = userRepository.findAll(Example.of(user),new PageRequest(0, 10,new Sort(new org.springframework.data.domain.Sort.Order(Direction.ASC,"orgId"))));
		System.out.println(JsonUtils.toJSONString(page));
	}
	
	@Test()
	public void update() {
		User user = userRepository.findOne("402881e660398959016039897dd40000");
//		if(null != user) {
//			user.setUserStatus(0);
//			user = userRepository.save(user);
//		}
		System.out.println(JsonUtils.toJSONString(user));
	}

	@Test
	public void save() {
		User user = new User();
//		user.setOrgId("1");
		user.setNickName("sadmin_nicke");
		user.setBrithday(LocalDate.now());
		user.setEmail("semail@xx.com");
		user.setUserName("admin1");
		user.setPassWord(new ShiroPasswordEncrypt().asSha512HashToBase64("admin", user.getUserName()));
		user.setPhone("138050");
		user.setSex(0);
		user.setUserStatus(1);
		user.setIsProtected(true);
		user.setUserType(0);
		user.setCreatedBy("1");
		user.setCreatedDate(DateTime.now());
		user.setLastModifiedBy("1");
		user.setLastModifiedDate(DateTime.now());
		User saved = userRepository.save(user);
		System.out.println(JsonUtils.toJSONString(saved));
	}

	@Test
	public void batchSave() {
		List<User> users = Lists.newArrayList();
		for (int i = 0; i < 10000; i++) {
			User user = new User();
//			user.setOrgId("1");
			user.setNickName("sadmin_nicke" + i);
			user.setBrithday(LocalDate.now());
			user.setEmail("semail@xx.com" + i);
			user.setUserName("admin" + i);
			user.setPassWord(new ShiroPasswordEncrypt().asSha512HashToBase64("admin", user.getUserName()));
			user.setPhone("138050" + i);
			user.setSex(0);
			user.setUserStatus(1);
			user.setIsProtected(true);
			user.setUserType(0);
//			user.setCreatedBy("1");
//			user.setCreatedDate(LocalDateTime.now());
//			user.setLastModifiedBy("1");
//			user.setLastModifiedDate(LocalDateTime.now());
			users.add(user);
		}
		long start = System.currentTimeMillis();
		List<User> saved = userRepository.save(users);
		long time = System.currentTimeMillis() - start;
		// System.out.println(JsonUtils.toJSONString(saved));
		System.err.println(saved.size() + ",时间：" + time);
	}

}
