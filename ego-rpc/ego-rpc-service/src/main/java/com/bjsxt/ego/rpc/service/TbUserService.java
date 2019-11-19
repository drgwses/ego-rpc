package com.bjsxt.ego.rpc.service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.rpc.pojo.TbUser;

public interface TbUserService {

	/***
	 * 实现用户名唯一性验证
	 * @param cond 验证数据
	 * @param type 验证类型
	 * @return
	 */
	public EgoResult loadTbUserByCondService(String cond,Integer type);
	
	/***
	 * 实现用户注册
	 * @param tbUser
	 * @return
	 */
	public EgoResult saveUserTbService(TbUser tbUser);
	
	
	/***
	 * 进行用户账户的验证
	 * @param uname
	 * @return
	 */
	public TbUser selectUserByUserName(String uname);
	
	 
}
