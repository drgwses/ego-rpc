package com.bjsxt.ego.item.dao;

import java.util.Map;

/***
 * 访问redis数据的方法
 * @author Administrator
 *
 */
public interface CarItemDao {
	
	
	public void addCarMap(String uid,Map<String,String> carMap);
	
	/****
	 * 查询某个用户对应的购物车集合
	 * @param uid
	 * @return
	 */
	public Map<String,String> loadCarMap(String uid);
	
	/****
	 * 获得某个用户对应的购物车中特定商品对应的购物车对象
	 * @param uid
	 * @param itemid
	 * @return
	 */
	public String loadCarItem(String uid,String itemid);
	
	/****
	 * 修改redis数据库中对应的购物车对象
	 * @param uid
	 * @param itemid
	 * @param carItemStr
	 */
	public void updateCarMapNum(String uid,String itemid,String carItemStr);
	
	/***
	 * 实现商品对应的购物车的删除
	 * @param uid
	 * @param itemid
	 */
	public void deleteCarMapItem(String uid,String itemid);
	
	/***
	 * 清空某个用户购物车
	 * @param uid
	 */
	public void deleteCarMapAll(String uid);

}
