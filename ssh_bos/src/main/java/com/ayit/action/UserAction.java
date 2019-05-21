package com.ayit.action;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ayit.action.base.BaseAction;
import com.ayit.beans.User;

import com.ayit.service.IUserService;
import com.ayit.service.UserServiceImpl;
import com.ayit.utils.BOSUtils;
import com.ayit.utils.MD5Utils;


@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	@Autowired
	private IUserService service;
	
	
	private String checkcode;

	
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}


	
	public String login(){
		//从Session中获取生成的验证码
		String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		//校验验证码是否输入正确
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)){
			//使用shiro框架提供的方式进行认证操作
			Subject subject = SecurityUtils.getSubject();//获得当前用户对象,状态为“未认证”
			AuthenticationToken token = new UsernamePasswordToken(model.getUsername(),MD5Utils.md5(model.getPassword()));//创建用户名密码令牌对象
			System.out.println("name = " + model.getUsername());
			System.out.println("password = " + MD5Utils.md5(model.getPassword()));
			System.out.println("subject = " + subject);
			try{
				subject.login(token);
			}catch(Exception e){
				e.printStackTrace();
				return LOGIN;
			}
			User user = (User) subject.getPrincipal();
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			return HOME;
		}else{
			//输入的验证码错误,设置提示信息，跳转到登录页面
			this.addActionError("输入的验证码错误！");
			return LOGIN;
		}
	}
	
	/**
	 * 用户登录
	 */
	public String login_bak(){
		//从Session中获取生成的验证码
		String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		//校验验证码是否输入正确
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)){
			//输入的验证码正确
			User user = service.login(model);
			if(user != null){
				//登录成功,将user对象放入session，跳转到首页
				ServletActionContext.getRequest().getSession().setAttribute("user", user);
				return HOME;
			}else{
				//登录失败，,设置提示信息，跳转到登录页面
				//输入的验证码错误,设置提示信息，跳转到登录页面
				this.addActionError("用户名或者密码输入错误！");
				return LOGIN;
			}
		}else{
			//输入的验证码错误,设置提示信息，跳转到登录页面
			this.addActionError("输入的验证码错误！");
			return LOGIN;
		}
	}
	
	/**
	 * 退出系统
	 * @return
	 */
	public String logout(){
		//当点击确定退出按钮之后，使当前session失效
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}

	/**
	 * 修改密码
	 * @throws IOException 
	 */
	public String editPassword() throws IOException{
	
		String flag = "1";
		User user = BOSUtils.getLoginUser();
		try {
			service.editPassword(user.getId(),model.getPassword());
			
		} catch (Exception e) {
			flag = "0";
			e.printStackTrace();
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;
	}
	
	private String[] roleIds;
	
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}


	public String add(){
		
		service.save(model,roleIds);
		return LIST;
	}
	
	public String pageQuery(){
		service.pageQuery(pageBean);
		this.Java2Json(pageBean, new String[]{"noticebills","roles"});
		return NONE;
	}
}
