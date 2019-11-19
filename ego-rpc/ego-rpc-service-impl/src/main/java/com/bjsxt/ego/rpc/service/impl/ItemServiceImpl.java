package com.bjsxt.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.rpc.mapper.TbItemDescMapper;
import com.bjsxt.ego.rpc.mapper.TbItemMapper;
import com.bjsxt.ego.rpc.mapper.TbItemParamItemMapper;
import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.pojo.TbItemDesc;
import com.bjsxt.ego.rpc.pojo.TbItemDescExample;
import com.bjsxt.ego.rpc.pojo.TbItemExample;
import com.bjsxt.ego.rpc.pojo.TbItemExample.Criteria;
import com.bjsxt.ego.rpc.pojo.TbItemParamItem;
import com.bjsxt.ego.rpc.pojo.TbItemParamItemExample;
import com.bjsxt.ego.rpc.service.ItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class ItemServiceImpl implements ItemService {

	//注入mapper接口代理对象
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	@Override
	public PageResult<TbItem> selectItemList(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		
		//执行分页操作
		Page ps = PageHelper.startPage(page, rows);
		
		TbItemExample example=new  TbItemExample();
		
		//执行数据库查询操作
		List<TbItem> list = tbItemMapper.selectByExample(example);
		
		PageResult<TbItem> result = new PageResult<TbItem>();
		
		result.setRows(list);
		result.setTotal(ps.getTotal());
		return result;
	}
	@Override
	public EgoResult updateItemStatus(List<Long> itemIds, Boolean flag) {
		// TODO Auto-generated method stub
		
		//创建TbItem对象
		TbItem item=new TbItem();
		if(flag){
			item.setStatus((byte) 1);
		}else{
			item.setStatus((byte) 2);
		}
		
		//动态产生where条件
		TbItemExample example=new TbItemExample();
		Criteria c = example.createCriteria();
		c.andIdIn(itemIds);
		
		//where id in(3,4,5)
		//tbItemMapper.updateByExample(item, example);
		tbItemMapper.updateByExampleSelective(item, example);
		//where id=?
		//tbItemMapper.updateByPrimaryKey(record)
		return EgoResult.ok();
	}
	@Override
	public EgoResult deleteItem(List<Long> itemIds) {
		// where id in(3,4,5)
		//动态产生where条件
		TbItemExample example= new TbItemExample();
		
		Criteria c = example.createCriteria();
		
		c.andIdIn(itemIds);
		tbItemMapper.deleteByExample(example);
		return EgoResult.ok();
	}
	@Override
	public EgoResult saveItem(TbItem item, TbItemDesc desc,
			TbItemParamItem itemParamItem) {
		// TODO Auto-generated method stub
		
		tbItemMapper.insert(item);
		tbItemDescMapper.insert(desc);
		tbItemParamItemMapper.insert(itemParamItem);
		return EgoResult.ok();
	}
	@Override
	public EgoResult updatetem(TbItem item, TbItemDesc desc,TbItemParamItem itemParamItem) {
		//更新商品基本信息
		this.tbItemMapper.updateByPrimaryKeySelective(item);
		
		TbItemDescExample example=new TbItemDescExample();
		com.bjsxt.ego.rpc.pojo.TbItemDescExample.Criteria c = example.createCriteria();
		c.andItemIdEqualTo(desc.getItemId());
		
		
		
		//where itemId=?
		//查询某个商品对于的描述
		Integer rows=tbItemDescMapper.countByExample(example);
		/**
		 * 判断该商品是否存在描述信息
		 * **/
		if(rows==0){
			this.tbItemDescMapper.insert(desc);
		}else{
			desc.setCreated(null);
			this.tbItemDescMapper.updateByPrimaryKeySelective(desc);
		}
		
		TbItemParamItemExample exam = new TbItemParamItemExample();
		com.bjsxt.ego.rpc.pojo.TbItemParamItemExample.Criteria 
			cp = exam.createCriteria();
	
		//跟新商品规格参数信息的条件
		cp.andItemIdEqualTo(itemParamItem.getItemId());
		
		
		tbItemParamItemMapper.updateByExampleSelective(itemParamItem, exam);
		
		return EgoResult.ok();
	}
	@Override
	public TbItem loadTbItemById(Long id) {
		// TODO Auto-generated method stub
		return tbItemMapper.selectByPrimaryKey(id);
	}

}
