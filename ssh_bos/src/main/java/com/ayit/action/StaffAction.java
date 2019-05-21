package com.ayit.action;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ayit.action.base.BaseAction;
import com.ayit.beans.Staff;
import com.ayit.beans.User;
import com.ayit.service.IStaffService;
import com.ayit.service.IUserService;
import com.ayit.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

	@Autowired
	private IStaffService staffService;
	
	private int page;
	private int rows;
	private String ids;
	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	/**
	 * 添加收派员
	 */
	public String add(){
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		
		staffService.add(model);
		
		return LIST;
	}
	
	public String pageQuery() throws IOException{
	
		
		staffService.pageQuery(pageBean);
		this.Java2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize","decidedzones"});
	
		
		return NONE;
	}
	
	@RequiresPermissions("delete-list")
	public String deleteBatch(){
		staffService.deleteBatch(ids);
		return LIST;
	}
	
	public String edit(){
		Staff staff = staffService.selectById(model.getId());
		staff.setName(model.getName());
		staff.setTelephone(model.getTelephone());
		staff.setDecidedzones(model.getDecidedzones());
		staff.setDeltag(model.getDeltag());
		staff.setHaspda(model.getHaspda());
		staff.setStation(model.getStation());
		staff.setStandard(model.getStandard());
		staffService.update1(staff);
		return LIST;
		
	}
	
	public String listajax(){
		List<Staff> list = (List<Staff>) staffService.findStaffNoDelete();
		this.Java2Json(list, new String[]{"decidedzones"});
		return NONE ;
	}
	
}
