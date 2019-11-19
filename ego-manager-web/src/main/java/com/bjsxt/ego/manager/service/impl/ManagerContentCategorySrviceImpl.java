package com.bjsxt.ego.manager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.IDUtils;
import com.bjsxt.ego.beans.TreeNode;
import com.bjsxt.ego.manager.service.ManagerContentCategorySrvice;
import com.bjsxt.ego.rpc.pojo.TbContentCategory;
import com.bjsxt.ego.rpc.service.TbContentCateGoryService;
 
@Service
public class ManagerContentCategorySrviceImpl implements ManagerContentCategorySrvice {

	//注入远程服务代理对象
	@Autowired
	private TbContentCateGoryService tbContentCateGoryServiceProxy;
	
	@Override
	public List<TreeNode> loadContentCategoryService(Long pid) {
		// TODO Auto-generated method stub
		List<TreeNode> list=new ArrayList<>();
		
		List<TbContentCategory> clist = 
				tbContentCateGoryServiceProxy.loadTbContentCateGoryByPidService(pid);
		
		for(TbContentCategory c:clist){
			//创建TreeNode对象
			TreeNode node=new TreeNode();
			
			node.setId(c.getId());
			node.setText(c.getName());
			node.setState(c.getIsParent()?"closed":"open");
			
			list.add(node);
		}
		
		return list;
	}

	@Override
	public EgoResult saveContentCategoryService(TbContentCategory contentCategory) {
		//创建Date对象
		Date date=new Date();
		//生成id
		Long id=IDUtils.genItemId();
		contentCategory.setId(id);
		contentCategory.setCreated(date);
		contentCategory.setUpdated(date);
		contentCategory.setStatus(1);
		contentCategory.setSortOrder(1);
		contentCategory.setIsParent(false);
		
		return tbContentCateGoryServiceProxy.saveTbContentCateGory(contentCategory);
	}

	@Override
	public void deleteContentCategoryService(Long id) {
		// TODO Auto-generated method stub
		tbContentCateGoryServiceProxy.deleteTbContentCateGoryService(id);
		
	}

	 
	 

}
