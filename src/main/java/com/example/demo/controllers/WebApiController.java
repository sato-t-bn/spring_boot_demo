package com.example.demo.controllers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 単純なGET及びPOSTの操作を受け付けるREST APIのコントローラ
 * 
 *  * <p>
 * {@code application.properties#server.port = 8080} と設定していると仮定して、
 * {@code "localhost:8080/sample/api/test/*"} にアクセスする。
 * </p>
 * 
 */
@RestController
@RequestMapping("api")
public class WebApiController {
	
	// SLF4Jのログ出力	
	private static final Logger log = LoggerFactory.getLogger(WebApiController.class);

	/**
	 * 定型文を返すだけ
	 * 
	 */ 	
	@RequestMapping("hello")
	private String hello() {
		return "Hello, Spring Boot!";
	}
	
	/**
	 * パスに入力された文字列を取得し、画面上に返す
	 * 
	 * @param param パスパラメータとして入力された文字列
	 * @return パスパラメータとして入力された文字列
	 */
	@RequestMapping("test/{something}")
	private String testPathParam(@PathVariable("something") String param) {
		log.info(param);
		return "受け取ったパラメータ: " + param ;
	}
	
	/**
	 * クエリパラメータとして入力さた文字列を取得し、画面上に返す
	 * 
	 * @param param クエリパラメータとして入力された文字列
	 * @return クエリパラメータとして入力された文字列
	 */	
	@RequestMapping("test")
	private String testQueryParam(@RequestParam("param") String param) {
		log.info(param);
		return "受け取ったクエリ: " + param ;
	}
	
}
