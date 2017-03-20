<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/blog.css">
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
<title>${pageTitle}-Powered by 七弦僧</title>
<style type="text/css">
	body{
		padding-top: 10px;
		padding-bottom: 40px;
		background:url(${pageContext.request.contextPath}/static/images/back.png);
	}
</style>
</head>
<body id="blog_title">
   <div class="container" >
   <div class="row" style="padding-top:15px;background-color:white;">  
			<jsp:include page="/foreground/common/head.jsp"/>
    </div>  
	<jsp:include page="/foreground/common/menu.jsp"/>
	<div class="row" >
	  <div class="col-md-3">
	  		<div class="data_list">
		  		<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/user_icon.png"/><font>博主信息</font>
				</div>
				<div class="user_info">
					<jsp:include page="/slideimage/image.jsp"/>
				</div>
				<div class="myTime" style="padding-left:15px;">    
	                <script charset="Shift_JIS" src="http://chabudai.sakura.ne.jp/blogparts/honehoneclock/honehone_clock_tr.js"></script>  
	            </div>
	            <a target="_blank" style="padding-left:15px;" href="http://wpa.qq.com/msgrd?v=3&amp;uin=973327685&amp;site=qq&amp;menu=yes"><img src="http://wpa.qq.com/pa?p=2:973327685:41" alt="点击这里给我发消息" title="点击这里给我发消息" data-bd-imgshare-binded="1" border="0"></a>
				<div class="nickName">昵称：${blogger.nickName}</div>
				<div class="userSign">个性签名：${blogger.sign}</div>
			</div>
	  		<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/byType_icon.png"/><font>按日志类别</font>
			    </div>
				<div class="datas">
					<ul>
					
						<c:forEach var="blogTypeCount" items="${blogTypeCountList }">
							<li><span><a href="${pageContext.request.contextPath}/index.html?typeId=${blogTypeCount.id}">${blogTypeCount.typeName }(${blogTypeCount.blogCount })</a></span></li>
						</c:forEach>
						
					</ul>
				</div>
			</div>
			<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/byDate_icon.png"/><font>按日期分类</font>
			    </div>
				<div class="datas">
					<ul>
						<c:forEach var="blogCount" items="${blogCountList }">
							<li><span><a href="${pageContext.request.contextPath}/index.html?releaseDateStr=${blogCount.releaseDateStr}">${blogCount.releaseDateStr}(${blogCount.blogCount})</a></span></li>
						</c:forEach>
						
					</ul>
				</div>
			</div>
			<div class="data_list">
				<div class="data_list_title">
					<img src="${pageContext.request.contextPath}/static/images/link_icon.png"/><font>友情链接</font>
			    </div>
				<div class="datas">
					<ul>
						<c:forEach var="link" items="${linkList }">
							<li><span>${link.linkName }</span></li>
						</c:forEach>
					</ul>
				</div>
			</div>
	  		
	  </div>
	   <div class="col-md-9">
	  	<jsp:include page="${mainPage}"/>
	  </div>
	</div>
	   <jsp:include page="/foreground/common/foot.jsp"/>
</div>
<jsp:include page="/foreground/common/slidedShare.jsp"/> 
 <jsp:include page="/foreground/common/up.jsp"/>
 <script>
        $(function () {
            $('#myCarousel').carousel({
                interval: 3000
            });
        })
</script>

</body>
</html>