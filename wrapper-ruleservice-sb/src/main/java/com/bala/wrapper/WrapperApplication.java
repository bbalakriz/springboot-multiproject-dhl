package com.bala.wrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;

@SpringBootApplication
@ComponentScan(basePackages = { "com.bala.export.*",
		"com.bala.military.*", "com.bala.wrapper.*" }, excludeFilters = @Filter(SpringBootApplication.class))
public class WrapperApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(WrapperApplication.class, args);
	}

}
