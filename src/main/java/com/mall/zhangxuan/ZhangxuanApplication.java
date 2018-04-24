package com.mall.zhangxuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;

import javax.servlet.MultipartConfigElement;
import java.io.FileNotFoundException;

@SpringBootApplication
public class ZhangxuanApplication {

	public static void main(String[] args) {
        SpringApplication.run(ZhangxuanApplication.class, args);
    }

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		try {
			String rootPath = ResourceUtils.getURL("classpath:").getPath();
			factory.setLocation(rootPath);
		} catch (FileNotFoundException e) {
			e.fillInStackTrace();
		}

		return factory.createMultipartConfig();
	}

}
