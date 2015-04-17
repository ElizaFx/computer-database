<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="start" required="true"%>
<%@ attribute name="end" required="true"%>
<%@ attribute name="current" required="true"%>
<div class="container text-center">
	<ul class="pagination">
		<li <c:if test="${pagined.isFirst()}">class="disabled"</c:if>><mylib:link
				disabled="${pagined.isFirst()}" limit="${pagined.getLimit()}"
				label="First Page">
				<span aria-hidden="true">&laquo;</span>
			</mylib:link> <mylib:link disabled="${pagined.isFirst()}"
				page="${pagined.getPrevious()}" limit="${pagined.getLimit()}"
				label="Previous">
				<span aria-hidden="true">&lsaquo;</span>
			</mylib:link></li>
		<c:forEach items="${pagined.getlPages()}" var="myPage">
			<c:choose>
				<c:when test="${myPage == pagined.getCurPage()}">
					<li class="disabled"><mylib:link disabled="true">${myPage}</mylib:link>
					</li>
				</c:when>
				<c:otherwise>
					<li><mylib:link page="${myPage}" limit="${pagined.getLimit()}">${myPage}</mylib:link>
					</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<li <c:if test="${pagined.isLast()}">class="disabled"</c:if>><mylib:link
				disabled="${pagined.isLast()}" page="${pagined.getNext()}"
				limit="${pagined.getLimit()}" label="Next">
				<span aria-hidden="true">&rsaquo;</span>
			</mylib:link> <mylib:link disabled="${pagined.isLast()}"
				page="${pagined.getPageMax()}" limit="${pagined.getLimit()}"
				label="Last Page">
				<span aria-hidden="true">&raquo;</span>
			</mylib:link></li>
	</ul>
	<div class="btn-group btn-group-sm pull-right" role="group">
		<mylib:link page="${pagined.getCurPage()}" limit="10"
			myClass="btn btn-default">10</mylib:link>
		<mylib:link page="${pagined.getCurPage()}" limit="50"
			myClass="btn btn-default">50</mylib:link>
		<mylib:link page="${pagined.getCurPage()}" limit="100"
			myClass="btn btn-default">100</mylib:link>
	</div>
</div>
