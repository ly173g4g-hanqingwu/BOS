package com.ayit.dao;

import java.util.List;

import com.ayit.beans.Region;
import com.ayit.dao.base.IBaseDao;

public interface IRegionDao extends IBaseDao<Region> {

	public List<Region> findListByQ(String q);

	

}
