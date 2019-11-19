package com.bjsxt.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.rpc.mapper.TbItemCatMapper;
import com.bjsxt.ego.rpc.pojo.TbItemCat;
import com.bjsxt.ego.rpc.pojo.TbItemCatExample;
import com.bjsxt.ego.rpc.pojo.TbItemCatExample.Criteria;
import com.bjsxt.ego.rpc.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService {

	//注入Mapper接口代理对象
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Override
	public List<TbItemCat> getItemCatListByParentId(Long id) {
		// TODO Auto-generated method stub
		//创建TbItemCatExample对象
		TbItemCatExample example=new TbItemCatExample();
		
		Criteria c = example.createCriteria();
		//where parent_id=?
		c.andParentIdEqualTo(id);
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		return list;
	}
	@Override
	public List<TbItemCat> loadItemCatListService() {
		// TODO Auto-generated method stub
		TbItemCatExample example=new TbItemCatExample();
		return tbItemCatMapper.selectByExample(example);
	}
	 

}
