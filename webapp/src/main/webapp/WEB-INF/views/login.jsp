<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/import/head.jsp"%>
<body>
<%@include file="/WEB-INF/import/header.jsp"%>
	<div class="container">
		<form class="form-signin" id="formLogin" action="login"
			method='POST'>
			<h2 class="form-signin-heading">Please sign in</h2>
			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>
			<label class="sr-only">User</label> <input type="text"
				id="j_username" name="j_username" class="form-control" placeholder="Login" required
				autofocus> <label class="sr-only">Password</label> <input
				type="password" id="j_password" name="j_password" class="form-control"
				placeholder="Password" required>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>

	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>