/**
 * 
 */
package com.hk.award;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.hk.award.domain.User;
import com.hk.award.repository.UserRepository;
import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.model.ReadableParam;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.poi.excel.read.SimpleDomReadableExcel;

/**
 * @author kally
 * @date 2018年1月15日下午1:31:47
 */
@SpringBootApplication
public class AwardApplication implements ApplicationRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AwardApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Resource resource = new ClassPathResource("user.xlsx");
		ReadableParam<User> readableParam = new ReadableParam<>();
		readableParam.setBeanClazz(User.class);
		readableParam.setTitleRow(0);
		readableParam.setDataStartRow(1);
		ReadableExcel<User> readableExcel = new SimpleDomReadableExcel<>(readableParam);
		ReadResult<User> result = readableExcel.read(resource.getInputStream());
		userRepository.init(result.getAllSheetData());
	}

}
