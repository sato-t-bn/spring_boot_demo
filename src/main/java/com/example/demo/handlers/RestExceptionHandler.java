package com.example.demo.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.controllers.WebApiController;
import com.example.demo.utils.JsonMapper;


/**
 * HTTPエラーの集約例外ハンドラークラス
 * 
 * @author sato.takayuki
 */
@RestControllerAdvice
public class RestExceptionHandler {

	// SLF4Jのログ出力	
	private static final Logger log = LoggerFactory.getLogger(WebApiController.class);
	
	/**
	 * APIエラーを返す
	 * 
	 * @param ex 例外インスタンス
	 * @return エラーのレスポンスエンティティ
	 */	
	@ExceptionHandler
	private ResponseEntity<String> onError(Exception ex) {
		
		log.error(ex.getMessage(), ex);
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String json = JsonMapper.map()
				.put("message", "APIエラー")
				.put("detail", ex.getMessage())
				.put("status", status.value())
				.stringify();
		
		return new ResponseEntity<String>(json, status);
	}

}
