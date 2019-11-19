package com.bjsxt.ego.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bjsxt.ego.beans.BigPicture;
import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.portal.service.PortalContentService;
import com.bjsxt.ego.rpc.pojo.TbContent;
import com.bjsxt.ego.rpc.service.TbContentService;

import redis.clients.jedis.JedisCluster;

@Service
public class PortalContentServiceImpl implements PortalContentService {

	@Value("${CONTENT_PICTURE}")
	private String contentPictureKey;
	
	//注入JedisCluster
	@Autowired
	private JedisCluster cluster;
	
	@Autowired
	private TbContentService tbContentServiceProxy;
	@Override
	public String loadContentListByCidService(Long cid) {
		// TODO Auto-generated method stub
		
		//查询redis数据库
		String jsonStr = cluster.get(contentPictureKey);
		if(!StringUtils.isEmpty(jsonStr)){
			return jsonStr;
		}
		//调用远程服务
		List<TbContent> list = tbContentServiceProxy.loadTbContentListByCidService(cid);
		//封装前台数据格式的广告数据
		List<BigPicture> bigList=new ArrayList<>();
		
		for(TbContent content:list){
			BigPicture pic = new BigPicture();
			
			pic.setSrcb(content.getPic());
			pic.setHeight(240);
			pic.setAlt(content.getTitle());
			pic.setWidth(670);
			pic.setSrc(content.getPic2());
			pic.setWidthb(550);
			pic.setHref(content.getUrl());
			pic.setHeightb(240);
			
			bigList.add(pic);
		}
		//将符合前提数据格式规范的list集合，序列化为json字符串
		String str = JsonUtils.objectToJson(bigList);
		
		//将str保存到redis缓存
		cluster.set(contentPictureKey, str);
		cluster.expire(contentPictureKey, 86400);
		return str;
	}

}
