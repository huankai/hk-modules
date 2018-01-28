package com.hk.fs;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.hk.commons.files.FileHandler;
import com.hk.commons.files.LocalFileHandler;

/**
 * 
 * @author huangkai
 * @date 2017年12月18日下午5:51:52
 */
@SpringBootApplication
@EnableScheduling
public class FileApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileApplication.class);

	/**
	 * 文件上传基本路径
	 */
	@Value("${fs.upload.basepath}")
	private String basePath;

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(FileApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

	/* *****************文件处理器 ************************************ */

	/**
	 * 本地文件处理器
	 * 
	 * @return
	 */
	@Bean
	public FileHandler fileHandler() {
		return new LocalFileHandler(basePath);
	}

	/* ***************** 定时任务，删除目录 ************************************ */

	/**
	 * 每天早上3点执行
	 */
	@Scheduled(cron = "0 0 3 * * ?")
	public void deleteDir() {
		LOGGER.info("开始删除目录 : {} ",basePath);
		try {
			File file = new File(basePath);
			if (file.isDirectory()) {
				FileUtils.deleteDirectory(file);
				LOGGER.info("删除成功..." );
			}
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("删除文件失败，文件目录 为 {},时间：{},错误原因：{}", basePath, LocalDateTime.now(), e.getMessage());
		}
	}

}
