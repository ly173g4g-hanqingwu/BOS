package com.ayit.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ayit.action.base.BaseAction;
import com.ayit.beans.Region;
import com.ayit.beans.Staff;
import com.ayit.beans.Subarea;
import com.ayit.service.ISubareaService;
import com.ayit.utils.FileUtils;
import com.ayit.utils.PageBean;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea>{

	@Autowired
	private ISubareaService subService;
	
	public String addSubarea(){
		subService.save(model);
		return LIST;
	}
	

	public String pageQuery(){
		/*DetachedCriteria dc = pageBean.getDetachedCriteria();
		
		String addresskey = model.getAddresskey();
		if(StringUtils.isNotBlank(addresskey)){
			dc.add(Restrictions.like(addresskey, "%"+addresskey+"%"));
		}
		Region region = model.getRegion();
		
		if(region != null){
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();
			dc.createAlias("region", "r");
			if(StringUtils.isNotBlank(province)){
				dc.add(Restrictions.like("r.province",  "%"+province+"%"));
			}
			if(StringUtils.isNotBlank(city)){
				dc.add(Restrictions.like("r.city",  "%"+city+"%"));
			}
			if(StringUtils.isNotBlank(district)){
				dc.add(Restrictions.like("r.district",  "%"+district+"%"));
			}
		}
		
		
		subService.pageQuery(pageBean);
		this.Java2Json(pageBean, new String[]{"subareas","decidedzone","currentPage","pageSize"});
		
		return NONE;*/
		
		DetachedCriteria dc = pageBean.getDetachedCriteria();
		//动态添加过滤条件
		String addresskey = model.getAddresskey();
		if(StringUtils.isNotBlank(addresskey)){
			//添加过滤条件，根据地址关键字模糊查询
			dc.add(Restrictions.like("addresskey", "%"+addresskey+"%"));
		}
		
		Region region = model.getRegion();
		if(region != null){
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();
			dc.createAlias("region", "r");
			if(StringUtils.isNotBlank(province)){
				//添加过滤条件，根据省份模糊查询-----多表关联查询，使用别名方式实现
				//参数一：分区对象中关联的区域对象属性名称
				//参数二：别名，可以任意
				dc.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			if(StringUtils.isNotBlank(city)){
				//添加过滤条件，根据市模糊查询-----多表关联查询，使用别名方式实现
				//参数一：分区对象中关联的区域对象属性名称
				//参数二：别名，可以任意
				dc.add(Restrictions.like("r.city", "%"+city+"%"));
			}
			if(StringUtils.isNotBlank(district)){
				//添加过滤条件，根据区模糊查询-----多表关联查询，使用别名方式实现
				//参数一：分区对象中关联的区域对象属性名称
				//参数二：别名，可以任意
				dc.add(Restrictions.like("r.district", "%"+district+"%"));
			}
		}
		
		subService.pageQuery(pageBean);
		this.Java2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize",
						"decidedzone","subareas"});
		return NONE;
		
	}
	
	public String exportxls() throws IOException{
		
		List<Subarea> list = subService.findAll();
		
		for (Subarea subarea : list) {
			System.out.println(subarea.getRegion().getName());
		}
		HSSFWorkbook work = new HSSFWorkbook();
		HSSFSheet sheet = work.createSheet("分区数据");
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("开始号");
		headRow.createCell(2).setCellValue("结束号");
		headRow.createCell(3).setCellValue("关键字");
		headRow.createCell(4).setCellValue("省市区");
		
		for(Subarea subarea:list){
			HSSFRow datarow = sheet.createRow(sheet.getLastRowNum() + 1);
			datarow.createCell(0).setCellValue(subarea.getId());
			datarow.createCell(1).setCellValue(subarea.getStartnum());
			datarow.createCell(2).setCellValue(subarea.getEndnum());
			datarow.createCell(3).setCellValue(subarea.getPosition());
			datarow.createCell(4).setCellValue(subarea.getRegion().getName());
		}
		//名字必须是filename ，否则会出错
		String filename = "分区数据.xls";
		//获取文件名的后缀名
		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		ServletActionContext.getResponse().setContentType(contentType);
		//获取浏览器的类型
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
		work.write(out);
		
		
		
		/*String filename = "分区数据.xls";
		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		ServletActionContext.getResponse().setContentType(contentType);
		
		//获取客户端浏览器类型
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);*/
		return NONE;
	}
	
	public String listajax(){
		List<Subarea> list =  subService.findListByDecided();
		this.Java2Json(list, new String[]{"decidedzone","region"});
		return NONE;
	}
	
	//定义属性驱动，接收参数
	private String decidedzoneId;
	
	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}


	public String findListByDecidedzoneId(){
		System.out.println(decidedzoneId);
		List<Subarea> list = subService.findListByDecidedzoneId(decidedzoneId);
		System.out.println(list.size());
		this.Java2Json(list, new String[]{"decidedzone","subareas"});
		return NONE;
	}
	
	
}
