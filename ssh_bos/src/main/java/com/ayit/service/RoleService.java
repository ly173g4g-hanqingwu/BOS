package com.ayit.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayit.beans.Function;
import com.ayit.beans.Role;
import com.ayit.dao.IRoleDao;
import com.ayit.utils.PageBean;

@Service
@Transactional
public class RoleService implements IRoleService {

	@Autowired
	private IRoleDao roleDao;

	@Override
	public void save(Role model, String functionids) {
		
		roleDao.save(model);
		if(StringUtils.isNotBlank(functionids)){
			String[] ids = functionids.split(",");
			for (String functionid : ids) {
				Function function = new Function(functionid);
				model.getFunctions().add(function);
			}
			
		}
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		roleDao.pageQuery(pageBean);
	}

	@Override
	public List<Role> findAll() {
		return roleDao.selectAll();
	}
		
	
}
