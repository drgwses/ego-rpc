package com.bjsxt.ego.manager.service;

import java.util.List;

import com.bjsxt.ego.beans.TreeNode;

public interface ManagerItemCatService {

	/***
	 * 根据节点id，加载当前节点的所有子节点集合
	 * **/
	public List<TreeNode> getItemCatList(Long id);
}
