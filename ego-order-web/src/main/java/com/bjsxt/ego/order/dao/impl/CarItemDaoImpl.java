package com.bjsxt.ego.order.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bjsxt.ego.order.dao.CarItemDao;

import redis.clients.jedis.JedisCluster;
@Repository
public class CarItemDaoImpl implements CarItemDao {

	//注入JedisCluster对象
	@Autowired
	private JedisCluster cluster;
	@Override
	public Map<String, String> loadCarItemMap(String uid) {
		// TODO Auto-generated method stub
		return cluster.hgetAll(uid);
	}

	@Override
	public void deleteCarItemMap(String uid) {
		// TODO Auto-generated method stub
		cluster.del(uid);
	}

}
