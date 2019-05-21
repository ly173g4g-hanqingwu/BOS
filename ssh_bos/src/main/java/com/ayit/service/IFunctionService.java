package com.ayit.service;

import java.util.List;

import com.ayit.beans.Function;
import com.ayit.utils.PageBean;

public interface IFunctionService {

	public List<Function> findAll();

	public void save(Function model);

	public void pageQuery(PageBean pageBean);

	public List<Function> findMenu();

}
