package com.bjsxt.ego.search.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.service.ItemService;
import com.bjsxt.ego.search.dao.ItemDao;
import com.bjsxt.ego.search.entity.Item;
import com.bjsxt.ego.search.entity.SearchResult;
import com.bjsxt.ego.search.service.SearchItemService;
@Service
public class SearchItemServiceImpl 
	implements SearchItemService {

	//注入dao对象
	@Autowired
	private ItemDao itemDao;
	
	//调用远程服务的代理对象
	@Autowired
	private ItemService itemServiceProxy;
	@Override
	public SearchResult loadItemService(String item_keywords, Integer page) {
		// TODO Auto-generated method stub
		SolrQuery params = new SolrQuery();
		
		//设置默认查询字段
		params.set("df", "item_keywords");
		
		//设置查询条件
		if(!StringUtils.isEmpty(item_keywords)){
			params.setQuery(item_keywords);
		}else{
			params.set("q", "*:*");
		}
		
		//指定分页参数
		Integer rows=20;
		//进行最小页判断
		if(page<1){
			page=1;
		}
		//进行最大页判断
		Integer maxpage=100;
		if(page>maxpage){
			page=maxpage;
		}
		Integer start=(page-1)*rows;
		params.setStart(start);
		params.setRows(rows);
		
		//设定高亮参数
		params.setHighlight(true);
		params.addHighlightField("title");
		params.setHighlightSimplePre("<font color='red'>");
		params.setHighlightSimplePost("</font>");
		//调用dao方法，进行索引库查询
		QueryResponse response = itemDao.loadItem(params);
		
		//获得本次查询到的文档集合
		SolrDocumentList docList = response.getResults();
		
		//获得本次查询的高亮数据
		Map<String, Map<String, List<String>>> hlts = response.getHighlighting();
		
		//获得本次查询到的总记录数
		long total = docList.getNumFound();
		
		//创建List<Item>对象
		//List<Item> list=new ArrayList<>();
		
		//将docList转化为List<Item>
		DocumentObjectBinder binder=new DocumentObjectBinder();
		List<Item> list=binder.getBeans(Item.class, docList);
		
		for(Item t:list){
			String id = t.getId();
			//获得某个商品信息的高亮数据
			Map<String, List<String>> map = hlts.get(id);
			//获得某个商品的某个字段的高亮数据
			List<String> lts = map.get("title");
			if(lts!=null&&lts.size()>0){
				t.setTitle(lts.get(0));
			}
		}
		
		SearchResult result = new SearchResult();
		result.setMaxpage(Long.parseLong(String.valueOf(maxpage)));
		result.setTotal(total);
		result.setList(list);
		return result;
	}
	@Override
	public TbItem loadItemService(Long id) {
		// TODO Auto-generated method stub
		return itemServiceProxy.loadTbItemById(id);
	}

}
