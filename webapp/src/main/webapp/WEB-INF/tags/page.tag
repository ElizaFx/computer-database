<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="begin" required="true" type="java.lang.Integer"%>
<%@ attribute name="end" required="true" type="java.lang.Integer"%>
<%@ attribute name="current" required="true" type="java.lang.Integer"%>
<%@ attribute name="search" required="true" type="java.lang.String"%>
<%@ attribute name="limit" required="true" type="java.lang.Integer"%>
<%@ attribute name="pagemax" required="true" type="java.lang.Integer"%>
<%@ attribute name="orderBy" required="true" type="java.lang.String"%>
<%@ attribute name="asc" required="true" type="java.lang.Boolean"%>
<div class="container text-center">
	<ul class="pagination">
		<li <c:if test="${begin >= current}">class="disabled"</c:if>><mylib:link
				disabled="${begin >= current}" limit="${limit}" search="${search}"
				orderBy="${orderBy}" asc="${asc}" label="First Page">
				<span aria-hidden="true">&laquo;</span>
			</mylib:link> <mylib:link disabled="${begin >= current}" page="${current - 1}"
				limit="${limit}" search="${search}" orderBy="${orderBy}"
				asc="${asc}" label="Previous">
				<span aria-hidden="true">&lsaquo;</span>
			</mylib:link></li>
		<c:forEach begin="${begin}" end="${end}" var="myPage">
			<li <c:if test="${myPage == current}">class="disabled"</c:if>><mylib:link
					disabled="${myPage == current}" page="${myPage}" limit="${limit}"
					search="${search}" orderBy="${orderBy}" asc="${asc}">${myPage}</mylib:link></li>
		</c:forEach>
		<li <c:if test="${end <= current}">class="disabled"</c:if>><mylib:link
				disabled="${end <= current}" page="${current + 1}" limit="${limit}"
				search="${search}" label="Next" orderBy="${orderBy}" asc="${asc}">
				<span aria-hidden="true">&rsaquo;</span>
			</mylib:link> <mylib:link disabled="${end <= current}" page="${pagemax}"
				limit="${limit}" search="${search}" label="Last Page"
				orderBy="${orderBy}" asc="${asc}">
				<span aria-hidden="true">&raquo;</span>
			</mylib:link></li>
	</ul>
	<div class="btn-group btn-group-sm pull-right" role="group">
		<mylib:link page="${current}" search="${search}" limit="10"
			myClass="btn btn-default" orderBy="${orderBy}" asc="${asc}">10</mylib:link>
		<mylib:link page="${current}" search="${search}" limit="50"
			myClass="btn btn-default" orderBy="${orderBy}" asc="${asc}">50</mylib:link>
		<mylib:link page="${current}" search="${search}" limit="100"
			myClass="btn btn-default" orderBy="${orderBy}" asc="${asc}">100</mylib:link>
	</div>
</div>
