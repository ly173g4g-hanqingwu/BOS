package com.ayit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayit.beans.Region;
import com.ayit.dao.IRegionDao;
import com.ayit.utils.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {
	
	@Autowired
	private IRegionDao regionDao;

	public void setRegionDao(IRegionDao regionDao) {
		this.regionDao = regionDao;
	}

	@Override
	public void saveOrUpdate(List<Region> regionList) {
		for (Region region : regionList) {
			regionDao.saveOrUpdate(region);
			
		}
		
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		
		regionDao.pageQuery(pageBean);
	}

	@Override
	public List<Region> findAll() {
		return regionDao.selectAll();
	}

	@Override
	public List<Region> findListByQ(String q) {
		return  regionDao.findListByQ(q);
	}
	
	
	
}
