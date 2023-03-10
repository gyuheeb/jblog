<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% pageContext.setAttribute("newline", "\n"); %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${blogVo.title }</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogVo.title }</h1>
			<ul>
			<c:choose>
			<c:when test="${empty authUser }">
					<li><a href="${pageContext.request.contextPath }/user/login">로그인</a><li>
			</c:when>
			<c:otherwise>
					<li><a href="${pageContext.request.contextPath}/index">로그아웃</a></li>
					<c:if test="${authUser.id == id}">
					<li><a href="${pageContext.request.contextPath}/${id}/blog/admin-basic">블로그 관리</a></li>
					</c:if>
					
			</c:otherwise>
			</c:choose>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${vo.title }</h4>
					<p>
						${vo.contents }
					<p>
				</div>
				<ul class="blog-list">
				<c:set var="count" value="${fn:length(postcatagorylist)} }" />
		      	<c:forEach items="${postcatagorylist }"	var="vo" varStatus="status">
					<li><a href="${pageContext.request.contextPath}/${id}/blog/main/category=${vo.catagory_no}/post=${vo.no}">
					${vo.title} </a> 
					<span>${vo.reg_date}</span>	</li>
				</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.profile}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:set var="count" value="${fn:length(catagorylist)} }" />
		      	<c:forEach items="${catagorylist }"	var="vo" varStatus="status">
						<li><a href="${pageContext.request.contextPath}/${id}/blog/main/category=${vo.no}">${vo.name}</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>