package com.ayit.dao;

import com.ayit.beans.User;
import com.ayit.dao.base.IBaseDao;

public interface IUserDao extends IBaseDao<User> {

	public User findUserByNameAndPassword(String username,String password);

	public User findUserByName(String username);

	

}

