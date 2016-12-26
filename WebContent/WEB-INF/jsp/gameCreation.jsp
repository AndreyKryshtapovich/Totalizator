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
<link rel="stylesheet" type="text/css" href="CSS/registrationcss.css">
<link rel="stylesheet" type="text/css" href="CSS/eventCreation.css">
<link rel="stylesheet" type="text/css" href="CSS/admincss.css">
<title>Creation new game</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />



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
<fmt:message bundle="${loc}" key="local.gameStartTime"
	var="gameStartTime" />
<fmt:message bundle="${loc}" key="local.gameEndTime" var="gameEndTime" />

</head>
<body>
	<c:import url ="common/header.jsp"/>
	<div class="content">
		<div class="center clearfix">
			<div>
				<c:if test="${not empty sessionScope.result}">
					<c:if test="${sessionScope.result }">
						<c:out value="Game Successfully created." />
					</c:if>

					<c:if test="${not sessionScope.result }">
						<c:out value="Game wasn't created." />
					</c:if>
				</c:if>

			</div>
			<br>

			<form action="Controller" method="post" name="game-creation">
				<fieldset>
					<legend class="legendInfo">${newGameCreation}</legend>
					<div class="form-row">
						<input type="hidden" name="command" value="game-creation" />
					</div>

					<div class="form-row">
						<label for="start-date">${startDate}</label> <input type="date"
							name="start-date" id="start-date" />
					</div>

					<div class="form-row-time">
						<label>${gameStartTime } </label> <input type="number" min="0"
							max="23" name="start-time-hours" id="start-time-hours" /> : <input
							type="number" min="0" max="59" name="start-time-minutes"
							id="start-time-minutes" />
					</div>

					<div class="form-row">
						<label for="end-date">${endDate}</label> <input type="date"
							name="end-date" id="end-date" />
					</div>

					<div class="form-row-time">
						<label>${gameEndTime } </label> <input type="number" min="0"
							max="23" name="end-time-hours" id="end-time-hours" /> : <input
							type="number" min="0" max="59" name="end-time-minutes"
							id="end-time-minutes" />
					</div>

					<div class="form-row">
						<label for="min-bet-amount">${minBetAmount}</label> <input
							type="number" name="min-bet-amount" min="1" id="min-bet-amount" />
					</div>

					<br>
				</fieldset>

				<div class="registration-submit">
					<input class="adminWorkBtn" type="submit" value="Submit Creation">
				</div>
			</form>
		</div>
	</div>
	<c:import url ="common/footer.jsp"/>
</body>
</html>