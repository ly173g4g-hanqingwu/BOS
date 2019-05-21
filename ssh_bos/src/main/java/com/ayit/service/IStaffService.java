package com.ayit.service;

import java.util.List;

import com.ayit.beans.Staff;
import com.ayit.utils.PageBean;

public interface IStaffService {

	void add(Staff model);

	void pageQuery(PageBean pageBean);

	void deleteBatch(String ids);

	

	void update1(Staff staff);

	Staff selectById(String id);

	List<Staff> findStaffNoDelete();

	
	
}
