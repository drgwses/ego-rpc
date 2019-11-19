package com.bjsxt.ego.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.rpc.pojo.TbUser;

public interface SsoUserService {
	
	/***
	 * 验证用户名唯一性
	 * @param cond
	 * @param type
	 * @return
	 */
	public EgoResult loadUserByCondService(String cond,Integer type);
	
	
	/***
	 * 实现用户注册
	 * @param tbUser
	 * @return
	 */
	public EgoResult saveUserService(TbUser tbUser);
	
	/****
	 * 实现用户账户验证
	 * @param username
	 * @param password
	 * @return
	 */
	public EgoResult selectUser(String username,String password
			,HttpServletRequest request,HttpServletResponse response);
	
	/***
	 * 获得用户登录状态
	 * @param token
	 * @return
	 */
	public EgoResult loadUserByToken(String token);
	
	/****
	 * 实现用户登录状态的删除
	 */
	public EgoResult deleteUserByToken(String token);

}
