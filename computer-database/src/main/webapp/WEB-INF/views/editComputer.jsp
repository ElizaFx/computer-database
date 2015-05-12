<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/import/head.jsp"%>
<body>
	<%@include file="/WEB-INF/import/header.jsp"%>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1>Edit Computer</h1>
					<c:if test="${success != null}">
						<div class="alert alert-success" role="alert">
							<spring:message code="${success}" arguments="${computer.name}" />
						</div>
					</c:if>
					<c:if test="${danger != null}">
						<div class="alert alert-danger" role="alert">
							<spring:message code="${danger}" />
						</div>
					</c:if>
					<form:form action="editComputer?id=${computer.id}" method="POST"
						modelAttribute="computer">
						<form:input path="id" id="id" type="hidden" value="${computer.id}" />
						<fieldset>
							<div
								class="form-group <c:if
										test="${errors.hasFieldErrors('name')}">has-error</c:if>">
								<form:label path="computerName">Computer name</form:label>
								<form:input path="computerName" type="text" class="form-control"
									id="computerName" name="computerName"
									placeholder="Computer name" value="${computer.name}"
									required="true" />
								<span class="help-block">Required <br /> <form:errors
										path="computerName" /></span>
							</div>
							<div
								class="form-group <c:if
										test="${errors.hasFieldErrors('introduced')}">has-error</c:if>">
								<form:label path="introduced">Introduced date</form:label>
								<form:input path="introduced" type="date" class="form-control"
									id="introduced" name="introduced" placeholder="Introduced date"
									value="${computer.introduced}" />
								<span class="help-block">Pattern YYYY-MM-dd or
									dd-MM-YYYY. Delimiters can be - or . or / <br /> <form:errors
										path="introduced" />
								</span>
							</div>
							<div
								class="form-group <c:if
										test="${errors.hasFieldErrors('discontinued')}">has-error</c:if>">
								<form:label path="discontinued">Discontinued date</form:label>
								<form:input path="discontinued" type="date" class="form-control"
									id="discontinued" name="discontinued"
									placeholder="Discontinued date"
									value="${computer.discontinued}" />
								<span class="help-block">Pattern YYYY-MM-dd or
									dd-MM-YYYY. Delimiters can be - or . or / <br /> <form:errors
										path="discontinued" />
								</span>
							</div>
							<div
								class="form-group <c:if
										test="${errors.hasFieldErrors('company')}">has-error</c:if>">
								<form:label path="companyId">Company</form:label>
								<form:select path="companyId" class="form-control"
									id="companyId" name="companyId">
									<form:option value="0" selected='true'>--</form:option>
									<c:forEach items="${lCompanies}" var="company">
										<option value="${company.id}"
											${computer.companyId == company.id ? 'selected' : '' }>${company.name }</option>
									</c:forEach>
								</form:select>
								<br />
								<form:errors class="help-block" path="companyId" />
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/computerValidator.js"></script>
</body>
</html>