package com.ayit.service;

import java.util.List;

import com.ayit.beans.Role;
import com.ayit.utils.PageBean;

public interface IRoleService {

	public void save(Role model, String functionids);

	public void pageQuery(PageBean pageBean);

	public List<Role> findAll();

}
