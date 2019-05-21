package com.ayit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayit.beans.Role;
import com.ayit.beans.User;
import com.ayit.dao.IUserDao;
import com.ayit.utils.MD5Utils;
import com.ayit.utils.PageBean;
@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDao userDao;
	@Override
	public User login(User user) {
		String password = MD5Utils.md5(user.getPassword());
		/*System.out.println("password + "+ password);
		System.out.println(user.getUsername());*/
		
		return userDao.findUserByNameAndPassword(user.getUsername(),password);
	}
	
	public  void editPassword(String id, String password) {
		System.out.println("password = " + password);
		System.out.println("id = " + id);
		
		password = MD5Utils.md5(password);		
		//return userDao.updatePassword(id,password);
		userDao.executeQuery("editPassword", password,id);
		
	}

	@Override
	public void save(User user, String[] roleIds) {
		String pwd = MD5Utils.md5(user.getPassword());
		user.setPassword(pwd);
		userDao.save(user);
		if(roleIds != null && roleIds.length > 0)
		{
			for (String roleId : roleIds) {
				Role role = new Role(roleId);
				user.getRoles().add(role);
			}
			
		}
		
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		userDao.pageQuery(pageBean);
	}

}
