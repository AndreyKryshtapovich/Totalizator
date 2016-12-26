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
				<c:if test="${not sessionScope.canselledMsg}">
					<c:out value="Game Cupoun was canselled." />
				</c:if>
			</c:if>

			<c:if test="${not empty sessionScope.canselledMsg}">
				<c:if test="${not sessionScope.rollbackMsg}">
					<c:out
						value="Game Cupoun was canselled. Transaction was rollbacked." />
				</c:if>
			</c:if>

			<form action='Controller' method='get' name='search-all-events'>
				<input type="hidden" name="command" value="search-all-events" />
				<div class="form-row">
					<label>Game:</label>
					<print:dropdownGamePopulation gamesList="${games}" />
				</div>
				<div class="user">
					<input type='submit' class="adminWorkBtn" value='Search Events'>
				</div>
			</form>
		</div>
	</div>

	<c:import url="common/footer.jsp" />
</body>
</html>