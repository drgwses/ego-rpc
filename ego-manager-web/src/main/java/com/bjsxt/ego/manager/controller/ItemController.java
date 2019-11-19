package com.bjsxt.ego.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.manager.service.ManagerItemService;
import com.bjsxt.ego.rpc.pojo.TbItem;

@Controller
public class ItemController {
	
	
	//注入service对象
	@Autowired
	private ManagerItemService managerItemService;
	
	/***
	 * 处理商品信息分页查询的请求
	 * **/
	@RequestMapping(value="item/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public PageResult<TbItem> itemList(@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="30")Integer rows){
		return managerItemService.selectItemListService(page, rows);
	}
	
	/**
	 * 处理商品上架的请求
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="item/reshelf",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult reshelfItem(Long[] ids){
		return managerItemService.reshelfItem(ids);
	}
	
	/***
	 * 处理商品下架请求
	 * **/
	@RequestMapping(value="item/instock",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult instockItem(Long[] ids){
		return managerItemService.instockItem(ids);
	}
	
	/***
	 * 处理商品信息的删除请求
	 * **/
	@RequestMapping(value="item/delete",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult itemDelete(Long[] ids){
		return managerItemService.delteItemService(ids);
	}
	
	/***
	 *处理商品信息发布的请求
	 * **/
	@RequestMapping(value="item/save",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult itemSave(TbItem item,String desc,String itemParams){
		
		
		return managerItemService.saveItemService(item, desc,itemParams);
	}
	

	/***
	 *处理商品信息更新的请求
	 * **/
	@RequestMapping(value="item/update",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult itemUpate(TbItem item,String desc,String itemParams){
		
		
		return managerItemService.updateItemService(item, desc,itemParams);
	}
}
