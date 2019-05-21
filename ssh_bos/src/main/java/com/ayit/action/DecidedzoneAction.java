package com.ayit.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ayit.action.base.BaseAction;
import com.ayit.beans.Decidedzone;
import com.ayit.client.Customer;
import com.ayit.client.ICustomerService;
import com.ayit.service.IDecidedzoneService;

@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
	@Autowired
	private IDecidedzoneService decidedzoneService;
	
	private List<Integer> customerIds;
	//属性驱动，接收页面提交的多个参数
	
	
	public String add(){
		decidedzoneService.add(model);
		return LIST;
	}
	
	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}
	public String pageQuery(){
		decidedzoneService.pageQuery(pageBean);
		this.Java2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize","decidedzones","subareas"});
		return NONE;
	}
	
	//注入crm客户代理
	@Autowired
	private ICustomerService proxy;
	
	public String findCustomerNotAssociation(){
		List<Customer> list = proxy.findCustomerNotAssociation();
		this.Java2Json(list, new String[]{});
		return NONE;
	}
	
	public String findCustomerAssociation(){
		String decidedzoneid = model.getId();
		System.out.println("decidedzoneid = " +decidedzoneid);
		List<Customer> list = proxy.findCustomerAssociation(decidedzoneid);
		return NONE;
	}
	
	public String assigncustomerstodecidedzone(){
		
		String id = model.getId();
		
		proxy.assigncustomerstodecidedzone(id, customerIds);
		return LIST;
	}
	
}
