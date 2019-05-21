package com.ayit.relam;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.ayit.beans.Function;
import com.ayit.beans.User;
import com.ayit.dao.IFunctionDao;
import com.ayit.dao.IUserDao;

public class BOSRealm extends AuthorizingRealm{
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IFunctionDao functionDao;
	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("执行认证方法");
		UsernamePasswordToken mytoken = (UsernamePasswordToken) token;
		String username = mytoken.getUsername();
		System.out.println("username = " + username);
		User user = userDao.findUserByName(username);
		if(user == null){
			//用户不存在
			return null;
		}
		//简单认证信息对象
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		//如果查询到。再由框架比对数据库中查询到的密码和页面提交的密码是否一致
		//AuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
		System.out.println(info);
		return info;
		
		
		
	}

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		//System.out.println("执行授权方法");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//获取当前登录用户
		List<Function> list = null;
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		if(user.getUsername().equals("admin")){
			DetachedCriteria criteria = DetachedCriteria.forClass(Function.class);
			//如果为内置的admin对象，则赋予所有的权限
			list = functionDao.findByCriteria(criteria );
			
		}else{
			String id = user.getId();
			System.out.println(id);
			list = functionDao.findFunctionListByUserId(id);
		}
		for (Function function : list) {
			info.addStringPermission(function.getCode());
		}
		
		return info;
	}
	


}
