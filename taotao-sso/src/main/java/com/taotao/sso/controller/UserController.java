package com.taotao.sso.controller;

import java.awt.PageAttributes.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;

/**
 * 用户 Controller
 * <p>Title: UserController</p>
 * <p>Description: </p>
 * @author	黄文俊
 * @date	2017年7月31日下午9:35:34
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService uservice;
	
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {
		// 参数验证
		TaotaoResult result = null;
		
		if (StringUtils.isBlank(param)) {
			result = TaotaoResult.build(400, "校验内容不为空");
		} 
		
		if (type == null) {
			result = TaotaoResult.build(400, "校验类型不为空");
		}
		
		if (type != 1 && type !=2 && type != 3) {
			result = TaotaoResult.build(500, "校验类型错误");
		}
		
		if (result != null) {
			if (callback != null) {
				MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
				mappingJacksonValue.setJsonpFunction(callback);
				
				return mappingJacksonValue;
			} else {
				return result;
			}
		} else {

		}
		
		try {
			result = uservice.checkData(param, type); 
			 
			
		} catch (Exception e) {
			result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		if (callback != null) {
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
			mappingJacksonValue.setJsonpFunction(callback);
			
			return mappingJacksonValue;
		} else {
			return result;
		}
	}
	
	/**
	 * 创建用户
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public TaotaoResult createUser(TbUser user) {
		try {
			TaotaoResult result = uservice.createUser(user);
			
			return result;
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	/**
	 * 用户登录
	 */
	@RequestMapping(value="/login")
	@ResponseBody
	public TaotaoResult userLogin(String username, String password,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			TaotaoResult result = uservice.userLogin(username, password, request, response);
			
			return result;
		} catch (Exception e) {
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	@RequestMapping("/token/{token}")
	public Object getUserByToken(@PathVariable String token, String callback) {
		TaotaoResult taotaoResult;
		
		try {
			taotaoResult = uservice.getUserByToken(token);
		} catch (Exception e) {
			taotaoResult = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		//判断是否是 jsonp 所调用
		if (StringUtils.isBlank(callback)) {
			return taotaoResult;
		} else {
			// 代表是 jsonp 所调用
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(taotaoResult);
			mappingJacksonValue.setJsonpFunction(callback);
			
			return mappingJacksonValue;
		}
	}
}