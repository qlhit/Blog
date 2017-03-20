<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
    <title>博客类别添加页面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	
		function submitData(){
			var typeName=$("#typeName").val();
			var orderNo=$("#orderNo").val();
			if(typeName==null || typeName==''){
				alert("请输入博客类别名称！");
			}else if(orderNo==null || orderNo==''){
				alert("请填写博客类别排序号！");
			}else{
			alert(typeName);
				$.post("${pageContext.request.contextPath}/admin/blogType/save.do",{'typeName':typeName,'orderNo':orderNo},
				function(result){
					if(result.success){
						alert("博客类别添加成功！");
						window.location.reload();
					}else{
						alert("博客类别添加失败！");
					}
				},"json");
			}
		}
	</script>

  </head>
  
  <body>
    <body style="margin: 10px">
    <div id="p" class="easyui-panel" title="添加博客类别" style="padding: 10px">
    <table cellspacing="20px">
		<tr>
			<td width="105px">博客类别名称:</td>
			<td>
				<input type="text" id="typeName" name="typeName"" style="width: 400px"/>
			</td>
		</tr>
		<tr>
			<td width="105px">博客类别序号:</td>
			<td>
				<input type="text" id="orderNo" name="orderNo"" style="width: 123px"/>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<a href="javascript:submitData()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">添加博客类别</a>
			</td>
		</tr>
  </body>
</html>
