package com.ayit.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ayit.action.base.BaseAction;
import com.ayit.beans.Role;
import com.ayit.service.IRoleService;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	//属性驱动，接收权限的id
	private String functionids;
	@Autowired
	private IRoleService roleService;
	public void setFunctionids(String functionids) {
		this.functionids = functionids;
	}
	public String add(){
		roleService.save(model,functionids);
		return LIST;
	}
	
	public String pageQuery(){
		roleService.pageQuery(pageBean);
		this.Java2Json(pageBean, new String[]{"functions","users"});
		return NONE;
	}
	
	public String listajax(){
		List<Role> list = roleService.findAll();
		this.Java2Json(list,new String[]{"functions","users"});
		return NONE;
		
	}
	
}
