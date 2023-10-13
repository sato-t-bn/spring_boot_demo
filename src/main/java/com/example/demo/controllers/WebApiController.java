package com.example.demo.controllers;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.SamplePostDto;

/**
 * 単純なGET及びPOSTの操作を受け付けるREST APIのコントローラ
 * 
 *  * <p>
 * {@code application.properties#server.port = 8080} と設定していると仮定して、
 * {@code "localhost:8080/sample/api/test/*"} にアクセスする。
 * </p>
 * 
 * @author sato.takayuki
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
		return "受け取ったパラメータ: " + param;
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
		return "受け取ったクエリ: " + param;
	}

	/**
	 * POSTされたJSONデータを取得する
	 * 
	 *  @param body SamplePostDtoに準拠するJSONオブジェクト
	 *  @return POSTにより取得したJSONオブジェクト
	 */	
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	private String postSamplePostDto(@RequestBody SamplePostDto body) {
		log.info(Integer.toString(body.getNumber()));
		return "受け取ったリクエストボディ: " + body;
	}
	
	/**
	 * POSTされたJSONデータを取得し、JSONデータを返す
	 * 
	 *  @param body SamplePostDtoに準拠するJSONオブジェクト
	 *  @return POSTにより取得したJSONオブジェクト
	 */	
	@RequestMapping(value = "/test/return", method = RequestMethod.POST)
	private SamplePostDto postAndReturnSamplePostDto(@RequestBody SamplePostDto body) {
		log.info(Integer.toString(body.getNumber()));
		SamplePostDto response = new SamplePostDto(body.getNumber(), body.getPhrase());
		return response;
	}

	/**
	 * イメージ画像をリクエスト先に戻す
	 * 
	 * @return イメージ画像ファイル(今回はローカルのCドライブ内に用意する)
	 */	
	@RequestMapping(value="/get_image", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	private Resource returnImageFile() {
		log.info("画像ファイルを送信します");
		return new FileSystemResource(new File("C:\\Users\\sato.takayuki\\Downloads\\EbBwP4iUYAIGoHE.jpg"));
	}
	
	@RequestMapping("test/ex")
	public String testException() throws Exception {
		throw new RuntimeException("エラーが発生しました");
	}
}
