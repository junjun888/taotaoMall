package com.taotao.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.aspectj.util.FileUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;

import sun.net.ftp.FtpClient;

/**
 * 图片上传服务
 * <p>Title: PictureImpl</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年6月28日下午9:07:15
 * @version 1.0
 */
@Service
public class PictureImpl implements PictureService{
	
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	
	@Value("${FTP_BASE_PTAH}")
	private String FTP_BASE_PTAH;
	
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;

	@Override
	public Map uploadPicture(MultipartFile uploadFile) {
		Map resultMap = new HashMap<>();
		try {
			// 取原始文件名
			String oldName = uploadFile.getOriginalFilename();
			// 生成新的文件名
			String newName = IDUtils.genImageName();
			newName = newName + oldName.substring(oldName.lastIndexOf("."));
			// 图片上传
			String imagePath = new DateTime().toString("/yyyy/MM/dd");
			boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME.trim(), FTP_PASSWORD.trim(), FTP_BASE_PTAH,
					imagePath, newName, uploadFile.getInputStream());
			
			if (!result) {
				resultMap.put("error", 1);
				resultMap.put("message", "文件上传失败");
				
				return resultMap;
			} else {
				resultMap.put("error", 0);
				resultMap.put("url", IMAGE_BASE_URL + imagePath + "/" + newName);
				
				return resultMap;
			}
		} catch (IOException e) {
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传发生异常");
			
			return resultMap;
		}
	}
	
}