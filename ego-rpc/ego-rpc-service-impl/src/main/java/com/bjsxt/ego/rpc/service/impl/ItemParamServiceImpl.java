package com.bjsxt.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.rpc.mapper.TbItemParamMapper;
import com.bjsxt.ego.rpc.pojo.TbItemParam;
import com.bjsxt.ego.rpc.pojo.TbItemParamExample;
import com.bjsxt.ego.rpc.pojo.TbItemParamExample.Criteria;
import com.bjsxt.ego.rpc.service.ItemParamService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper tbItemParamMapper;
	@Override
	public PageResult<TbItemParam> loadTbItemParamListService(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		PageResult<TbItemParam>  result=new PageResult<>();
		
		//进行分页参数指定
		Page pe = PageHelper.startPage(page, rows);
		
		TbItemParamExample example=new TbItemParamExample();
		
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		
		result.setRows(list);
		result.setTotal(pe.getTotal());
		
		return result;
	}
	@Override
	public TbItemParam loadTbItemParamByCidService(Long cid) {
		// TODO Auto-generated method stub
		TbItemParamExample example=new TbItemParamExample();
		
		//封装查询条件
		Criteria c = example.createCriteria();
		c.andItemCatIdEqualTo(cid);
		
		 
		
		List<TbItemParam> list = 
				tbItemParamMapper.selectByExampleWithBLOBs(example);
		
		if(list!=null && list.size()==1){
			return list.get(0);
		}
		return null;
	}
	@Override
	public EgoResult saveTbItemParamService(TbItemParam tbItemParam) {
		// TODO Auto-generated method stub
		
		try {
			
			tbItemParamMapper.insert(tbItemParam);
			
		     return EgoResult.ok();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public EgoResult deleteTbItemParamService(List<Long> ids) {
		// TODO Auto-generated method stub
		
		try {
			//创建Example对象
			TbItemParamExample example=new TbItemParamExample();
			
			Criteria c = example.createCriteria();
			
			//封装删除条件  where id in(1,23,3)
			c.andIdIn(ids);  
			tbItemParamMapper.deleteByExample(example);
			
		    return EgoResult.ok();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
 

}
