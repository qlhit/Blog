<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>评论信息审核页面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script>
      function formatContent(val,row){
	   	return "<a target='_blank' style='text-decoration: none;' href='${pageContext.request.contextPath}/blog/articles/"+row.blog.id+".html'>"+val+"</a>";
	  }
      function formatBlogId(val,row){
      	return val.id;
      }
      function commentReview(state){
		var selectedRows=$("#dg").datagrid("getSelections");
		if(selectedRows.length==0){
			 $.messager.alert("系统提示","请选择要审核的评论！");
			 return;
		 }
		 var strIds=[];
		 for(var i=0;i<selectedRows.length;i++){
			 strIds.push(selectedRows[i].id);
		 }
		 var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确定要审核这<font color=red>"+selectedRows.length+"</font>条评论吗？",function(r){
			if(r){
				$.post("${pageContext.request.contextPath}/admin/comment/review.do",{ids:ids,state:state},function(result){
					if(result.success){
						 $.messager.alert("系统提示","审核提交成功！");
						 $("#dg").datagrid("reload");
					}else{
						$.messager.alert("系统提示","审核提交失败！");
					}
				},"json");
			} 
  	   });
	}
      </script>
  </head>
  
  <body style="margin: 1px">
   <table id="dg" title="评论信息审核" class="easyui-datagrid" 
           fitColumns="true" pagination="true" rownumbers="true" 
           url="${pageContext.request.contextPath}/admin/comment/listReview.do" fit="true" toolbar="#tb">
      <thead>
        <tr>
  		<th field="cb" checkbox="true" align="center"></th>
  		<th field="id" width="50" align="center">编号</th>
  		<th field="userIp" width="100" align="center">博客IP</th>
  		<th field="blog" width="50" align="center" formatter="formatBlogId">博文ID</th>
  		<th field="content" width="150" align="center" formatter="formatContent">评论内容</th>
  		<th field="commentDate" width="100" align="center">评论日期</th>
  		<th field="state" width="20" align="center">状态</th>
      </thead>
      </table>
       <div id="tb">
	        <div>
				<a href="javascript:commentReview(1)" class="easyui-linkbutton" iconCls="icon-ok" plain="true">审核通过</a>
				<a href="javascript:commentReview(2)" class="easyui-linkbutton" iconCls="icon-no" plain="true">审核不通过</a>
			</div>
		</div>
  </body>
</html>
