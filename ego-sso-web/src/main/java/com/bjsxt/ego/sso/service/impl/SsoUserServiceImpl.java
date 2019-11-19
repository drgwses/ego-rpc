package com.bjsxt.ego.sso.service.impl;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.bjsxt.ego.beans.CookieUtils;
import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.JsonUtils;
import com.bjsxt.ego.rpc.pojo.TbUser;
import com.bjsxt.ego.rpc.service.TbUserService;
import com.bjsxt.ego.sso.service.SsoUserService;

import redis.clients.jedis.JedisCluster;
@Service
public class SsoUserServiceImpl implements SsoUserService {

	//注入远程服务对象
	@Autowired
	private TbUserService  tbUserServiceProxy;
	@Autowired
	private JedisCluster cluster;
	@Override
	public EgoResult loadUserByCondService(String cond, Integer type) {
		// TODO Auto-generated method stub
		return tbUserServiceProxy.loadTbUserByCondService(cond, type);
	}
	@Override
	public EgoResult saveUserService(TbUser tbUser) {
		// TODO Auto-generated method stub
		String pwd=tbUser.getPassword();
		String md5 = DigestUtils.md5DigestAsHex(pwd.getBytes());
		tbUser.setPassword(md5);
		return tbUserServiceProxy.saveUserTbService(tbUser);
	}
	@Override
	public EgoResult selectUser(String username, String password
			,HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		EgoResult result=new EgoResult();
		
		TbUser tbUser = tbUserServiceProxy.selectUserByUserName(username);
		if(tbUser!=null){
			//对前端提交的密码进行加密
			password=DigestUtils.md5DigestAsHex(password.getBytes());
			if(password.equals(tbUser.getPassword())){
				//将当前登录用户对象，转化为json字符串，保存到redis数据
				String jsonStr = JsonUtils.objectToJson(tbUser);
				String token=UUID.randomUUID().toString();
				//将用户信息保存到redis数据库
				cluster.set(token, jsonStr);
				cluster.expire(token, 1800);
				
				//将token响应到客户端
				CookieUtils.setCookie(request, response, "sso_token", token);
				
				result.setStatus(200);
				result.setMsg("ok");
				result.setData(token);
				
				
				return result;
			}
		}
		
		result.setStatus(400);
		result.setMsg("error");
		result.setData(null);
		return result;
	}
	@Override
	public EgoResult loadUserByToken(String token) {
		// TODO Auto-generated method stub
		//创建EgoResult对象
		EgoResult result=new EgoResult();
		
		String jsonStr = cluster.get(token);
		if(!StringUtils.isEmpty(jsonStr)){
			result.setStatus(200);
			result.setMsg("ok");
			
			//result.setData(jsonStr);
			//将jsonStr转化为TbUser对象
			TbUser tbuser = JsonUtils.jsonToPojo(jsonStr, TbUser.class);
			result.setData(tbuser);
			return result;
		}
		result.setStatus(400);
		result.setMsg("error");
		result.setData(null);
		return result;
	}
	@Override
	public EgoResult deleteUserByToken(String token) {
		// TODO Auto-generated method stub
		//创建EgoResult对象
		EgoResult result=new EgoResult();
		//删除redis数据
		Long del = cluster.del(token);
		if(!del.equals(0L)){
			result.setStatus(200);
			result.setMsg("ok");
			result.setData("");
			return result;
		}
		result.setStatus(400);
		result.setMsg("error");
		result.setData(null);	
		return result;
	}

}
