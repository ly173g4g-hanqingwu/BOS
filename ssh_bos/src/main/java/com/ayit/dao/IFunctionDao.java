package com.ayit.dao;

import java.util.List;

import com.ayit.beans.Function;
import com.ayit.dao.base.IBaseDao;

public interface IFunctionDao extends IBaseDao<Function> {

	public List<Function> findFunctionListByUserId(String id);

	public List<Function> findAllMenu();

	public List<Function> findMenuByUserId(String id);
	
}
