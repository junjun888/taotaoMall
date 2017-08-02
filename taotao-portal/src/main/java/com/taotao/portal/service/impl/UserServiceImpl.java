package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;

/**
 * 用户管理Service
 * <p>Title: UserServiceImpl</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年8月2日下午9:30:00
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Value("${SSO_BASE_URL}")
	private String SSO_BASE_URL;
	
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;

	@Override
	public TbUser getUserByToken(String token) {
		try {
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
			// 将 json转换成 TaoTaoResult
			TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
			
			if (result.getStatus() == 200) {
				TbUser user = (TbUser) result.getData();
				
				return user;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
 		return null;
	}

}
