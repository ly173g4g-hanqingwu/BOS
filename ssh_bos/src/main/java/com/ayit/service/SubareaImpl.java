package com.ayit.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayit.beans.Staff;
import com.ayit.beans.Subarea;
import com.ayit.dao.ISubareaDao;
import com.ayit.utils.PageBean;

@Service
@Transactional
public class SubareaImpl implements ISubareaService {

	@Autowired
	private ISubareaDao subareaDao;
	@Override
	public void save(Subarea model) {
		subareaDao.save(model);

	}
	@Override
	public void pageQuery(PageBean pageBean) {
		subareaDao.pageQuery(pageBean);		
	}
	@Override
	public List<Subarea> findAll() {
		List<Subarea> list = subareaDao.selectAll();
		return list;
	}
	@Override
	public List<Subarea> findListByDecided() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Subarea.class);
		criteria.add(Restrictions.isNull("decidedzone"));
		return subareaDao.findByCriteria(criteria);
		
	}
	
	public List<Subarea> findListByDecidedzoneId(String decidedzoneId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Subarea.class);
		criteria.add(Restrictions.eq("decidedzone.id", decidedzoneId));
		return subareaDao.findByCriteria(criteria);
	}
	

}
