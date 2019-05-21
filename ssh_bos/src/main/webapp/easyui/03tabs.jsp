<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>tabs</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
	     
	<div title="xxx管理系统" style="height: 100px" data-options="region:'north'">北部区域</div>
	<div title="菜单系统" style="width: 200px" data-options="region:'west'">
	<!-- data-options="fit:true" 该句子让其打开后其余的放在底部 -->
		
		<div class="easyui-accordion" data-options="fit:true">
			<div data-options="iconCls:'icon-cut'" title="面板一">
				<a  id = "but1" class="easyui-linkbutton">选项卡一</a>
				<script type="text/javascript">
					$(function(){
						$("#but1").click(function(){
							var e = $("#mytabs").tabs("exists","选项卡一");
							if(e){
								$("#mytabs").tabs("select","选项卡一");
							}
							else{
								$("#mytabs").tabs("add",{
									title:'选项卡一',
									iconCls:'icon-edit',
									closable:true
								});
							}
						});
					});
				</script>
				
			</div>
			<div title="面板二">
				<a id = "but2" class = "easyui-linkbutton">选项卡二</a>
				<script type="text/javascript">
					$(function(){
						$("#but2").click(function(){
							
							var b = $("#mytabs").tabs("exists","选项卡二");
							if(b){
								$("#mytabs").tabs("select","选项卡二");
							}
							else{
								$("#mytabs").tabs("add",{
									title:'选项卡二',
									closable:true
								})
							}
							
						})
					})
				</script>
			</div>
			<div title="面板三">
				
				3333
			</div>
		</div>
	</div>
	<div  data-options="region:'center'">
		<div id="mytabs" class="easyui-tabs" data-options="fit:true">
			<!-- <div data-options="closable:'true'" data-options="iconCls:'icon-cut'" title="选项卡一">111</div>
			<div data-options="closable:'true'" title="选项卡二">222</div>
			<div data-options="closable:'true'" title="选项卡三">333</div> -->
		</div>
	</div>
	<div style="width: 100px" data-options="region:'east'">东部区域</div>
	<div style="height: 50px" data-options="region:'south'">南部区域</div>
	
	
	
</body>
</html>