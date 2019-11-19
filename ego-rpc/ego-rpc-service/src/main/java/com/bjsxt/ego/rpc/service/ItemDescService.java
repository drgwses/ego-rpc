package com.bjsxt.ego.rpc.service;

import com.bjsxt.ego.rpc.pojo.TbItemDesc;

public interface ItemDescService {
	
	/***
	 * 通过商品的id，获得商品的描述信息
	 * @param itemId
	 * @return
	 */
	public TbItemDesc getItemDesc(Long itemId);

}
