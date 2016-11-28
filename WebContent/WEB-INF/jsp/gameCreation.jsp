<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Creation new game</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />

<fmt:message bundle="${loc}" key="local.newGameCreation"
	var="newGameCreation" />
<fmt:message bundle="${loc}" key="local.startDate" var="startDate" />

<fmt:message bundle="${loc}" key="local.startTimeHours"
	var="startTimeHours" />
<fmt:message bundle="${loc}" key="local.startTimeMinutes"
	var="startTimeMinutes" />
<fmt:message bundle="${loc}" key="local.endDate" var="endDate" />
<fmt:message bundle="${loc}" key="local.endTimeHours" var="endTimeHours" />

<fmt:message bundle="${loc}" key="local.endTimeMinutes"
	var="endTimeMinutes" />
<fmt:message bundle="${loc}" key="local.minBetAmount" var="minBetAmount" />

</head>
<body>
	<h1>${newGameCreation}</h1>
	<form action="Controller" method="post">
		<div>
			<input type="hidden" name="command" value="change-language" />
		</div>
		<input type="hidden" name="local" value="ru" /> <input type="submit"
			value="${ru_button}" /><br />
	</form>

	<form action="Controller" method="post">
		<div>
			<input type="hidden" name="command" value="change-language" />
		</div>
		<input type="hidden" name="local" value="en" /> <input type="submit"
			value="${en_button}" /><br />
	</form>
	
	<c:if test="${not empty sessionScope.result}">
		<c:if test="${sessionScope.result }" >
			<c:out value="Game Successfully created." />
		</c:if>
		
		<c:if test="${not sessionScope.result }" >
			<c:out value="Game wasn't created." />
		</c:if>
	</c:if>
	
	<div>
		<form action="Controller" method="post" name="game-creation">
			<div>
				<input type="hidden" name="command" value="game-creation" />
			</div>

			<div>
				<label for="start-date">${startDate}</label>
			</div>
			<div>
				<input type="date" name="start-date" id="start-date" />
			</div>

			<div>
				<label for="start-time-hours">${startTimeHours}</label>
			</div>
			<div>
				<input type="number" min="0" max="23" name="start-time-hours"
					id="start-time-hours" />
			</div>

			<div>
				<label for="start-time-minutes">${startTimeMinutes}</label>
			</div>
			<div>
				<input type="number" min="0" max="59" name="start-time-minutes"
					id="start-time-minutes" />
			</div>

			<div>
				<label for="end-date">${endDate}</label>
			</div>
			<div>
				<input type="date" name="end-date" id="end-date" />
			</div>

			<div>
				<label for="end-time-hours">${endTimeHours}</label>
			</div>
			<div>
				<input type="number" min="0" max="23" name="end-time-hours"
					id="end-time-hours" />
			</div>

			<div>
				<label for="end-time-minutes">${endTimeMinutes}</label>
			</div>
			<div>
				<input type="number" min="0" max="59" name="end-time-minutes"
					id="end-time-minutes" />
			</div>

			<div>
				<label for="min-bet-amount">${minBetAmount}</label>
			</div>
			<div>
				<input type="number" name="min-bet-amount" min="1"
					id="min-bet-amount" />
			</div>

			<div>
				<input type="submit" value="Submit Creation">
			</div>
		</form>
	</div>
</body>
</html>