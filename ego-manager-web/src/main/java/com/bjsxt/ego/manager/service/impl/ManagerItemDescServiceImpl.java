package com.bjsxt.ego.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.manager.service.ManagerItemDescService;
import com.bjsxt.ego.rpc.pojo.TbItemDesc;
import com.bjsxt.ego.rpc.service.ItemDescService;

@Service
public class ManagerItemDescServiceImpl implements ManagerItemDescService {

	//注入远程服务的代理对象
	@Autowired
	private ItemDescService itemDescServiceProxy;
	@Override
	public EgoResult getItemDescService(Long itemId) {
		// TODO Auto-generated method stub
		
		//调用远程服务
		TbItemDesc desc=itemDescServiceProxy.getItemDesc(itemId);
		
		if(desc!=null)
			return new EgoResult(desc);
		return new EgoResult(null);
	}

}
