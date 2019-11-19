package com.bjsxt.ego.manager.service;

import org.springframework.web.multipart.MultipartFile;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.beans.PictureResult;
import com.bjsxt.ego.rpc.pojo.TbItem;

public interface ManagerItemService {
	
	/**
	 * 完成商品信息的分页查询
	 * **/
	public PageResult<TbItem> selectItemListService(Integer page ,Integer rows);
	

	/**
	 * 商品信息的上架
	 * @param ids
	 * @return
	 */
	public EgoResult reshelfItem(Long[] ids);
	
	/**
	 * 商品信息的下架
	 * @param ids
	 * @return
	 */
	public EgoResult instockItem(Long[] ids);
	
	/**
	 * 删除商品信息
	 * @param ids
	 * @return
	 */
	public EgoResult delteItemService(Long[] ids);
	
	/**
	 * 完成商品图片的上传
	 * **/
	public PictureResult uploadItemPic(MultipartFile file);
	
	/**
	 * 完成商品详细信息的发布
	 * @param item 商品对象
	 * @param desc 商品描述
	 * @return
	 */
	public EgoResult saveItemService(TbItem item,String desc,String paramData);
	
	/**
	 * 完成商品详细信息的更新
	 * @param item 商品对象
	 * @param desc 商品描述
	 * @return
	 */
	public EgoResult updateItemService(TbItem item,String desc,String paramData);
}
