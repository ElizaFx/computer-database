<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand"
			href="${pageContext.request.contextPath}/dashboard">${param.id }<spring:message code="global.title" /></a>
			
			<a class="navbar-right navbar-brand" href="?locale=en"><img alt="english" src="flags/en.svg"></a>
			<a class="navbar-right navbar-brand" href="?locale=fr"><img alt="french" src="flags/fr.svg"></a>
	</div>
</header>