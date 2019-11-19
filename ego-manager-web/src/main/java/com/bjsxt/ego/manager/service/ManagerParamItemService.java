package com.bjsxt.ego.manager.service;

import com.bjsxt.ego.beans.EgoResult;

public interface ManagerParamItemService {

	/***
	 * 根据商品id,加载商品规格参数
	 * @param itemid
	 * @return
	 */
	public EgoResult loadParamItemService(Long itemid);
}
