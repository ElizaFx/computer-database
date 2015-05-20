<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand"
			href="${pageContext.request.contextPath}/dashboard"><spring:message code="global.title" /></a>
			<mylib:lang id="${param.id }"/>
	</div>
</header>