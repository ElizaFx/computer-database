<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/import/head.jsp" %>
<body>
<%@include file="/WEB-INF/import/header.jsp" %>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<c:if test="${success != null}">
						<div class="alert alert-success" role="alert">${success}</div>
					</c:if>
					<c:if test="${danger != null}">
						<div class="alert alert-danger" role="alert">${danger}</div>
					</c:if>
					<form action="addComputer" method="POST">
						<fieldset>
							<div class="form-group ${computerNameClass}">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName"
									name="computerName" placeholder="Computer name"
									value="${computerName}" required> <span
									class="help-block">Required <c:if
										test="${computerNameMessage != null}">
										<br />${computerNameMessage}</c:if></span>
							</div>
							<div class="form-group ${introducedClass}">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" id="introduced"
									name="introduced" placeholder="Introduced date"
									value="${introduced}"> <span class="help-block">Pattern
									YYYY-MM-dd or dd-MM-YYYY. Delimiters can be - or . or / <c:if
										test="${introducedMessage != null}">
										<br />${introducedMessage}</c:if>
								</span>
							</div>
							<div class="form-group ${discontinuedClass}">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" id="discontinued"
									name="discontinued" placeholder="Discontinued date"
									value="${discontinued}"> <span class="help-block">Pattern
									YYYY-MM-dd or dd-MM-YYYY. Delimiters can be - or . or / <c:if
										test="${discontinuedMessage != null}">
										<br />${discontinuedMessage}</c:if>
								</span>
							</div>
							<div class="form-group ${companyIdClass}">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<option value="0">--</option>
									<c:forEach items="${lCompanies}" var="company">
										<option value="${company.getId()}"
											${companyId == company.getId() ? "selected" : "" }>${company.getName() }</option>
									</c:forEach>
								</select> <span class="help-block">${companyIdMessage}</span>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/computerValidator.js"></script>
</body>
</html>