package com.bjsxt.ego.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.ego.beans.TreeNode;
import com.bjsxt.ego.manager.service.ManagerItemCatService;

@Controller
public class ItemCatController {

	//注入service对象
	@Autowired
	private ManagerItemCatService managerItemCatService;
	
	/***
	 * 处理加载商品类目的请求
	 * **/
	@RequestMapping(value="/item/cat/list",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public List<TreeNode> itemCatList(@RequestParam(defaultValue="0",required=false) Long id){
		return managerItemCatService.getItemCatList(id);
	}
	
	
	
}
