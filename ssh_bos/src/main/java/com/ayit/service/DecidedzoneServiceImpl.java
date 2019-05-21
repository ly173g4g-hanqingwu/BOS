package com.ayit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayit.beans.Decidedzone;
import com.ayit.dao.IDecidedzoneDao;
import com.ayit.utils.PageBean;

@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {
	
	@Autowired
	private IDecidedzoneDao decidedzoneDao;

	@Override
	public void add(Decidedzone model) {
		decidedzoneDao.save(model);		
	}

	@Override
	public void pageQuery(PageBean pageBean) {
		decidedzoneDao.pageQuery(pageBean);		
	}
	
}
