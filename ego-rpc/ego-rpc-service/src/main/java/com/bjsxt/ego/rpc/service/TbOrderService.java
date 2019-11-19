package com.bjsxt.ego.rpc.service;

import java.util.List;

import com.bjsxt.ego.rpc.pojo.TbOrder;
import com.bjsxt.ego.rpc.pojo.TbOrderItem;
import com.bjsxt.ego.rpc.pojo.TbOrderShipping;

public interface TbOrderService {

	/****
	 * 完成订单信息的保存
	 */
	public void saveTbOrderService(TbOrder tbOrder,List<TbOrderItem> orderItems,
			TbOrderShipping tbOrderShipping);
	/***
	 * 根据用户id，加载当前登录用户的订单列表
	 * @param id
	 * @return
	 */
	public List<TbOrder> loadTbOrderListService(Long id);

	/***
	 * 根据订单的id,查询订单的明细
	 * @param orderid
	 * @return
	 */
	public List<TbOrderItem> loadTbOrderItemListService(String orderid);
}
