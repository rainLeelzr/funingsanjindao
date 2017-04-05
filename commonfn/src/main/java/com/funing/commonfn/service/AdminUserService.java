package com.funing.commonfn.service;

import javax.servlet.http.HttpServletRequest;

import com.funing.commonfn.model.AdminUser;
import com.funing.commonfn.util.JsonResult;

public interface AdminUserService extends BaseService<Integer, AdminUser> {

	JsonResult Login(String passport, String password, HttpServletRequest request)  throws Exception;

	JsonResult saveOrUpdate(AdminUser admin);
}