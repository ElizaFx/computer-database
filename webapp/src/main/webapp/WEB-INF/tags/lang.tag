<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ attribute name="id" required="false" type="java.lang.Integer"%>
<spring:message code="global.locale" var="localeCode" />
<spring:message code="global.localeName" var="localeName" />
<c:choose>
	<c:when test="${id != null}">
	<div class="dropdown navbar-brand">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown"
				role="button" aria-expanded="false"><img class="img-rounded"
				alt="${localeName}" src="${pageContext.request.contextPath}/flags/${localeCode}.svg"><span
				class="caret"></span></a>
			<ul class="dropdown-menu" role="menu">
				<li><a href="editComputer?id=${id }&locale=en">English (UK)</a></li>
				<li><a href="editComputer?id=${id }&locale=en_US">English (US)</a></li>
				<li><a href="editComputer?id=${id }&locale=fr">Français</a></li>
			</ul>
		</div>
	</c:when>
	<c:otherwise>
		<div class="dropdown navbar-brand">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown"
				role="button" aria-expanded="false"><img class="img-rounded"
				alt="${localeName}" src="${pageContext.request.contextPath}/flags/${localeCode}.svg"><span
				class="caret"></span></a>
			<ul class="dropdown-menu" role="menu">
				<li><a href="?locale=en">English (UK)</a></li>
				<li><a href="?locale=en_US">English (US)</a></li>
				<li><a href="?locale=fr">Français</a></li>
			</ul>
		</div>
	</c:otherwise>
</c:choose>