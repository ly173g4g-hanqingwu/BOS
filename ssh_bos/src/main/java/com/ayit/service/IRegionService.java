package com.ayit.service;

import java.util.List;



import com.ayit.beans.Region;
import com.ayit.utils.PageBean;


public interface IRegionService {

	void saveOrUpdate(List<Region> regionList);

	void pageQuery(PageBean pageBean);

	public List<Region> findAll();

	public List<Region> findListByQ(String q);

}
