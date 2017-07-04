package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTPTest {
	
	@Test
	public void testFtpClient() throws Exception{
		// 创建一个FtpClient对象
		FTPClient ftpClient = new FTPClient();
		// 创建 Ftp连接， 默认是 21 端口
		ftpClient.connect("192.168.253.128", 21);
		
		// 登录 Ftp服务器 ， 使用用户名和密码
		ftpClient.login("123", "123");
		System.out.println(ftpClient.getReplyCode());
		// 上传文件
		// 读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\jun\\Desktop\\test.jpg"));
		// 设置上传路径
		ftpClient.changeWorkingDirectory("/home/123/www/images");
		// 修改上传文件的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		// 第一个参数 服务端文档名
		// 第二个参数 上传文档的inputStream
		ftpClient.storeFile("hello1.jsp", inputStream);
		// 关闭连接
		ftpClient.logout(); 
	}
}