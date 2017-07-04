package com.taotao.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;


/**
 * 图片上传接口
 * <p>Title: PictureService</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年6月28日下午9:03:33
 * @version 1.0
 */
public interface PictureService {
	Map uploadPicture(MultipartFile uploadFile);
}