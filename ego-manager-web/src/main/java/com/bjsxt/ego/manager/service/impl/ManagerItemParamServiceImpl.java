package com.bjsxt.ego.manager.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.manager.service.ManagerItemParamService;
import com.bjsxt.ego.rpc.pojo.TbItemParam;
import com.bjsxt.ego.rpc.service.ItemParamService;

@Service
public class ManagerItemParamServiceImpl 
implements ManagerItemParamService {
	
	//注入服务的远程代理对象
	@Autowired
	private ItemParamService itemParamServiceProxy;

	@Override
	public PageResult<TbItemParam> 
		loadItemParamListService(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return itemParamServiceProxy.loadTbItemParamListService(page, rows);
	}

	@Override
	public EgoResult loadItemParamByCidService(Long cid) {
		// TODO Auto-generated method stub
		EgoResult result=null;
		try{
			
			TbItemParam tbItemParam = 
					itemParamServiceProxy.loadTbItemParamByCidService(cid);
			result=new EgoResult();
			result.setStatus(200);
			result.setData(tbItemParam);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public EgoResult saveItemParamService(Long cid, String paramData) {
		// TODO Auto-generated method stub
		
		TbItemParam tbItemParam=new TbItemParam();
		tbItemParam.setItemCatId(cid);
		tbItemParam.setParamData(paramData);
		tbItemParam.setCreated(new Date());
		tbItemParam.setUpdated(new Date());
		//调用远程服务
		return itemParamServiceProxy.saveTbItemParamService(tbItemParam);
		 
	}

	@Override
	public EgoResult deleteItemParamService(Long[] ids) {
		// TODO Auto-generated method stub
		//将Long类型数组转化为List集合
		List<Long> id = Arrays.asList(ids);
		return itemParamServiceProxy.deleteTbItemParamService(id);
	}

}
