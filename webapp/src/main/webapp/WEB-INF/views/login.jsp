<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/import/head.jsp"%>
<body>
	<%@include file="/WEB-INF/import/header.jsp"%>
	<div class="container" style="max-width: 330px">
		<form class="form-horizontal" id="formLogin" action="login"
			method='POST'>
			<h1 class="form-group">
				<spring:message code="login.homeTitle" />
			</h1>
			<c:if test="${not empty error}">
				<div class="alert alert-danger form-group" role="alert">
					<spring:message code="${error}" />
				</div>
			</c:if>
			<c:if test="${not empty logout}">
				<div class="alert alert-info form-group" role="alert">
					<spring:message code="${logout}" />
				</div>
			</c:if>
			<div class="form-group">
				<label class="sr-only"><spring:message code="login.user" /></label>
				<div class="input-group">
					<span class="input-group-addon" aria-hidden="true"><i
						class="glyphicon glyphicon-user"></i></span><input type="text"
						id="j_username" name="j_username" class="form-control"
						placeholder="<spring:message code="login.login"/>" required
						autofocus>
				</div>
			</div>
			<div class="form-group">
				<label class="sr-only"><spring:message code="login.password" /></label>
				<div class="input-group">
					<span class="input-group-addon" aria-hidden="true"><i
						class="glyphicon glyphicon-lock"></i></span><input type="password"
						id="j_password" name="j_password" class="form-control"
						placeholder="<spring:message code="login.password"/>" required>
				</div>
			</div>
			<div class="form-group">
				<button class="btn btn-lg btn-primary btn-block" type="submit">
					<span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
					<spring:message code="login.signin" />
				</button>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</div>
		</form>

	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>