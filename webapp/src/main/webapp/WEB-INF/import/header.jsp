<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand"
			href="${pageContext.request.contextPath}/dashboard"><spring:message
				code="global.title" /></a>
		<div class="navbar-right">
			<mylib:lang id="${param.id }" />
			<sec:authorize access="not isAnonymous()">
				<form class="navbar-brand" method="post"
					action="<c:url value="/logout" />">
					<button class="btn btn-danger btn-sm" type="submit">
						<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
						<spring:message code="global.logout" />
					</button>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</sec:authorize>
		</div>
	</div>
</header>