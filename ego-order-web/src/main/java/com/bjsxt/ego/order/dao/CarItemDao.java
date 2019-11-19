package com.bjsxt.ego.order.dao;

import java.util.Map;

public interface CarItemDao {

	/****
	 * 加载用户的购物车集合
	 */
	public Map<String,String> loadCarItemMap(String uid);
	
	/****
	 * 清空购物车
	 */
	public void deleteCarItemMap(String uid);
}
