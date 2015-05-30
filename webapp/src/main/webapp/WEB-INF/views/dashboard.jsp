<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/import/head.jsp"%>
<body>
	<%@include file="/WEB-INF/import/header.jsp"%>
	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				<c:choose>
					<c:when test="${pagined.getNbItems()  == 0}">
						<spring:message code="dashboard.homeTitleEmpty" />
					</c:when>
					<c:when test="${pagined.getNbItems()  == 1}">
						<spring:message code="dashboard.homeTitleAlone" />
					</c:when>
					<c:otherwise>
						<spring:message code="dashboard.homeTitle"
							arguments="${pagined.getNbItems() }" />
					</c:otherwise>
				</c:choose>
			</h1>
			<c:if test="${success != null}">
				<div class="alert alert-success" role="alert">${success}</div>
			</c:if>
			<c:if test="${danger != null}">
				<div class="alert alert-danger" role="alert">${danger}</div>
			</c:if>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form:form id="searchForm" action="#" method="GET"
						class="form-inline">
						<input type="search" id="searchbox" name="search"
							class="form-control"
							placeholder="<spring:message code='dashboard.searchName'/>"
							value="<c:out value='${pagined.getSearch()}'/>" />
						<input type="submit" id="searchsubmit"
							value="<spring:message code='dashboard.filterByName'/>"
							class="btn btn-primary" />
					</form:form>
				</div>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<div class="pull-right">
						<spring:message code='global.edit' var="editMessage" />
						<spring:message code='global.view' var="viewMessage" />
						<a class="btn btn-success" id="addComputer" href="addComputer"><spring:message
								code="dashboard.addComputer" /></a> <a class="btn btn-default"
							id="editComputer" href="#"
							onclick="$.fn.toggleEditMode('${editMessage}', '${viewMessage}');"><spring:message
								code="global.edit" /></a>
					</div>
				</sec:authorize>
			</div>
		</div>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<form:form id="deleteForm" action="#" method="POST">
				<input type="hidden" name="selection" value="">
			</form:form>
		</sec:authorize>
		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<spring:message code="dashboard.confirmDeletion"
								var="confirmDeletionMessage" />
							<spring:message code="global.ok" var="okMessage" />
							<spring:message code="global.cancel" var="cancelMessage" />
							<!-- Variable declarations for passing labels as parameters -->
							<!-- Table header for Computer Name -->
							<th class="editMode" style="width: 60px; height: 22px;"><input
								type="checkbox" id="selectall" /> <span
								style="vertical-align: top;"> - <a href="#"
									id="deleteSelected"
									onclick="$.fn.deleteSelected('${confirmDeletionMessage}', '${okMessage}', '${cancelMessage}');">
										<i class="fa fa-trash-o fa-lg"></i>
								</a>
							</span></th>
						</sec:authorize>
						<th><spring:message code="global.computerName" /> <mylib:caret
								name="name" orderBy="${pagined.getObName()}"
								asc="${pagined.isAsc()}" search="${pagined.getSearch()}"
								page="${pagined.getPage()}" limit="${pagined.getLimit()}" /></th>
						<th><spring:message code="global.introducedDate" /> <mylib:caret
								name="introduced" orderBy="${pagined.getObName()}"
								asc="${pagined.isAsc()}" search="${pagined.getSearch()}"
								page="${pagined.getPage()}" limit="${pagined.getLimit()}" /></th>
						<!-- Table header for Discontinued Date -->
						<th><spring:message code="global.discontinuedDate" /> <mylib:caret
								name="discontinued" orderBy="${pagined.getObName()}"
								asc="${pagined.isAsc()}" search="${pagined.getSearch()}"
								page="${pagined.getPage()}" limit="${pagined.getLimit()}" /></th>
						<!-- Table header for Company -->
						<th><spring:message code="global.company" /> <mylib:caret
								name="company" orderBy="${pagined.getObName()}"
								asc="${pagined.isAsc()}" search="${pagined.getSearch()}"
								page="${pagined.getPage()}" limit="${pagined.getLimit()}" /></th>
					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${pagined.getPagination()}" var="computer">
						<tr>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<td class="editMode"><input type="checkbox" name="cb"
									class="cb" value="${computer.getId()}"></td>
							</sec:authorize>
							<td><sec:authorize access="hasRole('ROLE_ADMIN')">
									<a href="editComputer?id=${computer.getId()}" onclick=""><c:out
											value='${computer.getName()}' /></a>
								</sec:authorize> <sec:authorize access="not hasRole('ROLE_ADMIN')">
									<c:out value='${computer.getName()}' />
								</sec:authorize></td>
							<td>${computer.getIntroduced()}</td>
							<td>${computer.getDiscontinued()}</td>
							<td>${computer.getCompanyName()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
	<footer class="navbar-fixed-bottom">
		<mylib:page begin="${pagined.getFirstPage()}"
			end="${pagined.getLastPage()}" search="${pagined.getSearch()}"
			current="${pagined.getPage()}" limit="${pagined.getLimit()}"
			pagemax="${pagined.getPageMax()}" orderBy="${pagined.getObName()}"
			asc="${pagined.isAsc()}" />
	</footer>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>
</body>
</html>
