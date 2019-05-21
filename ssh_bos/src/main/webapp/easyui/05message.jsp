<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Message</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		$(function(){
			//提示框
		
		/* $.messager.alert('提示信息','哈哈哈','warning'); */
		//确认框
	/* 	$.messager.confirm('提示信息', '你确定离开系统么?', function(r){
			if (r){ 
				alert(r)
			} }); 
		}) */
		//欢迎框
		$.messager.show({  	
  			title:'欢迎你',  	
  			msg:'美好的一天从现在开始',  	
 			 timeout:3000,  	
  			showType:'show'  
		});
		})
	</script>
</head>
<body >
	
</body>
</html>