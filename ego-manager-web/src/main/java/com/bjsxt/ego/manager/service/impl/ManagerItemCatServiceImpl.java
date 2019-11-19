package com.bjsxt.ego.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.TreeNode;
import com.bjsxt.ego.manager.service.ManagerItemCatService;
import com.bjsxt.ego.rpc.pojo.TbItemCat;
import com.bjsxt.ego.rpc.service.ItemCatService;
@Service
public class ManagerItemCatServiceImpl implements ManagerItemCatService {

	//注入远程服务的代理对象
	@Autowired
	private ItemCatService itemCatServiceProxy;
	@Override
	public List<TreeNode> getItemCatList(Long id) {
		// TODO Auto-generated method stub
		
		//调用远程服务
		List<TbItemCat> list = itemCatServiceProxy.getItemCatListByParentId(id);
		
		//创建List<TreeNode>集合对象
		List<TreeNode> nodeList=new ArrayList<TreeNode>();
		
		TreeNode node=null;
		for(TbItemCat cat:list){
			node = new TreeNode();
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getIsParent()?"closed":"open");
			
			nodeList.add(node);
			
		}
		
		return nodeList;
	}

}
