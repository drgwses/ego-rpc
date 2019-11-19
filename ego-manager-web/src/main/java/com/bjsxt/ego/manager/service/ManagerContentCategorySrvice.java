package com.bjsxt.ego.manager.service;

import java.util.List;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.TreeNode;
import com.bjsxt.ego.rpc.pojo.TbContentCategory;

public interface ManagerContentCategorySrvice {

	/***
	 * 加载内容分类树
	 * @param pid
	 * @return
	 */
	public List<TreeNode> loadContentCategoryService(Long pid);
	
	/***
	 * 添加内容分类节点
	 * @param contentCategory
	 * @return
	 */
	public EgoResult saveContentCategoryService(TbContentCategory contentCategory);
	
	/****
	 * 删除内容分类节点
	 * @param id
	 */
	public void deleteContentCategoryService(Long id);
	 
	 
}
