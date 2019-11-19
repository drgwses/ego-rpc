package com.bjsxt.ego.rpc.service;

import java.util.List;

import com.bjsxt.ego.beans.EgoResult;
import com.bjsxt.ego.beans.PageResult;
import com.bjsxt.ego.rpc.pojo.TbItem;
import com.bjsxt.ego.rpc.pojo.TbItemDesc;
import com.bjsxt.ego.rpc.pojo.TbItemParamItem;

public interface ItemService {
	
	/**
	 * 实现商品信息的分页查询
	 * **/
	public PageResult<TbItem> selectItemList(Integer page,Integer rows);
	
	/**
	 * 完成商品上下架状态的修改
	 * @param itemIds  商品的id集合
	 * @param flag true:上架，false:下架
	 * @return
	 */
	public EgoResult updateItemStatus(List<Long> itemIds,Boolean flag);
	
	/****
	 * 删除商品信息
	 * @param itemIds  需要删除的商品信息的id集合
	 * @return
	 */
	public EgoResult deleteItem(List<Long> itemIds);
	
	/**
	 * 保存商品信息
	 * @param item
	 * @param desc
	 * @return
	 */
	public EgoResult saveItem(TbItem item,TbItemDesc desc,TbItemParamItem itemParamItem);
	
	/**
	 * 更新商品信息
	 * @param item
	 * @param desc
	 * @return
	 */
	public EgoResult updatetem(TbItem item,TbItemDesc desc,TbItemParamItem itemParamItem);
	
	/****
	 * 根据id。查询商品详细信息
	 * @param id
	 * @return
	 */
	public TbItem loadTbItemById(Long id);

}
