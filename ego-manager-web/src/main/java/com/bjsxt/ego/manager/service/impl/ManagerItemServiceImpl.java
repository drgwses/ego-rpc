package com.bjsxt.ego.manager.service.impl;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.FtpUtils;
import com.bjsxt.ego.beans.IDUtils;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.beans.PictureResult;
import com.bjsxt.ego.manager.service.ManagerItemService;
import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.pojo.TbItemDesc;
import com.bjsxt.ego.rpc.pojo.TbItemParamItem;
import com.bjsxt.ego.rpc.service.ItemService;
@Service
public class ManagerItemServiceImpl implements ManagerItemService {

	//注入的是远程服务的代理对象
	@Autowired
	private ItemService itemServiceProxy;
	
	
	//通过spring的EL表达式注入ftp信息
	@Value("${FTP_HOST}")
	private String FTP_HOST;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_PATH}")
	private String FTP_PATH;
	@Value("${IMAGE_HTTP_PATH}")
	private String IMAGE_HTTP_PATH;
	
	
	@Override
	public PageResult<TbItem> selectItemListService(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		return itemServiceProxy.selectItemList(page, rows);
	}
	@Override
	public EgoResult reshelfItem(Long[] ids) {
		// TODO Auto-generated method stub
		//将ids数组转化为List集合
		List<Long> itemIds = Arrays.asList(ids);
		//调用远程服务
		return itemServiceProxy.updateItemStatus(itemIds, true);
	}
	@Override
	public EgoResult instockItem(Long[] ids) {
		// TODO Auto-generated method stub
		//将ids数组转化为List集合
		List<Long> itemIds = Arrays.asList(ids);
		return itemServiceProxy.updateItemStatus(itemIds, false);
	}
	@Override
	public EgoResult delteItemService(Long[] ids) {
		// TODO Auto-generated method stub
		List<Long> itemIds = Arrays.asList(ids);
		//调用远程删除商品信息的服务
		return itemServiceProxy.deleteItem(itemIds);
		 
	}
	@Override
	public PictureResult uploadItemPic(MultipartFile file) {
		// TODO Auto-generated method stub
		boolean flag=false;
		String fileName=null;
		try{

			//获得信息的文件名字
			fileName=IDUtils.genImageName();
			//获得上传的文件的原始名字
			String oriName = file.getOriginalFilename();
			//获得文件扩展名
			String ext=oriName.substring(oriName.lastIndexOf("."));
			
			fileName=fileName+ext;
			
			InputStream local = file.getInputStream();
			
			//实现文件上传到ftp
			flag=FtpUtils.uploadFile(FTP_HOST, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, 
					FTP_PATH, fileName, local);
			
			
		}catch(Exception ex){
			ex.printStackTrace();
			flag=false;
		}
		
		PictureResult result=null;
		if(flag){
			result=new PictureResult();
			result.setError(0);
			result.setUrl(IMAGE_HTTP_PATH+"/"+fileName);
			result.setMessage("ok");
		}else{
			result=new PictureResult();
			result.setError(1);
			result.setUrl("url");
			result.setMessage("error");
		}
		return result;
	}
	@Override
	public EgoResult saveItemService(TbItem item, String desc,String paramData) {
		// TODO Auto-generated method stub
		
		Date date = new Date();
		
		//自己产生商品的id，满足后期的分开分表的需求
		Long id=IDUtils.genItemId();
		
		//给item对象封装数据
		item.setId(id);
		item.setStatus((byte) 1);
		item.setCreated(date);
		item.setUpdated(date);
		
		//创建TbItemDesc对象
		TbItemDesc tbItemDesc=new TbItemDesc();
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setItemId(id);
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		
		TbItemParamItem itemParamItem=new TbItemParamItem();
		itemParamItem.setItemId(id);
		itemParamItem.setParamData(paramData);
		itemParamItem.setCreated(date);
		itemParamItem.setUpdated(date);
		//调用远程服务，实现商品信息的发布
		return itemServiceProxy.saveItem(item, tbItemDesc,itemParamItem);
		  
	}
	@Override
	public EgoResult updateItemService(TbItem item, String desc,String paramData) {
		// TODO Auto-generated method stub
		Date date = new Date();
		 
		
		//给item对象封装数据
		item.setUpdated(date);
		
		//创建TbItemDesc对象
		TbItemDesc tbItemDesc=new TbItemDesc();
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setItemId(item.getId());
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		
		//创建
		TbItemParamItem tbItemParamItem=new TbItemParamItem();
		tbItemParamItem.setParamData(paramData);
		tbItemParamItem.setItemId(item.getId());
		tbItemParamItem.setUpdated(date);
		
		//调用远程服务，实现商品信息的发布
		return itemServiceProxy.updatetem(item, tbItemDesc,tbItemParamItem);
	}

}
