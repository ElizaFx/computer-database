<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/import/head.jsp" %>
<body>
<%@include file="/WEB-INF/import/header.jsp" %>

	<section id="main">
		<div class="container">
			<div class="alert alert-danger">
				Error 404: Page not found. <br />
				<!-- stacktrace -->
				<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
					<p>${trace}</p>
				</c:forEach>
			</div>
		</div>
	</section>

	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/dashboard.js"></script>

</body>
</html>