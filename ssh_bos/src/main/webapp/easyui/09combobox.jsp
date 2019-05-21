<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Layout</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
	
</head>
<body>
	<!-- 传统的方式  缺点是数据只能事先写好 -->
<!--  
	 <select class="easyui-combobox">
	 	<option>小红</option>
	 	<option>小明</option>
	 	<option>小黑</option>
	 	<option>小白</option>
	 </select>    
-->
	<input data-options="url:'${pageContext.request.contextPath }/json/combobox.json',
	valueField:'id',
	textField:'name',
	editable:false" 
	
	class="easyui-combobox">


	
</body>
</html>