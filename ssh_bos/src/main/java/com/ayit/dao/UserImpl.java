package com.ayit.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ayit.beans.User;
import com.ayit.dao.base.BaseDaoImpl;

@Repository
public class UserImpl extends BaseDaoImpl<User> implements IUserDao {
	/**
	 * 根据用户名和密码查询用户
	 */
	public User findUserByNameAndPassword(String username, String password) {
		String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username,password);
		/*String sql = "select * from t_student";
		List<User> list = (List<User>) this.getHibernateTemplate().find(sql);*/
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public User findUserByName(String username) {
		System.out.println("username = " + username);
		String hql = "from User u where u.username = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username);
		System.out.println("list = " + list.size());
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;

		
	}
	
	
	
}