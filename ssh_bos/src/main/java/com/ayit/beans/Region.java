package com.ayit.beans;

import java.util.HashSet;
import java.util.Set;

/**
 * 区域
 */

public class Region implements java.io.Serializable {

/*	public static void main(String[] args){
		Region region = new Region("1111","河南省","商丘市","虞城县",null,null,null,null);
		String name = region.getName();
		System.out.println(name);
	}*/
	// Fields

	private String id;
	private String province;
	private String city;
	private String district;
	private String postcode;
	private String shortcode;
	private String citycode;
	private Set subareas = new HashSet(0);

	// Constructors
	
	

	/** default constructor */
	public Region() {
	}

	public Region(String id, String province, String city, String district, String postcode) {
		super();
		this.id = id;
		this.province = province;
		this.city = city;
		this.district = district;
		this.postcode = postcode;
	}

	/** minimal constructor */
	public Region(String id) {
		this.id = id;
	}

	/** full constructor */
	public Region(String id, String province, String city, String district,
			String postcode, String shortcode, String citycode, Set subareas) {
		this.id = id;
		this.province = province;
		this.city = city;
		this.district = district;
		this.postcode = postcode;
		this.shortcode = shortcode;
		this.citycode = citycode;
		this.subareas = subareas;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getShortcode() {
		return this.shortcode;
	}

	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}

	public String getCitycode() {
		return this.citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public Set getSubareas() {
		return this.subareas;
	}

	public void setSubareas(Set subareas) {
		this.subareas = subareas;
	}

	public String getName(){
		return province + city + district;
	}

	
	

	
	
}