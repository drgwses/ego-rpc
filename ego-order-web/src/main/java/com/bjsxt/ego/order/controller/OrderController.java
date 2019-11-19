package com.bjsxt.ego.order.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bjsxt.ego.order.entity.CarItem;
import com.bjsxt.ego.order.service.OrderService;
import com.bjsxt.ego.rpc.pojo.TbOrder;
import com.bjsxt.ego.rpc.pojo.TbOrderItem;
import com.bjsxt.ego.rpc.pojo.TbOrderShipping;
import com.bjsxt.ego.rpc.pojo.TbUser;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	/***
	 * 进行页面跳转
	 */
	 @RequestMapping("/{url}")
	 public String loadPage(@PathVariable String url){
		 return url;
	 }
	 
	 /****
	  * 处理跳转到订单确认页面的请求
	  */
	 @RequestMapping("/order/cart")
	 public String orderCart(HttpServletRequest request){
		 //获得当前登录用户对象
		 TbUser user=(TbUser) request.getAttribute("user");
		 
		 Long id=user.getId();
		 
		 Map<Long, CarItem> carMap = orderService.loadCarItemMapService(id);
		 
		 request.setAttribute("carMap",carMap);
		 return "ordercart";
	 }
	 
	 /****
	  * 处理保存订单信息的请求
	  */
	 @RequestMapping("/order/save")
	 public String orderSave(TbOrder tbOrder,
			 TbOrderShipping orderShipping,HttpServletRequest request){
		 //获得当前登录用户对象
		 TbUser user=(TbUser) request.getAttribute("user");
		 
		 Long id=user.getId();
		 
		 Map<String, String> map = orderService.saveOrderService(tbOrder, id, orderShipping);
		 request.setAttribute("itemid",map.get("itemid"));
		 request.setAttribute("total", map.get("total"));
		 return "success";
		 
	 }
	 
	 /****
	  * 处理加载用户订单列表的请求
	  */
	 @RequestMapping("/order/list")
	 public String orderList(HttpServletRequest request){
		//获得当前登录用户对象
		 TbUser user=(TbUser) request.getAttribute("user");
		 
		 Long id=user.getId();
		 
		 List<TbOrder> list = orderService.loadOrderListService(id);
		 
		 request.setAttribute("orderList", list);
		 
		 return "orders";
	 }
	 
	 /****
	  * 处理加载订单明细列表的请求
	  */
	 @RequestMapping("/order/detail/list/{orderid}")
	 public String orderDetailList(@PathVariable String orderid,Model model){
		 List<TbOrderItem> list = orderService.loadOrderItemListService(orderid);
		 model.addAttribute("list", list);
		 
		 return "ordersdetail";
		 
	 }
	 
	
}
