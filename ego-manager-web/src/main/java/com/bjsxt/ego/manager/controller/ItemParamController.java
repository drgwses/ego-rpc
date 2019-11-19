package com.bjsxt.ego.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.manager.service.ManagerItemParamService;
import com.bjsxt.ego.rpc.pojo.TbItemParam;

@Controller
public class ItemParamController {

	//注入service对象
	@Autowired
	private ManagerItemParamService managerItemParamService;
	
	/****
	 * 处理规格参数模板分页查询的请求
	 */
	@RequestMapping(value="/item/param/list",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public PageResult<TbItemParam> 
			itemParamList(@RequestParam(defaultValue="1")Integer page,
			@RequestParam(defaultValue="30")Integer rows){
		return managerItemParamService.loadItemParamListService(page, rows);
	}
	
	/****
	 * 处理根据商品类名id，查询规格模板请求
	 * @return
	 */
	@RequestMapping(value="/item/param/query/{cid}",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult
			itemParamQuery(@PathVariable Long cid){
		return managerItemParamService.loadItemParamByCidService(cid);
	}
	
	
	/****
	 * 处理新增商品规格参数模板的请求
	 * @param cid
	 * @param paramData
	 * @return
	 */
	@RequestMapping(value="/item/param/save/{cid}",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult saveItemParam(@PathVariable Long cid,String paramData){
		
				return managerItemParamService.saveItemParamService(cid, paramData);
	}
	
	
	/****
	 * 处理商品规格参数模板批量删除的请求
	 * @param cid
	 * @param paramData
	 * @return
	 */
	@RequestMapping(value="/item/param/delete",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult deleteItemParam(Long[] ids){
		
		return managerItemParamService.deleteItemParamService(ids);
	}
	
	/****
	 * 处理根据商品类名id，查询规格模板请求
	 * @return
	 */
	@RequestMapping(value="/item/param/select/{cid}",
			produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public EgoResult
			itemParamSelect(@PathVariable Long cid){
		return managerItemParamService.loadItemParamByCidService(cid);
	}
}
