package com.bjsxt.ego.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.manager.service.ManagerParamItemService;
import com.bjsxt.ego.rpc.pojo.TbItemParamItem;
import com.bjsxt.ego.rpc.service.ParamItemService;
@Service
public class ManagerParamItemServiceImpl implements ManagerParamItemService {

	@Autowired
	private ParamItemService paramItemServiceProxy;
	@Override
	public EgoResult loadParamItemService(Long itemid) {
		// TODO Auto-generated method stub
		EgoResult result=null;
		try {
			//调用远程服务
			TbItemParamItem tbItemparamItem = 
					paramItemServiceProxy.loadTbItemParamItemService(itemid);
			
			result = new EgoResult();
			result.setStatus(200);
			result.setData(tbItemparamItem);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}
