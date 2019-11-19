package com.bjsxt.ego.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.ego.search.service.SearchItemDescService;

@Controller
public class SearchItemDescController {

	//注入service对象
	@Autowired
	private SearchItemDescService searchItemDescService;
	
	/****
	 * 处理加载商品描述信息的请求
	 */
	@RequestMapping("/item/desc/{id}")
	@ResponseBody
	public String loadItemDesc(@PathVariable Long id){
		return searchItemDescService.loadItemDescService(id);
	}
}
