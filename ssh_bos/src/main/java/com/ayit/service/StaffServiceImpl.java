package com.ayit.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayit.beans.Staff;
import com.ayit.dao.IStaffDao;
import com.ayit.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

	@Autowired  
	private IStaffDao staffDao;
	@Override
	public void add(Staff staff) {
		//staffDao.add(staff.getName(),staff.getTelephone(),staff.getStation(),staff.getHaspda(), staff.getStandard()); 
		//staffDao.executeQuery("addStaff", staff.getName(),staff.getTelephone(),staff.getStation(),staff.getHaspda(), staff.getStandard());
		staffDao.save(staff);
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}
	@Override
	public void deleteBatch(String ids) {
		if(StringUtils.isNotBlank(ids)){
			String[] staffIds = ids.split(",");
			for (String id : staffIds) {
				staffDao.executeQuery("staffDelete", id);
			}
		}
	}
	

	@Override
	public void update1(Staff staff) {
		staffDao.update(staff);
	}
	@Override
	public Staff selectById(String id) {
		
		return staffDao.selectById(id);
	}
	@Override
	public List<Staff> findStaffNoDelete() {
		DetachedCriteria criteria = DetachedCriteria.forClass(Staff.class);
		criteria.add(Restrictions.eq("deltag", "0"));
		List<Staff> list = staffDao.findByCriteria(criteria);
		return list;
	}
	
	

}
