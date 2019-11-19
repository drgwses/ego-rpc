package com.bjsxt.ego.item.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bjsxt.ego.item.dao.CarItemDao;

import redis.clients.jedis.JedisCluster;

@Repository
public class CarItemDaoImpl implements CarItemDao {

	//注入JedisCluster
	@Autowired
	private JedisCluster cluster;
	@Override
	public void addCarMap(String uid, Map<String, String> carMap) {
		// TODO Auto-generated method stub
		cluster.hmset(uid, carMap);

	}
	@Override
	public Map<String, String> loadCarMap(String uid) {
		// TODO Auto-generated method stub
		return cluster.hgetAll(uid);
	}
	@Override
	public String loadCarItem(String uid, String itemid) {
		// TODO Auto-generated method stub
		return cluster.hget(uid, itemid);
	}
	@Override
	public void updateCarMapNum(String uid, String itemid, String carItemStr) {
		// TODO Auto-generated method stub
		cluster.hset(uid,itemid, carItemStr);
	}
	@Override
	public void deleteCarMapItem(String uid, String itemid) {
		// TODO Auto-generated method stub
		cluster.hdel(uid, itemid);
		
	}
	@Override
	public void deleteCarMapAll(String uid) {
		// TODO Auto-generated method stub
		cluster.del(uid);
		
	}

}
