package com.funing.commonfn.service;

import net.sf.json.JSONObject;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.funing.commonfn.model.User;
import com.funing.commonfn.util.JsonResult;

import java.util.Map;


public interface UserService extends BaseService<Integer, User> {
	public Map<String, Object> login(JSONObject data, String ip) throws Exception;
	public TextMessage TestConnection() ;

	User logout(JSONObject data);

	Map<String,Object> getUser(JSONObject data);
}