package com.bjsxt.ego.item.service;

import java.util.Map;

import com.bjsxt.ego.item.entity.CarItem;

public interface CarItemService {

	/***
	 * 将商品放入购物车
	 * @param itemid 商品id
	 * @param uid  用户的id
	 */
	public void addItemToCarService(Long itemid,Long uid);
	
	/****
	 * 加载用户购物车列表
	 * @param uid
	 * @return
	 */
	public Map<Long,CarItem> loadCarItemListService(Long uid);
	
	/***
	 * 修改购物车数量
	 * @param itemid
	 * @param uid
	 * @return
	 */
	public String updateCarItemNumService(Long itemid,Long uid,Integer num);
	
	/****
	 * 删除用户的某个商品对应的购物车
	 * @param itemid
	 * @param uid
	 */
	public void deleteCarItemService(Long itemid,Long uid);
	/****
	 * 清空用户购物车
	 * @param uid
	 */
	public void deleteCarItemAllService(Long uid);
}
