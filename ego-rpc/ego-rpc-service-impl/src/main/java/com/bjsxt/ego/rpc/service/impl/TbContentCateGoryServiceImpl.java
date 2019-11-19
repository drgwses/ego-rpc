package com.bjsxt.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.rpc.mapper.TbContentCategoryMapper;
import com.bjsxt.ego.rpc.pojo.TbContentCategory;
import com.bjsxt.ego.rpc.pojo.TbContentCategoryExample;
import com.bjsxt.ego.rpc.pojo.TbContentCategoryExample.Criteria;
import com.bjsxt.ego.rpc.service.TbContentCateGoryService;

@Service
public class TbContentCateGoryServiceImpl implements TbContentCateGoryService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	@Override
	public List<TbContentCategory> loadTbContentCateGoryByPidService(Long pid) {
		// TODO Auto-generated method stub
		//创建Example对象
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria c = example.createCriteria();
		//where parent_id=?
		c.andParentIdEqualTo(pid);
		
		return tbContentCategoryMapper.selectByExample(example);
		 
	}
	@Override
	public EgoResult saveTbContentCateGory(TbContentCategory contentCategory) {
		// TODO Auto-generated method stub
		
		EgoResult result=null;
		try{
			//TbContentCategory对象
			TbContentCategory record=new TbContentCategory();
			record.setIsParent(true);
			record.setId(contentCategory.getParentId());
			//更新当前添加的节点父节点的is_parent
			tbContentCategoryMapper.updateByPrimaryKeySelective(record);
			//添加内容分类节点
			tbContentCategoryMapper.insert(contentCategory);
			
			result=new EgoResult();
			result.setStatus(200);
			result.setData(contentCategory);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public void deleteTbContentCateGoryService(Long id) {
		// TODO Auto-generated method stub
		//更新当前点击的节点的父节点is_parent
		
		//获得当前点击的节点父节点id
		TbContentCategory contentCategory = 
				tbContentCategoryMapper.selectByPrimaryKey(id);
		
		Long pid=contentCategory.getParentId();
		
		//根据pid查询，pid对应的节点是否有子节点
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria c = example.createCriteria();
		//where parent_id=?
		c.andParentIdEqualTo(pid);
		int count = tbContentCategoryMapper.countByExample(example);
		if(count==1){//更新当前需要删除的节点的父节点的is_parent
			TbContentCategory record=new TbContentCategory();
			record.setId(pid);
			record.setIsParent(false);
			tbContentCategoryMapper.updateByPrimaryKeySelective(record);
		}
		
		//删除当前点击的节点
		tbContentCategoryMapper.deleteByPrimaryKey(id);
		
		
		
		
		
	}
	 
	 
	 

}
