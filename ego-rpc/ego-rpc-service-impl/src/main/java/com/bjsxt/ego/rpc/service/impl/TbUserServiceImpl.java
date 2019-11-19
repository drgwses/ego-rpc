package com.bjsxt.ego.rpc.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.rpc.mapper.TbUserMapper;
import com.bjsxt.ego.rpc.pojo.TbUser;
import com.bjsxt.ego.rpc.pojo.TbUserExample;
import com.bjsxt.ego.rpc.pojo.TbUserExample.Criteria;
import com.bjsxt.ego.rpc.service.TbUserService;
@Service
public class TbUserServiceImpl implements TbUserService {

	@Autowired
	private TbUserMapper tbUserMappper;
	@Override
	public EgoResult loadTbUserByCondService(String cond, Integer type) {
		// TODO Auto-generated method stub
		TbUserExample example=new TbUserExample();
		Criteria c = example.createCriteria();
		//封装查询条件
		if(type.equals(1)){
			c.andUsernameEqualTo(cond);
		}else if(type.equals(2)){
			c.andPhoneEqualTo(cond);
		}else{
			c.andEmailEqualTo(cond);
		}
		
		List<TbUser> list = tbUserMappper.selectByExample(example);
		
		//创建EgoResult对象
		EgoResult result = new EgoResult();
		result.setStatus(200);
		result.setMsg("ok");
		if(list!=null && list.size()>0)
			 result.setData(false);
		else
			//用户名可用
			result.setData(true);
		return result;
	}
	@Override
	public EgoResult saveUserTbService(TbUser tbUser) {
		// TODO Auto-generated method stub
		EgoResult result = new EgoResult();
		try {
			Date date=new Date();
			tbUser.setCreated(date);
			tbUser.setUpdated(date);
			tbUserMappper.insert(tbUser);
			result.setStatus(200);
			result.setMsg("注册成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setStatus(400);
			result.setMsg("注册失败. 请校验数据后请再提交数据");
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public TbUser selectUserByUserName(String uname) {
		// TODO Auto-generated method stub
		TbUserExample example=new TbUserExample();
		Criteria c = example.createCriteria();
		
		c.andUsernameEqualTo(uname);
		
		List<TbUser> list = tbUserMappper.selectByExample(example);
		if(list!=null&&list.size()==1)
			return list.get(0);
		return null;
	}

}
