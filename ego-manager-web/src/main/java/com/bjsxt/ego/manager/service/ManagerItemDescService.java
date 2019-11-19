package com.bjsxt.ego.manager.service;

import com.bjsxt.ego.beans.EgoResult;

public interface ManagerItemDescService {
	
	/**
	 * 获得需要回显的商品描述
	 * **/
	public EgoResult getItemDescService(Long itemId);

}
