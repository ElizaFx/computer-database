<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="id" required="false" type="java.lang.Integer"%>
<c:choose>
	<c:when test="${id != null}">
		<a class="navbar-right navbar-brand" href="editComputer?id=${id }&locale=en"><img
			alt="english" src="flags/en.svg"></a>
		<a class="navbar-right navbar-brand" href="editComputer?id=${id }&locale=fr"><img
			alt="french" src="flags/fr.svg"></a>
	</c:when>
	<c:otherwise>
		<a class="navbar-right navbar-brand" href="?locale=en"><img
			alt="english" src="flags/en.svg"></a>
		<a class="navbar-right navbar-brand" href="?locale=fr"><img
			alt="french" src="flags/fr.svg"></a>
	</c:otherwise>
</c:choose>