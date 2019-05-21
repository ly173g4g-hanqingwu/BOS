package com.ayit.service;

import com.ayit.beans.Decidedzone;
import com.ayit.utils.PageBean;

public interface IDecidedzoneService {

	void add(Decidedzone model);

	void pageQuery(PageBean pageBean);

	

}
