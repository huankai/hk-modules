package com.hk.fs;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author huangkai
 * @date 2017年12月18日下午5:51:52
 */
@SpringBootApplication
public class FileApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(FileApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

}
