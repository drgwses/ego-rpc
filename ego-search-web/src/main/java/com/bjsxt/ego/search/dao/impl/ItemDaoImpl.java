package com.bjsxt.ego.search.dao.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bjsxt.ego.search.dao.ItemDao;

@Repository
public class ItemDaoImpl implements ItemDao{

	
	//注入solr集群的访问对象
	@Autowired
	private CloudSolrServer solrServer;

	@Override
	public QueryResponse loadItem(SolrQuery params) {
		// TODO Auto-generated method stub
		try {
			return solrServer.query(params);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
