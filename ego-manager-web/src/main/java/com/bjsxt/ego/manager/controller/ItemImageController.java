package com.bjsxt.ego.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bjsxt.ego.beans.PictureResult;
import com.bjsxt.ego.manager.service.ManagerItemService;

@Controller
public class ItemImageController {

	//注入service
	@Autowired
	private ManagerItemService managerItemService;
	
	/**
	 * 处理图片上传请求
	 * ***/
	@RequestMapping(value="pic/upload",produces=MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
	@ResponseBody
	public PictureResult picUpload(MultipartFile uploadFile){
		return managerItemService.uploadItemPic(uploadFile);
	}
}
