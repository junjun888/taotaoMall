package com.taotao.sso.service;

import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {
	
	TaotaoResult checkData(String content, Integer type);
	
	TaotaoResult createUser(TbUser user);
}