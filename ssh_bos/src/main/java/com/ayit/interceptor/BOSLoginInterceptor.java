package com.ayit.interceptor;

import org.apache.struts2.ServletActionContext;

import com.ayit.beans.User;
import com.ayit.utils.BOSUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class BOSLoginInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//String user = ServletActionContext.getRequest().getSession().getAttribute("user");
		User user = BOSUtils.getLoginUser();
		if(user == null){
			//如果不存在当前用户，则跳转到登录界面，此处不能再写常量LOGIN，因为这个常量是从actionSupport中继承过来的
			return "login";
		}
		//否则拦截器不工作
		return invocation.invoke();
	}

}
