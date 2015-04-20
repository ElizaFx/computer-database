<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ attribute name="page" required="false" type="java.lang.Integer" %> 
<%@ attribute name="limit" required="false" type="java.lang.Integer" %> 
<%@ attribute name="search" required="false" type="java.lang.String" %> 
<%@ attribute name="disabled" required="false" type="java.lang.Boolean" %> 
<%@ attribute name="label" required="false" type="java.lang.String" %> 
<%@ attribute name="myClass" required="false" type="java.lang.String" %> 

<c:choose>
	<c:when test="${disabled != null && disabled == true }">
		<a href="#" aria-label="${label}" class="${myClass}"><jsp:doBody /></a>
	</c:when>
	<c:when test="${(page == null || page == 0) && limit != null}">
		<a href="dashboard?page=1&limit=${limit}&search=${search}" aria-label="${label}" class="${myClass}"><jsp:doBody/></a>
	</c:when>
	<c:when test="${page != null && limit == null }">
		<a href="dashboard?page=${page}&limit=1&search=${search}" aria-label="${label}" class="${myClass}"><jsp:doBody/></a>
	</c:when>
	<c:when test="${page != null && limit != null }">
		<a href="dashboard?page=${page}&limit=${limit}&search=${search}" aria-label="${label}" class="${myClass}"><jsp:doBody/></a>
	</c:when>
	<c:otherwise>
		<a href="dashboard?page=1&limit=10&search=${search}" aria-label="${label}" class="${myClass}"><jsp:doBody/></a>
	</c:otherwise>
</c:choose>