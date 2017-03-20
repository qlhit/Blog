<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>评论信息管理页面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">

	  function formatContent(val,row){
	   	return "<a target='_blank' style='text-decoration: none;' href='${pageContext.request.contextPath}/blog/articles/"+row.blog.id+".html'>"+val+"</a>";
	  }
      function formatBlogId(val,row){
      	return val.id;
      }
	  function openCommModifyTab(){
			window.parent.openTab('评论审核','commentReview.jsp','icon-review');
	  }
	  function deleteComm(){
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
					$.post("${pageContext.request.contextPath}/admin/comment/delete.do",{ids:ids},function(result){
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
	  
	  function searchComm(){
	  alert($("#s_Content").val());
		  $("#dg").datagrid('load',{
				"content":$("#s_Content").val()
			});
	  }
	  
	  function formatState(val,row){
		if(val==0){
			return "待审核";
		}else if(val==1){
			return "审核通过";
		}else if(val==2){
			return "审核未通过";
		}
	}
	</script>
  </head>
  <body style="margin: 1px">
   <table id="dg" title="评论信息管理" class="easyui-datagrid" 
           fitColumns="true" pagination="true" rownumbers="true" 
           url="${pageContext.request.contextPath}/admin/comment/listAdmin.do" fit="true" toolbar="#tb">
      <thead>
        <tr>
  		<th field="cb" checkbox="true" align="center"></th>
  		<th field="id" width="50" align="center">编号</th>
  		<th field="userIp" width="100" align="center">博客IP</th>
  		<th field="blog" width="50" align="center" formatter="formatBlogId">博文ID</th>
  		<th field="content" width="200" align="center" formatter="formatContent">评论内容</th>
  		<th field="commentDate" width="100" align="center">评论日期</th>
  		<th field="state" width="50" align="center" formatter="formatState">状态</th>
      </thead>
      <div id="tb">
		<div>
		    <a href="javascript:openCommModifyTab()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteComm()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			<input type="text" id="s_Content" style="position:relative;margin-left:5px;" size="21" placeholder="请输入查询的评论内容..." onkeydown="if(event.keyCode==13) searchComm()"/>
			<a href="javascript:searchComm()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
    </div>
  </body>
</html>
