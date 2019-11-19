package com.bjsxt.ego.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	
	/**
	 * 加载商城后台系统的首页
	 * **/
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	
	/**
	 * 加载其他的jsp试图
	 * **/
	@RequestMapping("{page}")
	public String showPage(@PathVariable String page){
		return page;
	}
}
