package com.ayit.service;

import java.util.List;

import com.ayit.beans.User;
import com.ayit.utils.PageBean;

public interface IUserService {

	User login(User model);

	void editPassword(String id, String password);

	void save(User model, String[] roleIds);

	public void pageQuery(PageBean pageBean);
	
}
