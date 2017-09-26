package com.allen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import java.util.List;

@SpringBootApplication
public class Chapter3Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter3Application.class, args);
	}

	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/login").setViewName("login");
	}

	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
		argumentResolvers.add(new ReaderHandlerMethodArgumentResolver());
	}
}
