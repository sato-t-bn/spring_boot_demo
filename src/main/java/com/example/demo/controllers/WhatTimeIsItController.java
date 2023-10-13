package com.example.demo.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("view/what_time_is_it")
@Controller
public class WhatTimeIsItController {

	@GetMapping
	private String view(Model model) {

		String now = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

		model.addAttribute("datetime", now);

		return "wtii";
	}
}
