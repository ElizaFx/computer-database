<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="name" required="true" type="java.lang.String"%>
<%@ attribute name="orderBy" required="true" type="java.lang.String"%>
<%@ attribute name="asc" required="true" type="java.lang.Boolean"%>

<c:if test="${name == orderBy}">
	<span
		<c:choose>
			<c:when test="${asc }">class="order dropup"</c:when> 
			<c:otherwise>class="order dropdown"</c:otherwise>
		</c:choose>>
		<span class="caret" style="margin: 10px 5px;"> </span>
	</span>
</c:if>