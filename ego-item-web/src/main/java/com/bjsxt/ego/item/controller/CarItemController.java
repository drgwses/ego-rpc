package com.bjsxt.ego.item.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjsxt.ego.item.entity.CarItem;
import com.bjsxt.ego.item.service.CarItemService;
import com.bjsxt.ego.rpc.pojo.TbUser;
 

@Controller
public class CarItemController {

    @Autowired
    private CarItemService carItemService;
	/****
	 * 处理将商品添加到购物车的请求
	 */
	@RequestMapping("/cart/add/{itemid}")
	public String cartAdd(@PathVariable Long itemid,
			HttpServletRequest request){
		TbUser user=(TbUser) request.getAttribute("user");
		carItemService.addItemToCarService(itemid, user.getId());
		return "cartSuccess";
	}
	
	
	/*****
	 * 处理加载用户购物车集合的请求
	 */
	@RequestMapping("/cart/cart")
	public String loadCarItemList(HttpServletRequest request){
		
		TbUser user=(TbUser)request.getAttribute("user");
		Long uid=user.getId();
		Map<Long, CarItem> carMap = carItemService.loadCarItemListService(uid);
		request.setAttribute("carMap",carMap);
		
		return "cart";
		
	}
	
	/****
	 * 处理更新购物车数量的请求
	 */
	@RequestMapping("/cart/update/num/{itemid}/{num}")
	@ResponseBody
	public String cartUpdateNum(@PathVariable Long itemid,
			@PathVariable Integer num,HttpServletRequest request){
		TbUser user=(TbUser)request.getAttribute("user");
		Long uid=user.getId();
		return carItemService.updateCarItemNumService(itemid, uid, num);
	}
	
	/****
	 * 处理删除商品购物车的请求
	 */
	@RequestMapping("/cart/delete/{itemid}")
	public String cartDelete(@PathVariable Long itemid,HttpServletRequest request){
		TbUser user=(TbUser)request.getAttribute("user");
		Long uid=user.getId();
		
		carItemService.deleteCarItemService(itemid, uid);
		
		return "redirect:/cart/cart.html";
	}
	/****
	 * 处理清空用户购物车请求
	 */
	@RequestMapping("/delete/cart/all")
	public String deleteCartAll(HttpServletRequest request){
		TbUser user = (TbUser) request.getAttribute("user");
		Long id=user.getId();
		carItemService.deleteCarItemAllService(id);
		
		return "redirect:/cart/cart.html";
	}
	
	
}
