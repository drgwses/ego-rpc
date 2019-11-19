package com.bjsxt.ego.item.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.item.dao.CarItemDao;
import com.bjsxt.ego.item.entity.CarItem;
import com.bjsxt.ego.item.service.CarItemService;
import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.service.ItemService;

@Service
public class CarItemServiceImpl implements CarItemService {

	//注入dao对象
	@Autowired
	private CarItemDao carItemDao;
	@Autowired
	private ItemService itemServiceProxy;
	@Override
	public void addItemToCarService(Long itemid, Long uid) {
		// TODO Auto-generated method stub
		Map<String,String> carMap=null;
		CarItem carItem=null;
		
		//获得商品对象
		TbItem tbItem = itemServiceProxy.loadTbItemById(itemid);
		
		

		//判断用户是否第一次购物，或者是判断是否已经存在carMap
		carMap = carItemDao.loadCarMap(String.valueOf(uid));
		if(carMap==null){
			//创建Map集合对象
			carMap=new HashMap<>();
		}
		
		
		//判断itemid对象的商品是否存在一个购物车对象
		String carItemStr = carItemDao.loadCarItem(String.valueOf(uid),String.valueOf(itemid));
		if(StringUtils.isEmpty(carItemStr)){
			//创建购物车对象
			carItem=new CarItem();
			//将商品信息放入到购物车
			carItem.setItem(tbItem);
			carItem.setNum(1);
		}else{
			carItem=JsonUtils.jsonToPojo(carItemStr, CarItem.class);
			carItem.setNum(carItem.getNum()+1);//修改购物车数量
		}
		
		//将购物车对象转化为json字符串
		String jsonStr = JsonUtils.objectToJson(carItem);
		//购物车对象放入map集合
		carMap.put(String.valueOf(itemid), jsonStr);
		
		//将carMap集合保存到redis数据库
		carItemDao.addCarMap(String.valueOf(uid), carMap);

	}
	@Override
	public Map<Long, CarItem> loadCarItemListService(Long uid) {
		
		Map<Long, CarItem> carMap=new HashMap<>();
		// 获得某个用户的购物车列表
		Map<String, String> carMapStr = carItemDao.loadCarMap(String.valueOf(uid));
		if(!StringUtils.isEmpty(carMapStr)){
			for(Entry<String,String> e:carMapStr.entrySet()){
				
				String key=e.getKey();
				String value=e.getValue();
				//将value字符串，转化为CarItem对象
				CarItem carItem=JsonUtils.jsonToPojo(value, CarItem.class);
				
				carMap.put(Long.parseLong(key),carItem);
			}
		}
		
		return carMap;
	}
	@Override
	public String updateCarItemNumService(Long itemid, Long uid,Integer num) {
		// TODO Auto-generated method stub
		
		try{
			//获得需要修改的购物车对象
			String carItemStr 
				= carItemDao.loadCarItem(String.valueOf(uid),String.valueOf(itemid));	
			
			//将carItemStr转化为Caritem
			CarItem carItem = JsonUtils.jsonToPojo(carItemStr, CarItem.class);
			//修改购物车数量
			carItem.setNum(num);
			
			//将carItem修改后数据更新到redis数据库
			carItemDao.updateCarMapNum(String.valueOf(uid),
					String.valueOf(itemid), JsonUtils.objectToJson(carItem));
			
			return "ok";
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public void deleteCarItemService(Long itemid, Long uid) {
		// TODO Auto-generated method stub
		carItemDao.deleteCarMapItem(String.valueOf(uid),
				String.valueOf(itemid));
		
	}
	@Override
	public void deleteCarItemAllService(Long uid) {
		// TODO Auto-generated method stub
		carItemDao.deleteCarMapAll(String.valueOf(uid));
	}

}
