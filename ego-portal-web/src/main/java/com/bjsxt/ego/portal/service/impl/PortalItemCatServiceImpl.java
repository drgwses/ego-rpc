package com.bjsxt.ego.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bjsxt.ego.beans.CatNode;
import com.bjsxt.ego.beans.CatResult;
import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.portal.service.PortalItemCatService;
import com.bjsxt.ego.rpc.pojo.TbItemCat;
import com.bjsxt.ego.rpc.service.ItemCatService;

import redis.clients.jedis.JedisCluster;
@Service
public class PortalItemCatServiceImpl implements PortalItemCatService {

	@Value("${ITEM_CAT}")
	private String itemCatkey;
	//注入JedisCluster对象
	@Autowired
	private JedisCluster cluster;
	
	//注入远程代理对象
	@Autowired
	private   ItemCatService  itemCatServiceProxy;
	@Override
	public String loadItemCatService() {
		
		String jsonStr = cluster.get(itemCatkey);
		if(!StringUtils.isEmpty(jsonStr)){
			return jsonStr;
		}
		
		// TODO Auto-generated method stub
		List<TbItemCat> list = itemCatServiceProxy.loadItemCatListService();
		
		//创建CatResult对象
		CatResult result=new CatResult();
		
		//将List转化为符合前端规范数据格式,递归遍历list
		List<?> data = getChildren(0L,list);
		
		result.setData(data);
		//将result对象序列化json字符串
		String str = JsonUtils.objectToJson(result);
		
		//将str缓存到redis数据库
		cluster.set(itemCatkey, str);
		return str;
	}

	private List<?> getChildren(Long parentId, List<TbItemCat> itemCats) {
		// 盛放指定分类下的所有子分类信息
		List resultList = new ArrayList();

		for (TbItemCat itemCat : itemCats) {

			if (itemCat.getParentId().equals(parentId)) {
				if (itemCat.getIsParent()) {
					// 如果itemCat代表一级分类或者二级分类

					CatNode catNode = new CatNode();

					if (itemCat.getParentId().longValue() == 0) {
						// 如果是一级分类 "<a href='/products/1.html'>图书、音像、电子书刊</a>",
						catNode.setName(
								"<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
					} else {
						// 如果是二级分类 "电子书刊",
						catNode.setName(itemCat.getName());
					}
					// "/products/2.html",
					catNode.setUrl("/products/" + itemCat.getId() + ".html");
					catNode.setList(getChildren(itemCat.getId(), itemCats));
					// 将节点添加到list集合中
					resultList.add(catNode);
				} else {
					// 如果itemCat表示三级分类 "/products/3.html|电子书",
					resultList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());
				}
			}
		}
		return resultList;
	}

}
