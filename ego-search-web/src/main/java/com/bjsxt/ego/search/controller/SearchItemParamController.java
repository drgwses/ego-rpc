package com.bjsxt.ego.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.ego.search.service.SearchItemParamService;

@Controller
public class SearchItemParamController {

	@Autowired
	private SearchItemParamService searchItemParamService;
	
	/****
	 * 处理商品规格参数的加载请求
	 */
	@RequestMapping(value="/item/param/{id}",
			produces=MediaType.TEXT_HTML_VALUE+";charset=UTF-8")
	@ResponseBody
	public String itemParam(@PathVariable Long id){
		return searchItemParamService.loadItemParamService(id);
	}
}
