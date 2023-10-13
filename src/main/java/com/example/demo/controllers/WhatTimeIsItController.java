package com.example.demo.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 現在時刻を表示するHTMLページにサーバ現在時刻を送り込むコントローラ
 * 
 * 	@author sato.takayuki
 */
@RequestMapping("view/what_time_is_it")
@Controller
public class WhatTimeIsItController {

	/**
	 * 現在時刻を表示するHTMLページにサーバ現在時刻を送り込む
	 * 
	 * @param model HTMLページに送り込む値を格納するマップインスタンス
	 * @return 任意の文字列
	 */	
	@GetMapping
	private String view(Model model) {

		String now = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

		model.addAttribute("datetime", now);

		return "wtii";
	}
}
