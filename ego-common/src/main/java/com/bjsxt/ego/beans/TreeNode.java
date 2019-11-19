package com.bjsxt.ego.beans;

/**
 * 封装tree控件需要的数据模型
 * */
public class TreeNode {

	private Long id;
	private String text;
	private String state;  //status
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
