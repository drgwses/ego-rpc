package com.bjsxt.ego.rpc.service;

import com.bjsxt.ego.rpc.pojo.TbItemParamItem;

public interface ParamItemService {

	/***
	 * 根据商品id,查询商品对应的规格参数信息
	 * @param itemid
	 * @return
	 */
	public TbItemParamItem loadTbItemParamItemService(Long itemid);
}
