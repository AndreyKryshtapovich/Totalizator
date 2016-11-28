<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<body>

		<jsp:useBean id="games"
		class="by.epamtr.totalizator.bean.listbean.JSPGameListBean"
		scope="request" />

	<form action="Controller" method="get" name="dropdownTest">
		<input type="hidden" name="command" value="get-games-in-development" />
		<input list="browsers" name="browser">
		<datalist id="browsers">
			<c:forEach var="aff" items="${testList.}">
				<option value="${aff}"></option>
			</c:forEach>
		</datalist>
		<input type="submit">
	</form>


</body>
</html>