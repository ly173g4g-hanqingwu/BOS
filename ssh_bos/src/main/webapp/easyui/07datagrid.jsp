<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>datagrid</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<!-- 静态表格使用datagrid样式 -->
	<table class="easyui-datagrid">
		<thead>
			<tr>
				<th data-options="field:'id'">编号</th>
				<th data-options="field:'name'">姓名</th>
				<th data-options="field:'age'">年龄</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>001</td>
				<td>小白</td>
				<td>10</td>
			</tr>
			<tr>
				<td>002</td>
				<td>小红</td>
				<td>11</td>
			</tr>
			<tr>
				<td>003</td>
				<td>小黑</td>
				<td>12</td>
			</tr>
		</tbody>
	</table>
	<!-- 通过ajax请求获取json数据动态创建datagrid表格-->
	<table data-options="url:'${pageContext.request.contextPath }/json/datagrid_data.json'" class="easyui-datagrid">
		<thead>
			<tr>
				<th data-options="field:'id'">编号</th>
				<th data-options="field:'name'">姓名</th>
				<th data-options="field:'age'">年龄</th>
			</tr>
		</thead>
		<tbody>
			
		</tbody>
	</table>
	<!-- 通过easyui提供的api创建datagrid表格-->
	<hr>
	<script type="text/javascript">
		$(function(){
			$("#mytable").datagrid({
				columns:[[
					{title:'编号',field:'id',checkbox:true},
					{title:'姓名',field:'name'},
					{title:'年龄',field:'age'}
				]],
				url:'${pageContext.request.contextPath }/json/datagrid_data.json',
				singleSelect:true,
				rownumbers:true,
				toolbar:[
					{text:'添加',iconCls:'icon-add',handler: function(){
						alert('add')
						}
					},
					{text:'删除',iconCls:'icon-remove'},
					{text:'修改',iconCls:'icon-edit'},
					{text:'查询',iconCls:'icon-search'}
				],
				pagination:true,
			
				
			})
		})
	</script>
	
	<table id="mytable">

	</table>
	
	
	
</body>
</html>