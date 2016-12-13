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
<fmt:message bundle="${loc}" key="local.signOut" var="signOut" />
<fmt:message bundle="${loc}" key="local.language" var="language" />
<fmt:message bundle="${loc}" key="local.payment" var="payment" />
<fmt:message bundle="${loc}" key="local.news" var="news" />
<fmt:message bundle="${loc}" key="local.results" var="results" />
<fmt:message bundle="${loc}" key="local.about" var="about" />
<fmt:message bundle="${loc}" key="local.gameStartTime"
	var="gameStartTime" />
<fmt:message bundle="${loc}" key="local.gameEndTime" var="gameEndTime" />

</head>
<body>


	<header>
		<section>
			<div class="top-area">
				<ul class="topnav" id="myTopnav">
					<li><a href="#payment"><b>${payment }</b></a></li>
					<li><a href="#news"><b>${news }</b></a></li>
					<li><a href="#results"><b>${results }</b></a></li>
					<li><a href="#about"><b>${about }</b></a></li>
				</ul>

				<div class="dropdown">
					<button class="dropbtn">${language }</button>
					<div class="dropdown-content">

						<form action="Controller" method="post">
							<input type="hidden" name="command" value="change-language" />
							<div>
								<input type="hidden" name="local" value="ru" />
								<div>
									<input type="submit" class="dropBtn" value="${ru_button}" />
								</div>
							</div>
						</form>

						<form action="Controller" method="post">
							<div>
								<input type="hidden" name="command" value="change-language" />
							</div>

							<input type="hidden" name="local" value="en" /> <input
								type="submit" value="${en_button}" /><br />
						</form>
					</div>
				</div>
				<form action="Controller" method="post" name="sign-out">
					<div>
						<input type="hidden" name="command" value="sign-out" />
						<div class="user">
							<input class="btn-login" type="submit" value="${signOut}">
						</div>

					</div>
				</form>

			</div>
		</section>
	</header>


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
	<footer class="bottom">
		<p>&copy;All rights reserved. Totalizator by Andrey Kryshtapovich</p>

		<section>
			<p>Наш адрес электронной почты info@toto.com, телефон
				контакт-центра: 8 (800) 77-56-21.</p>
		</section>

		<section>
			<a href="https://www.facebook.com"> <img
				src="IMG/facebook_logo.jpg" alt="Facebook" width="25" height="25">
			</a> <a href="https://www.twitter.com"> <img
				src="IMG/twitter_logo.jpg" alt="Twitter" width="25" height="25">
			</a>
		</section>
	</footer>
</body>
</html>