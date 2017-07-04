package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.json.JSONUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;

/**
 * 上传图片处理
 * <p>Title: PictureController</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年6月28日下午9:58:49
 * @version 1.0
 */
@Controller
public class PictureController {
	
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody//相当于 responseWrint写回一个json对象
	public String pictureUpload(MultipartFile uploadFile) {
		@SuppressWarnings("rawtypes")
		Map result = pictureService.uploadPicture(uploadFile);
		// 为了保证浏览器兼容， 需要把result 转换成json
		String json = JsonUtils.objectToJson(result);
		
		return json;
	}
}