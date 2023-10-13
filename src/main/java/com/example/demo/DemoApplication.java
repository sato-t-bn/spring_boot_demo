package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Primary
	@Bean
	public SpringTemplateEngine pageTemplateEngine() {
		
		var resolver = new ClassLoaderTemplateResolver();
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setPrefix("templates/");
		resolver.setSuffix(".html");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setCacheable(false);

		var engine = new SpringTemplateEngine();
		engine.setTemplateResolver(resolver);
		
		return engine;
	}

	@Bean("messageTemplateEngine")
	public SpringTemplateEngine messageTemplateEngine() {
		
		var resolver = new ClassLoaderTemplateResolver();
		resolver.setTemplateMode(TemplateMode.TEXT);
		resolver.setPrefix("templates/messages/");
		resolver.setSuffix(".message");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setCacheable(true);
		
		var engine = new SpringTemplateEngine();
		engine.setTemplateResolver(resolver);
		
		return engine;
	}
}
