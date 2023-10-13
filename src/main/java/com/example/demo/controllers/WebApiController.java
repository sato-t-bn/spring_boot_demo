package com.example.demo.controllers;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

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
	
	/**
	 * 500エラーを返す
	 * なお、エラーレスポンスは集約例外ハンドラで生成する
	 * 
	 * @return RuntimeExceptionのインスタンス
	 */	
	@RequestMapping("test/ex")
	public String testException() throws Exception {
		throw new RuntimeException("エラーが発生しました");
	}
	
	/**
	 * 現在時刻をテキストメッセージとして返す
	 * メッセージテンプレートを使用
	 * 
	 * @return 現在時刻を表示するテキストメッセージ
	 */	
	@GetMapping("what_time_is_it")
	public String whatTimeIsIt() {
		
		LocalDateTime now = LocalDateTime.now();
		
		String hour = now.getHour() + "時";
		String timestamp = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(now);
		
		return process(hour, timestamp);
	}

	/**
	 * メッセージテンプレートにサーバ現在時刻を流し込む
	 * 
	 * @param hour サーバ現在時
	 * @param timestamp サーバ現在時刻
	 * @return テンプレートへの変数流し込み結果
	 */	
	private String process(String hour, String timestamp) {
		
		var resolver = new ClassLoaderTemplateResolver();
		resolver.setTemplateMode(TemplateMode.TEXT);
		resolver.setPrefix("templates/messages/");
		resolver.setSuffix(".message");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setCacheable(true);

		var engine = new SpringTemplateEngine();
		engine.setTemplateResolver(resolver);
		
		var context = new Context();
		context.setVariable("hour", hour);
		context.setVariable("timestamp", timestamp);
		
		final String message = engine.process("wtii", context);
		log.debug(message);
		return message;
	}
}
