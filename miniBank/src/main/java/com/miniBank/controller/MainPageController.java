package com.miniBank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MainPageController {

	
	@RequestMapping("/mainpage")
	public String showMainPage() {
		return "mainpage";
	}
	
	
	
}
