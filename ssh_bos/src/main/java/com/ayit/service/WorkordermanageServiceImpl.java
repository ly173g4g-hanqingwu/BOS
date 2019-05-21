package com.ayit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayit.beans.Workordermanage;
import com.ayit.dao.IWorkordermanageDao;

@Service
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService {
	
	@Autowired
	private IWorkordermanageDao workordermanageDao;

	@Override
	public void add(Workordermanage model) {
		workordermanageDao.save(model);
	}
	
	
}
