package com.bjsxt.ego.manager.service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.rpc.pojo.TbContent;

public interface ManagerContentService {

	/***
	 * 根据内容分类的id,进行内容的分页查询
	 */
	public PageResult<TbContent> loadContentListService(Long cid,Integer page,Integer rows);
	/***
	 * 完成内容的添加
	 */
	public EgoResult  saveContentService(TbContent tbContent);
	
	/***
	 * 实现内容的批量删除
	 */
	public EgoResult deleteContentService(String ids);
	
	/****
	 * 实现内容数据的更新操作
	 */
	public EgoResult updateContentService(TbContent tbContent);
}
