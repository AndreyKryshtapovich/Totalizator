<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="https://fonts.googleapis.com/css?family=Jura"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="CSS/stylecss.css">
<link rel="stylesheet" type="text/css" href="CSS/admincss.css">

<title>Admin page</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.message" var="message" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />

<fmt:message bundle="${loc}" key="local.createEvents" var="createEvents" />
<fmt:message bundle="${loc}" key="local.matchEventsToGames" var="matchEventsToGames" />
<fmt:message bundle="${loc}" key="local.editSearchEvent" var="editSearchEvent" />
<fmt:message bundle="${loc}" key="local.createGame" var="createGame" />
</head>
<body>

<c:import url ="common/userHeader.jsp"/>

<div class="content">

	<h2>${message}${sessionScope.login}</h2>
	
	<c:if test="${not empty sessionScope.result}">
			<c:if test="${not sessionScope.result }">
				<c:out value="Failed matching event and game." />
			</c:if>
		</c:if>
	
	<div>
		<form action="Controller" method="get" name="create-game">
			<div>
				<input type="hidden" name="command" value="go-to-game-creation" />
			</div>
			<div class="user" >
				<input class="adminWorkBtn" type="submit" value="${createGame}">
			</div>
		</form>
	</div>


	<div>
		<form action="Controller" method="get" name="create-event">
			<div>
				<input type="hidden" name="command" value="go-to-event-creation" />
			</div>
			<div class="user">
				<input class="adminWorkBtn" type="submit" value="${createEvents }">
			</div>
		</form>
	</div>

	<div>
		<form action="Controller" method="get" name="matching">
			<div>
				<input type="hidden" name="command" value="go-to-search-event" />
			</div>
			<div class="user">
				<input class="adminWorkBtn" type="submit" value="${matchEventsToGames }">
			</div>
		</form>
	</div>

	<div>
		<form action="Controller" method="get" name="searchEdit">
			<div>
				<input type="hidden" name="command" value="go-to-edit-search-event" />
			</div>
			<div class="user">
				<input class="adminWorkBtn" type="submit" value="${editSearchEvent }">
			</div>
		</form>
	</div>
</div>
	
<c:import url = "common/footer.jsp"/>
</body>
</html>