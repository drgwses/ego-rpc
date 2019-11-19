package com.bjsxt.ego.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

/*****
 * 完成商品信息的检索
 * @author Administrator
 *
 */
public interface ItemDao {

	/***
	 * 完成商品索引库数据检索
	 * @param params 封装检索条件
	 * @return
	 */
	public QueryResponse loadItem(SolrQuery params);
}
