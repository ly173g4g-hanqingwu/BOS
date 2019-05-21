package com.ayit.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ayit.action.base.BaseAction;
import com.ayit.beans.Function;
import com.ayit.service.IFunctionService;


/**
 * 权限管理
 * @author zhaoqx
 *
 */
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function>{
	@Autowired
	private IFunctionService service;
	/**
	 * 查询所有权限，返回json数据
	 */
	public String listajax(){
		List<Function> list = service.findAll();
		for (Function function : list) {
			System.out.println(function.getName());
		}
		this.Java2Json(list, new String[]{"parentFunction","roles"});
		return NONE;
	}
	
	/**
	 * 添加权限 
	 */
	public String add(){
		service.save(model);
		return LIST;
	}
	
	public String pageQuery(){
		String page = model.getPage();
		pageBean.setCurrentPage(Integer.parseInt(page));
		service.pageQuery(pageBean);
		this.Java2Json(pageBean, new String[]{"parentFunction","roles","children"});
		return NONE;
	}
	
	/**
	 * 根据当前登录人查询对应的菜单数据，返回json
	 */
	public String findMenu(){
		List<Function> list = service.findMenu();
		this.Java2Json(list, new String[]{"parentFunction","roles","children"});
		return NONE;
	}
}
