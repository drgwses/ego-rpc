package com.bjsxt.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.rpc.mapper.TbItemParamItemMapper;
import com.bjsxt.ego.rpc.pojo.TbItemParamItem;
import com.bjsxt.ego.rpc.pojo.TbItemParamItemExample;
import com.bjsxt.ego.rpc.pojo.TbItemParamItemExample.Criteria;
import com.bjsxt.ego.rpc.service.ParamItemService;
@Service
public class ParamItemServiceImpl implements ParamItemService {

	//注入mapper代理对象
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	@Override
	public TbItemParamItem loadTbItemParamItemService(Long itemid) {
		// TODO Auto-generated method stub
		
		TbItemParamItemExample example=new TbItemParamItemExample();
		
		Criteria c = example.createCriteria();
		
		//封装查询条件
		c.andItemIdEqualTo(itemid);
		
		List<TbItemParamItem> list = 
				tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		
		if(list!=null && list.size()==1)
			return list.get(0);
		return null;
	}

}
