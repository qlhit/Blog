<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客类别管理页面</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	function deleteBlogType(){
			  var selectedRows=$("#dg").datagrid("getSelections");
				if(selectedRows.length==0){
					$.messager.alert("系统提示","请选择要删除的数据！");
					return;
				}
				var strIds=[];
				for(var i=0;i<selectedRows.length;i++){
					strIds.push(selectedRows[i].id);
				}
				var ids=strIds.join(",");
				$.messager.confirm("系统提示","您确定要删除这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
					if(r){
						$.post("${pageContext.request.contextPath}/admin/blogType/delete.do",{ids:ids},function(result){
							if(result.success){
								$.messager.alert("系统提示","数据已成功删除！");
								$("#dg").datagrid("reload");
							}else{
								$.messager.alert("系统提示","数据删除失败！");
							}
						},"json");
					}
				});
		  }
	 function openBlogTypeAddTab(){
		  window.parent.openTab('博客类别添加','addBlogType.jsp','icon-add');
		  }
	 function searchBlogType(){
		  alert($("#s_typeName").val());
			  $("#dg").datagrid('load',{
					"typeName":$("#s_typeName").val()
				});
		  }
	  function openBlogTypeModifyTab(){
	  var selectedRows=$("#dg").datagrid("getSelections");
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一个要修改的链接！");
			return;
		}
		var row=selectedRows[0];
		window.parent.openTab('博客类型修改','modifyBlogType.jsp?id='+row.id,'icon-writeblog');
	 }
	
</script>
</head>
<body style="margin: 1px">
<table id="dg" title="博客类别管理" class="easyui-datagrid" 
  fitColumns="true" pagination="true" rownumbers="true"
  url="${pageContext.request.contextPath}/admin/blogType/list.do" fit="true" toolbar="#tb">
  <thead>
  	<tr>
  		<th field="cb" checkbox="true" align="center"></th>
  		<th field="id" width="20" align="center">编号</th>
  		<th field="typeName" width="100" align="center">博客类型名称</th>
  		<th field="orderNo" width="100" align="center">排序序号</th>
  	</tr>
  </thead>
</table>
<div id="tb">
	<div>
		<a href="javascript:openBlogTypeAddTab()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		<a href="javascript:openBlogTypeModifyTab()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		<a href="javascript:deleteBlogType()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	</div>
	<div>
			<input type="text" style="position:relative;margin-left:5px;" id="s_typeName" placeholder="请输入查询的博客类别..." size="21" onkeydown="if(event.keyCode==13) searchBlogType()"/>
			<a href="javascript:searchBlogType()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
</div>
</body>
</html>