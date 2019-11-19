package com.bjsxt.ego.rpc.service;

import java.util.List;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.rpc.pojo.TbItemParam;

public interface ItemParamService {

	/****
	 * 商品规格参数模板的分页显示
	 */
	public PageResult<TbItemParam> 
		loadTbItemParamListService(Integer page,Integer rows);
	
	
	/****
	 * 根据商品类名的id，获得该类名的规格参数模板对象
	 * @param cid
	 * @return
	 */
	public TbItemParam loadTbItemParamByCidService(Long cid);
	
	
	/***
	 * 添加商品规格参数模板信息
	 * @param tbItemParam
	 * @return
	 */
	public EgoResult saveTbItemParamService(TbItemParam tbItemParam);
	
	/***
	 * 实现商品规格参数模板信息的批量删除
	 * @param ids
	 * @return
	 */
	public EgoResult deleteTbItemParamService(List<Long> ids);
	
	  
}
