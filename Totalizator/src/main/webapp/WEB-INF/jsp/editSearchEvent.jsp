<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="print"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="https://fonts.googleapis.com/css?family=Jura"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="CSS/registrationcss.css">
<link rel="stylesheet" type="text/css" href="CSS/stylecss.css">
<link rel="stylesheet" type="text/css" href="CSS/admincss.css">
<title>Edit/Search page</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.searchEvents" var="searchEvents" />
<fmt:message bundle="${loc}" key="local.game" var="game" />

</head>
<body>

	<c:import url="common/header.jsp" />

	<div class="content">
		<div class="center clearfix">

			<jsp:useBean id="games"
				class="by.epamtr.totalizator.bean.listbean.JSPGameListBean"
				scope="request" />
				
			<c:if test="${not empty sessionScope.successCloseMsg}">
				<c:if test="${sessionScope.successCloseMsg}">
					<c:out value="Game Cupoun was successfully closed." />
				</c:if>
			</c:if>

			<c:if test="${not empty sessionScope.successCloseMsg}">
				<c:if test="${not sessionScope.successCloseMsg}">
					<c:out
						value="Game Cupoun wasn't successfully closed. There are less then 15 events with appropriate status." />
				</c:if>
			</c:if>

			<c:if test="${not empty sessionScope.canselledMsg}">
				<c:if test="${sessionScope.canselledMsg}">
					<c:out value="Game Cupoun was canselled." />
				</c:if>
			</c:if>

			<c:if test="${not empty sessionScope.rollbackMsg}">
				<c:if test="${sessionScope.rollbackMsg}">
					<c:out
						value="Something went wrong. Transaction was rollbacked." />
				</c:if>
			</c:if>

			<form action='Controller' method='get' name='search-all-events'>
				<input type="hidden" name="command" value="search-all-events" />
				<div class="form-row">
					<label>${game }</label>
					<print:dropdownGamePopulation gamesList="${games}" /><span class="err"
						id="err-dropdown"></span>
				</div>
				<div class="user">
					<input  onclick="return validateDropdown()" type='submit' class="adminWorkBtn" value='${searchEvents }'>
				</div>
			</form>
		</div>
	</div>

	<c:import url="common/footer.jsp" />
	<script src="JS/dropdownValidation.js"></script>
</body>
</html>