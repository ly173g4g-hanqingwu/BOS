package com.ayit.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayit.beans.Function;
import com.ayit.beans.User;
import com.ayit.dao.IFunctionDao;
import com.ayit.utils.BOSUtils;
import com.ayit.utils.PageBean;

@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService{

	@Autowired
	private IFunctionDao dao;
	@Override
	public List<Function> findAll() {
		
		List<Function> list = dao.selectAll();
		return list;
	}
	@Override
	public void save(Function model) {
		
		Function parentFunction = model.getParentFunction();
		if(parentFunction != null && parentFunction.getId().equals("")){
			model.setParentFunction(null);
		}
		dao.save(model);
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		dao.pageQuery(pageBean);
			
	}
	@Override
	public List<Function> findMenu() {
		/*User user = BOSUtils.getLoginUser();
		List<Function> list = null;
		if(user.getUsername().equals("admin")){
			list = dao.findAllMenu();
		}else{
			list = dao.findMenuByUserId(user.getId());
		}
		return list;*/
		List<Function> list = null;
		User user = BOSUtils.getLoginUser();
		if(user.getUsername().equals("admin")){
			//如果是超级管理员内置用户，查询所有菜单
			list = dao.findAllMenu();
		}else{
			//其他用户，根据用户id查询菜单
			list = dao.findMenuByUserId(user.getId());
		}
		return list;
		
		
	}

	
		
}
