<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="begin" required="true" type="java.lang.Integer"%>
<%@ attribute name="end" required="true" type="java.lang.Integer"%>
<%@ attribute name="current" required="true" type="java.lang.Integer"%>
<%@ attribute name="limit" required="true" type="java.lang.Integer"%>
<%@ attribute name="pagemax" required="true" type="java.lang.Integer"%>

<div class="container text-center">
	<ul class="pagination">
		<li <c:if test="${begin == current}">class="disabled"</c:if>>
			<mylib:link disabled="${begin == current}"
				limit="${limit}" label="First Page">
				<span aria-hidden="true">&laquo;</span>
			</mylib:link>
			<mylib:link disabled="${begin == current}"
				page="${current - 1}" limit="${limit}"
				label="Previous">
				<span aria-hidden="true">&lsaquo;</span>
			</mylib:link>
		</li>
		<c:forEach begin="${begin}" end="${end}" var="myPage">
			<li <c:if test="${myPage == current}">class="disabled"</c:if>>
				<mylib:link disabled="${myPage == current}" page="${myPage}" limit="${limit}">${myPage}</mylib:link>
			</li>
		</c:forEach>
		<li <c:if test="${end == current}">class="disabled"</c:if>>
			<mylib:link disabled="${end == current}"
				page="${current + 1}" limit="${limit}"
				label="Next">
				<span aria-hidden="true">&rsaquo;</span>
			</mylib:link>
			<mylib:link disabled="${end == current}"
				page="${pagemax}" limit="${limit}"
				label="Last Page">
				<span aria-hidden="true">&raquo;</span>
			</mylib:link>
		</li>
	</ul>
	<div class="btn-group btn-group-sm pull-right" role="group">
		<mylib:link page="${current}" limit="10" myClass="btn btn-default">10</mylib:link>
		<mylib:link page="${current}" limit="50" myClass="btn btn-default">50</mylib:link>
		<mylib:link page="${current}" limit="100" myClass="btn btn-default">100</mylib:link>
	</div>
</div>
