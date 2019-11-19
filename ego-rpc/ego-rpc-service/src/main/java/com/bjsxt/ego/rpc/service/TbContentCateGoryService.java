package com.bjsxt.ego.rpc.service;

import java.util.List;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.rpc.pojo.TbContentCategory;

public interface TbContentCateGoryService {

	/***
	 * 加载内容分类树
	 * @param pid
	 * @return
	 */
	public List<TbContentCategory> loadTbContentCateGoryByPidService(Long pid);
	
	/****
	 * 添加内容分类节点
	 */
	public EgoResult saveTbContentCateGory(TbContentCategory contentCategory);
	
	/*****
	 * 删除内容分类节点
	 * @param id
	 */
	public void deleteTbContentCateGoryService(Long id);
	
 
	
	 
	 
}
