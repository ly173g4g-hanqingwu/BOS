package com.ayit.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayit.beans.Decidedzone;
import com.ayit.beans.Noticebill;
import com.ayit.beans.Staff;
import com.ayit.beans.User;
import com.ayit.beans.Workbill;
import com.ayit.client.Customer;
import com.ayit.client.ICustomerService;
import com.ayit.dao.IDecidedzoneDao;
import com.ayit.dao.INoticebillDao;
import com.ayit.dao.IWorkbillDao;
import com.ayit.utils.BOSUtils;

@Service
@Transactional

public class NoticebillServiceImpl implements INoticebillService {

	@Autowired
	private INoticebillDao noticebillDao;
	
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IDecidedzoneDao decidedzoneDao;
	@Autowired
	private IWorkbillDao workbillDao;
	
	@Override
	/**
	 * 保存业务通知单 并且尝试自动分单
	 */
	public void save(Noticebill model) {
		/*//获取当前登录人员
				User user = BOSUtils.getLoginUser();
				model.setUser(user);
				noticebillDao.save(model);
				String address = model.getPickaddress();
				String decidedzoneid = Customerservice.findDecidedzoneIdByAddress(address);
				if (decidedzoneid != null) {
					Decidedzone decidedzone= decidedzoneDao.selectById(decidedzoneid);
					Staff staff = decidedzone.getStaff();
					model.setOrdertype(Noticebill.ORDERTYPE_AUTO);//设置分单类型为自动分单
					Workbill workbill = new Workbill();
					workbill.setAttachbilltimes(0);//追单次数为0
					workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
					workbill.setNoticebill(model);//工单关联页面通知单
					workbill.setPickstate(workbill.PICKSTATE_NO);
					workbill.setType(workbill.TYPE_1);//工单类型为新单
					workbill.setRemark(model.getRemark());
					workbill.setStaff(staff);//业务通知单关联取派员对象
					workbillDao.save(workbill);
				}else{
					model.setOrdertype(Noticebill.ORDERTYPE_MAN);
				}*/
		
		User user = BOSUtils.getLoginUser();
		model.setUser(user);//设置当前登录用户
		noticebillDao.save(model);
		//获取客户的取件地址
		String pickaddress = model.getPickaddress();
		Customer customer = customerService.findCustomerByTelephone(model.getTelephone());
		/*System.out.println(customer);
		//远程调用crm服务，根据取件地址查询定区id
		String decidedzoneId = customerService.findDecidedzoneIdByAddress(pickaddress);*/
		String decidedzoneId = customer.getDecidedzoneId();
		
		if(decidedzoneId != null){
			//查询到了定区id，可以完成自动分单
			Decidedzone decidedzone = decidedzoneDao.selectById(decidedzoneId);
			Staff staff = decidedzone.getStaff();
			model.setStaff(staff);//业务通知单关联取派员对象
			//设置分单类型为：自动分单
			model.setOrdertype(Noticebill.ORDERTYPE_AUTO);
			//为取派员产生一个工单
			Workbill workbill = new Workbill();
			workbill.setAttachbilltimes(0);//追单次数
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//创建时间，当前系统时间
			workbill.setNoticebill(model);//工单关联页面通知单
			workbill.setPickstate(Workbill.PICKSTATE_NO);//取件状态
			workbill.setRemark(model.getRemark());//备注信息
			workbill.setStaff(staff);//工单关联取派员
			workbill.setType(Workbill.TYPE_1);//工单类型
			workbillDao.save(workbill);
			//调用短信平台，发送短信
		}else{
			//没有查询到定区id，不能完成自动分单
			model.setOrdertype(Noticebill.ORDERTYPE_MAN);
		}
		
				
	}

	
}
