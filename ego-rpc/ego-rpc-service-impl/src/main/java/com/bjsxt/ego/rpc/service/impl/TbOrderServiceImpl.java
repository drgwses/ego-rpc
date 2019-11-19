package com.bjsxt.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.rpc.mapper.TbOrderItemMapper;
import com.bjsxt.ego.rpc.mapper.TbOrderMapper;
import com.bjsxt.ego.rpc.mapper.TbOrderShippingMapper;
import com.bjsxt.ego.rpc.pojo.TbOrder;
import com.bjsxt.ego.rpc.pojo.TbOrderExample;
import com.bjsxt.ego.rpc.pojo.TbOrderExample.Criteria;
import com.bjsxt.ego.rpc.pojo.TbOrderItem;
import com.bjsxt.ego.rpc.pojo.TbOrderItemExample;
import com.bjsxt.ego.rpc.pojo.TbOrderShipping;
import com.bjsxt.ego.rpc.service.TbOrderService;
@Service
public class TbOrderServiceImpl implements TbOrderService {

	@Autowired
	private TbOrderMapper tbOrderMapper;
	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;
	@Autowired
	private TbOrderShippingMapper tbOrderShippingMapper;
	
	
	
	@Override
	public void saveTbOrderService(TbOrder tbOrder
			,List<TbOrderItem> orderItems, 
			TbOrderShipping tbOrderShipping) {
		// TODO Auto-generated method stub
		try {
			tbOrderMapper.insert(tbOrder);
			for(TbOrderItem orderItem:orderItems){
				
				tbOrderItemMapper.insert(orderItem);
			}
			tbOrderShippingMapper.insert(tbOrderShipping);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}



	@Override
	public List<TbOrder> loadTbOrderListService(Long id) {
		// TODO Auto-generated method stub
		
		//创建TbOrderExample
		TbOrderExample example=new TbOrderExample();
		Criteria c = example.createCriteria();
		//where user_id=id
		c.andUserIdEqualTo(id);
		return tbOrderMapper.selectByExample(example);
		 
	}



	@Override
	public List<TbOrderItem> loadTbOrderItemListService(String orderid) {
		// TODO Auto-generated method stub
		TbOrderItemExample example=new TbOrderItemExample();
		com.bjsxt.ego.rpc.pojo.TbOrderItemExample.Criteria c 
			= example.createCriteria();
		//where order_id=orderid;
		c.andOrderIdEqualTo(orderid);
		return tbOrderItemMapper.selectByExample(example);
		 
	}

}
