<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ attribute name="name" required="true" type="java.lang.String"%>
<%@ attribute name="orderBy" required="true" type="java.lang.String"%>
<%@ attribute name="asc" required="true" type="java.lang.Boolean"%>
<%@ attribute name="search" required="true" type="java.lang.String"%>
<%@ attribute name="page" required="true" type="java.lang.Integer"%>
<%@ attribute name="limit" required="true" type="java.lang.Integer"%>


<mylib:link search="${search}" page="${page}" limit="${limit}"
	orderBy="${name}" asc="true" disabled="${orderBy == name && asc == true}">
	<span class="order dropdown">
		<span class="caret" style="margin: 10px 10px; border-width:8px;"></span>
	</span>
</mylib:link>

<mylib:link search="${search}" page="${page}" limit="${limit}"
	orderBy="${name}" asc="false" disabled="${orderBy == name && asc == false}">
	<span class="order dropup">
		<span class="caret" style="margin: 10px 10px; border-width:8px;"> </span>
	</span>
</mylib:link>
