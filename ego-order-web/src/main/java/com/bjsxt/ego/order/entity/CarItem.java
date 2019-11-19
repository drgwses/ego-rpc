package com.bjsxt.ego.order.entity;

import java.io.Serializable;

import com.bjsxt.ego.rpc.pojo.TbItem;

/****
 * 购物车类
 * @author Administrator
 *
 */
public class CarItem implements Serializable{

	private TbItem item; //购买商品对象
	private Integer num; //购买商品的数量
	public TbItem getItem() {
		return item;
	}
	public void setItem(TbItem item) {
		this.item = item;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	
	
}
