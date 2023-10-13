package com.example.demo.controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class WebApiController {

	@RequestMapping("hello")
	private String hello() {
		return "Hello, Spring Boot!";
	}
	
	@RequestMapping("test/{something}")
	private String testPathParam(@PathVariable("something") String param) {
		return "受け取ったパラメータ: " + param ;
	}
	
	@RequestMapping("test")
	private String testQueryParam(@RequestParam("param") String param) {
		return "受け取ったクエリ: " + param ;
	}
}
