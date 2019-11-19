package com.bjsxt.ego.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.TreeNode;
import com.bjsxt.ego.manager.service.ManagerContentCategorySrvice;
import com.bjsxt.ego.rpc.pojo.TbContentCategory;

@Controller
public class ManagerContentCategoryController {

	//注入自己的serivce对象
	@Autowired
	private ManagerContentCategorySrvice managerContentCategorySrvice;
	
	/****
	 * 处理加载内容分类树的请求
	 */
	@RequestMapping(value="/content/category/list",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public List<TreeNode> contentCategroyList(@RequestParam(defaultValue="0") Long id){
		
		return managerContentCategorySrvice.loadContentCategoryService(id);
	}
	
	/****
	 * 处理添加内容分类节点的请求
	 */
	@RequestMapping(value="/content/category/create",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult contentCategroyCreate(TbContentCategory contentCategory){
		
		return managerContentCategorySrvice.saveContentCategoryService(contentCategory);
	}
	
	/****
	 * 处理添加内容分类节点的请求
	 */
	@RequestMapping(value="/content/category/delete",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult contentCategroyDelete(Long id){
		
		managerContentCategorySrvice.deleteContentCategoryService(id); 
		return null;
	} 
	
}
