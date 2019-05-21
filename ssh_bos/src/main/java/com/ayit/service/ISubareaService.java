package com.ayit.service;

import java.util.List;

import com.ayit.beans.Staff;
import com.ayit.beans.Subarea;
import com.ayit.utils.PageBean;

public interface ISubareaService {

	public void save(Subarea model);

	public void pageQuery(PageBean pageBean);

	public List<Subarea> findAll();

	public List<Subarea> findListByDecided();

	public List<Subarea> findListByDecidedzoneId(String decidedzoneId);

}

