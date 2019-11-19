package com.bjsxt.ego.rpc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.rpc.mapper.TbContentMapper;
import com.bjsxt.ego.rpc.pojo.TbContent;
import com.bjsxt.ego.rpc.pojo.TbContentExample;
import com.bjsxt.ego.rpc.pojo.TbContentExample.Criteria;
import com.bjsxt.ego.rpc.service.TbContentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class TbContentServiceImpl implements TbContentService {

	//注入mapper对象
	@Autowired
	private TbContentMapper tbContentMapper;
	@Override
	public PageResult<TbContent> loadTbContentListService(Long cid,
			Integer page,Integer rows) {
		// TODO Auto-generated method stub
		
		try {
			PageResult<TbContent> result=new PageResult<>();
			
			//指定分页查询参数
			Page pe = PageHelper.startPage(page, rows);
			
			//创建TbContentExample对象
			TbContentExample example=new TbContentExample();
			Criteria c = example.createCriteria();
			c.andCategoryIdEqualTo(cid);
			//查询数据库
			List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
			
			result.setRows(list);
			result.setTotal(pe.getTotal());
			
			return result;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	@Override
	public EgoResult saveTbContentService(TbContent tbContent) {
		// TODO Auto-generated method stub
		try {
			tbContentMapper.insert(tbContent);
			return EgoResult.ok();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	@Override
	public EgoResult deleteTbContentService(List<Long> ids) {
		// TODO Auto-generated method stub
		try{
			//创建TbContentExample对象
			TbContentExample example=new TbContentExample();
			
			Criteria c = example.createCriteria();
			//where id in()
			c.andIdIn(ids);
			tbContentMapper.deleteByExample(example);
			return EgoResult.ok();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public EgoResult updateTbContentService(TbContent tbContent) {
		// TODO Auto-generated method stub
		try {
			tbContentMapper.updateByPrimaryKeySelective(tbContent);
			return EgoResult.ok();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public List<TbContent> loadTbContentListByCidService(Long cid) {
		// TODO Auto-generated method stub
		try {
			TbContentExample example=new TbContentExample();
			//where category_id=?
			Criteria c = example.createCriteria();
			c.andCategoryIdEqualTo(cid);
			List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
