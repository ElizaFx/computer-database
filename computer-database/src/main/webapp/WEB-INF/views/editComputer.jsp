<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.getId()}</div>
					<h1>Edit Computer</h1>
					<c:if test="${success != null}">
						<div class="alert alert-success" role="alert">${success}</div>
					</c:if>
					<c:if test="${danger != null}">
						<div class="alert alert-danger" role="alert">${danger}</div>
					</c:if>
					<form action="editComputer?id=${computer.getId()}" method="POST">
						<input type="hidden" value="${computer.getId()}" />
						<fieldset>
							<div class="form-group ${computerNameClass}">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName"
									name="computerName" placeholder="Computer name"
									value="${computer.getName()}" required><span
									class="help-block">Required <c:if
										test="${computerNameMessage != null}">
										<br />${computerNameMessage}</c:if></span>
							</div>
							<div class="form-group ${introducedClass}">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" id="introduced"
									name="introduced" placeholder="Introduced date"
									value="${computer.getIntroduced()}"><span class="help-block">Pattern
									YYYY-MM-dd or dd-MM-YYYY. Delimiters can be - or . or / <c:if
										test="${introducedMessage != null}">
										<br />${introducedMessage}</c:if>
								</span>
							</div>
							<div class="form-group ${discontinuedClass}">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" id="discontinued"
									name="discontinued" placeholder="Discontinued date"
									value="${computer.getDiscontinued()}"><span class="help-block">Pattern
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
											${computer.getCompanyId() == company.getId() ? "selected" : "" }>${company.getName() }</option>
									</c:forEach>
								</select><span class="help-block">${companyIdMessage}</span>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/addcomputer.js"></script>
</body>
</html>