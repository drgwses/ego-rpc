package com.bjsxt.ego.manager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.manager.service.ManagerContentService;
import com.bjsxt.ego.rpc.pojo.TbContent;
import com.bjsxt.ego.rpc.service.TbContentService;
@Service
public class ManagerContentServiceImpl implements ManagerContentService {

	//注入远程服务代理对象
	@Autowired
	private TbContentService tbContentServiceProxy;
	@Override
	public PageResult<TbContent> loadContentListService(Long cid, 
			Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return tbContentServiceProxy.loadTbContentListService(cid, page, rows);
	}
	@Override
	public EgoResult saveContentService(TbContent tbContent) {
		// TODO Auto-generated method stub
		
		Date date=new Date();
		tbContent.setCreated(date);
		tbContent.setUpdated(date);
		return tbContentServiceProxy.saveTbContentService(tbContent);
	}
	@Override
	public EgoResult deleteContentService(String ids) {
		// TODO Auto-generated method stub
		String[] idss=ids.split(",");
		//将idss转化为List<Long>
		
		//创建List集合对象
		List<Long> list = new ArrayList<>();
		
		for(String id:idss){
			list.add(Long.parseLong(id));
		}
		return tbContentServiceProxy.deleteTbContentService(list);
	}
	@Override
	public EgoResult updateContentService(TbContent tbContent) {
		// TODO Auto-generated method stub
		
		
		Date date = new Date();
		tbContent.setUpdated(date);
		return tbContentServiceProxy.updateTbContentService(tbContent);
	}

}
