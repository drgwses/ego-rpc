package com.bjsxt.ego.rpc.service;

import java.util.List;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.rpc.pojo.TbContent;

public interface TbContentService {
	
	/***
	 * 根据内容分类的id，查询该分类下的所有内容
	 * @param cid
	 * @return
	 */
	public PageResult<TbContent> loadTbContentListService(Long cid,Integer page,Integer rows);
	
	/***
	 * 完成内容的添加
	 * @param tbContent
	 * @return
	 */
	public EgoResult saveTbContentService(TbContent tbContent);
	
	/****
	 * 实现内容的批量删除
	 * @param ids
	 * @return
	 */
	public EgoResult deleteTbContentService(List<Long> ids);
	
	/***
	 * 完成内容的更新
	 * @param tbContent
	 * @return
	 */
	public EgoResult updateTbContentService(TbContent tbContent);
	
	/****
	 * 加载某个类目对应的内容列表
	 */
	public List<TbContent> loadTbContentListByCidService(Long cid);

}
