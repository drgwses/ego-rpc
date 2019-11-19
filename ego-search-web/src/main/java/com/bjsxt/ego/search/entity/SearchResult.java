package com.bjsxt.ego.search.entity;

import java.util.List;

/****
 * 封装响应到前台数据模型
 * @author Administrator
 *
 */
public class SearchResult {

	private List<Item> list;  //查询到商品集合
	private Long maxpage; //最大页
	private Long total;   //查询到的商品总数
	public List<Item> getList() {
		return list;
	}
	public void setList(List<Item> list) {
		this.list = list;
	}
	public Long getMaxpage() {
		return maxpage;
	}
	public void setMaxpage(Long maxpage) {
		this.maxpage = maxpage;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
	
}
