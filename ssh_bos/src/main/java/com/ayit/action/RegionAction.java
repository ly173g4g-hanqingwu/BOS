package com.ayit.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ayit.action.base.BaseAction;
import com.ayit.beans.Region;
import com.ayit.beans.Staff;
import com.ayit.service.IRegionService;
import com.ayit.utils.PageBean;
import com.ayit.utils.PinYin4jUtils;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
	
	private File regionFile;
	@Autowired
	private IRegionService regionService;


	public void setRegionService(IRegionService regionService) {
		this.regionService = regionService;
	}

	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}
	//导入功能
	public String importXls() throws FileNotFoundException, IOException{
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(regionFile));//创建有数据表格
		HSSFSheet sheet = workbook.getSheetAt(0);//取了sheet1
		List<Region> regionList = new ArrayList<Region>();
		for (Row row : sheet) {
			int num = row.getRowNum();
			if(num ==  0){
				continue;
			}else{
				String id = row.getCell(0).getStringCellValue();
				String province = row.getCell(1).getStringCellValue();
				String city= row.getCell(2).getStringCellValue();
				String district = row.getCell(3).getStringCellValue();
				String postcode = row.getCell(4).getStringCellValue();
				Region region = new Region(id,province,city,district,postcode,null,null,null);
				
				province = province.substring(0, province.length()-1);
				city = city.substring(0, city.length()-1);
				district = district.substring(0, district.length()-1);
				String str = province + city + district;
				String[] str1 = PinYin4jUtils.getHeadByString(str);
				String shortcode = StringUtils.join(str1);
				String citycode = PinYin4jUtils.hanziToPinyin(city,"");
				region.setShortcode(shortcode);
				region.setCitycode(citycode);
				regionList.add(region);
				System.out.println(region);
			}
		}
		regionService.saveOrUpdate(regionList);
		return NONE;
	}
	
	public String pageQuery() throws IOException{
		regionService.pageQuery(pageBean);
		this.Java2Json(pageBean, new String[]{"subareas","currentPage","detachedCriteria","pageSize"});
		return NONE;
	}
	/**
	 * 使数据回显到页面
	 * @return
	 */
	private String q;
	
	public void setQ(String q) {
		this.q = q;
	}

	public String listajax(){
		List<Region> list = null;
		if(StringUtils.isNotBlank(q)){
			list = regionService.findListByQ(q);
		}else{
			list = regionService.findAll();
		}
		this.Java2Json(list, new String[]{"subareas","province","city","district","postcode","shortcode","citycode"});
		return NONE;
	}
	
	
}
