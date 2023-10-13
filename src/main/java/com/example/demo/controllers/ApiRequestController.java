package com.example.demo.controllers;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 外部APIを呼び出すコントローラクラス
 * 
 * @author sato.takayuki
 */
@RestController
public class ApiRequestController {

	/**
	 * 東京の天気予報を天気予報APIより取得して返す
	 * 
	 * @return 天気予報APIより取得したJSONレスポンスのボディ
	 */	
	@RequestMapping(value = "weather/tokyo", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	private String callWeatherAtTokyo() {

		RestTemplate rest = new RestTemplate();

		final String cityCode = "130010";
		final String endpoint = "https://weather.tsukumijima.net/api/forecast";

		final String url = endpoint + "?city=" + cityCode;
		
		ResponseEntity<String> response = rest.getForEntity(url, String.class);
		
		String json = response.getBody();
		
		return StringEscapeUtils.unescapeJava(json);
	}
}
