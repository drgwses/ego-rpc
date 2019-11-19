package com.bjsxt.ego.rpc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.rpc.mapper.TbItemDescMapper;
import com.bjsxt.ego.rpc.pojo.TbItemDesc;
import com.bjsxt.ego.rpc.service.ItemDescService;

@Service
public class ItemDescServiceImpl implements ItemDescService {

	//注入mapper接口代理对象
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Override
	public TbItemDesc getItemDesc(Long itemId) {
		// TODO Auto-generated method stub
		return tbItemDescMapper.selectByPrimaryKey(itemId);
	}

}
