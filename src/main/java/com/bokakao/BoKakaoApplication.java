package com.bokakao;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BoKakaoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(BoKakaoApplication.class, args);
		SpringApplication application = new SpringApplication(BoKakaoApplication.class);
        application.setWebApplicationType(WebApplicationType.SERVLET);
        //application.setBannerMode(Banner.Mode.OFF);     // 배너 감추기
        application.run(args);
	}

}
