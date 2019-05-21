package com.ayit.action;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ayit.action.base.BaseAction;
import com.ayit.beans.Decidedzone;
import com.ayit.beans.Noticebill;
import com.ayit.beans.Staff;
import com.ayit.beans.User;
import com.ayit.beans.Workbill;
import com.ayit.client.Customer;
import com.ayit.client.ICustomerService;
import com.ayit.dao.IDecidedzoneDao;
import com.ayit.dao.IWorkbillDao;
import com.ayit.service.INoticebillService;
import com.ayit.utils.BOSUtils;

@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {
	
	@Autowired
	private ICustomerService Customerservice;

	@Autowired
	private INoticebillService noticebillService;
	
	public String findCustomerByTelephone(){
		String telephone = model.getTelephone();
		Customer customer = Customerservice.findCustomerByTelephone(telephone);
		this.Java2Json(customer, new String[]{});
		return NONE;
	}
	
	
	public String add(){
		noticebillService.save(model);
		return "noticebill_add";
	}

	
}
