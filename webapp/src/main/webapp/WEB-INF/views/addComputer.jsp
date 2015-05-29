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
					<h1>
						<spring:message code="addComputer.title" />
					</h1>
					<c:if test="${success != null}">
						<div class="alert alert-success" role="alert">
							<spring:message code="${success}" arguments="${computerName}" var="successMessage" />
							<c:out value='${successMessage }'/>
						</div>
					</c:if>
					<c:if test="${danger != null}">
						<div class="alert alert-danger" role="alert">
							<spring:message code="${danger}" />
						</div>
					</c:if>
					<form:form action="addComputer" method="POST"
						modelAttribute="computer">
						<fieldset>
							<div
								class="form-group <c:if
										test="${errors.hasFieldErrors('name')}">has-error</c:if>">
								<form:label path="computerName">
									<spring:message code="global.computerName" />
								</form:label>
								<spring:message code="global.computerName"
									var="computerNameMessage" />
								<form:input path="computerName" type="text" class="form-control"
									id="computerName" name="computerName"
									placeholder="${computerNameMessage}" value="${computer.name}"
									required="true" />
								<span class="help-block"><spring:message code="global.required" /><br /> <form:errors
										path="computerName" /></span>
							</div>
							<div
								class="form-group <c:if
										test="${errors.hasFieldErrors('introduced')}">has-error</c:if>">
								<form:label path="introduced">
									<spring:message code="global.introducedDate" />
								</form:label>
								<spring:message code="global.introducedDate"
									var="introducedDateMessage" />
								<form:input path="introduced" type="date" class="form-control"
									id="introduced" name="introduced"
									placeholder="${introducedDateMessage}"
									value="${computer.introduced}" />
								<span class="help-block"><form:errors path="introduced" />
								</span>
							</div>
							<div
								class="form-group <c:if
										test="${errors.hasFieldErrors('discontinued')}">has-error</c:if>">
								<form:label path="discontinued">
									<spring:message code="global.discontinuedDate" />
								</form:label>
								<spring:message code="global.discontinuedDate"
									var="discontinuedDateMessage" />
								<form:input path="discontinued" type="date" class="form-control"
									id="discontinued" name="discontinued"
									placeholder='${discontinuedDateMessage}'
									value="${computer.discontinued}" />
								<span class="help-block"><form:errors path="discontinued" />
								</span>
							</div>
							<div
								class="form-group <c:if
										test="${errors.hasFieldErrors('company')}">has-error</c:if>">
								<form:label path="companyId">
									<spring:message code="global.company" />
								</form:label>
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
							<spring:message code="global.add" var="addMessage" />
							<input type="submit" value="${addMessage}"
								class="btn btn-success">
							<spring:message code="global.or" />
							<a href="dashboard" class="btn btn-default"><spring:message
									code="global.cancel" /></a>
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