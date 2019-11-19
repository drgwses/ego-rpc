package com.bjsxt.ego.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.manager.service.ManagerContentService;
import com.bjsxt.ego.rpc.pojo.TbContent;

@Controller
public class ManagerContentController {

	//注入service对象
	@Autowired
	private ManagerContentService managerContentService;
	
	/****
	 * 处理内容分页查询请求
	 */
	@RequestMapping(value="/content/query/list",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public PageResult<TbContent> contentQueryList(Long categoryId,Integer page,Integer rows){
		return managerContentService.loadContentListService(categoryId, page, rows);
	}
	
	/****
	 * 处理内容添加请求
	 */
	@RequestMapping(value="/content/save",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult contentSave(TbContent tbContent){
		return managerContentService.saveContentService(tbContent);
	}
	
	/****
	 * 处理内容删除请求
	 */
	@RequestMapping(value="/content/delete",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult contentDelete(String ids){
		return managerContentService.deleteContentService(ids);
	}
	
	/****
	 * 处理内容更新请求
	 */
	@RequestMapping(value="/rest/content/edit",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult contentEdit(TbContent tbContent){
		return managerContentService.updateContentService(tbContent);
	}
}
