package com.funing.commonfn.dao.impl;

import com.funing.commonfn.dao.UserDao;
import com.funing.commonfn.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<Integer, User> implements UserDao {

}