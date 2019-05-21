package com.ayit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ayit.beans.Region;
import com.ayit.dao.base.BaseDaoImpl;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

	@Override
	public List<Region> findListByQ(String q) {
		System.out.println("q ==========" +q);
		String hql = "From Region r where r.province like ? or r.city like ?  or r.district like ?  or r.postcode like ?  or r.shortcode like ? or r.citycode like ?";
		List<Region> find = (List<Region>) this.getHibernateTemplate().find(hql, "%"+q+"%", "%"+q+"%", "%"+q+"%", "%"+q+"%", "%"+q+"%", "%"+q+"%");
		return find;
	
	}

}
