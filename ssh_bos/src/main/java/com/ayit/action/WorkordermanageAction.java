package com.ayit.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ayit.action.base.BaseAction;
import com.ayit.beans.Workordermanage;
import com.ayit.service.IWorkordermanageService;


@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {

	@Autowired
	private IWorkordermanageService workordermanageService;
	
	public String add(){
		workordermanageService.add(model);
		return NONE;
	}
	
}
