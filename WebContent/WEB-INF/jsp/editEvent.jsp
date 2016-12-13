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
<link rel="stylesheet" type="text/css" href="CSS/stylecss.css">
<link rel="stylesheet" type="text/css" href="CSS/registrationcss.css">
<link rel="stylesheet" type="text/css" href="CSS/eventCreation.css">
<link rel="stylesheet" type="text/css" href="CSS/admincss.css">
<title>Event editing page</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="local.signOut" var="signOut" />
<fmt:message bundle="${loc}" key="local.language" var="language" />
<fmt:message bundle="${loc}" key="local.payment" var="payment" />
<fmt:message bundle="${loc}" key="local.news" var="news" />
<fmt:message bundle="${loc}" key="local.results" var="results" />
<fmt:message bundle="${loc}" key="local.about" var="about" />
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
		<form action="Controller" method="post" name="edit-event">
			<fieldset>
				<legend class="legendInfo">Please insert event's info</legend>
			<div>
				<input type="hidden" name="command" value="edit-event" />
				<input type="hidden" name="eventId" value="${requestScope.eventId }" />
			</div>

			<div class="form-row">
				<label for="name">Event Name:</label><input type="text" name="name" id="name"
					value="${requestScope.eventName}" />
			</div>

			<div class="form-row">
				<label for="gameCouponID">Game Coupon Id:</label><input type="number" name="gameCouponId" id="gameCouponId"
					value="${requestScope.gameId}" readonly />
			</div>

			<div class="form-row">
				<label for="team-one">Team One:</label><input type="text" name="team-one" id="team-one"
					value="${requestScope.teamOne}" />
			</div>


			<div class="form-row">
				<label for="team-two">Team Two:</label><input type="text" name="team-two" id="team-two"
					value="${requestScope.teamTwo}" />
			</div>

			<div class="form-row">
				<label>Result:</label> 
				<select name="resultId">
					<c:forEach var="item" items="${resultsMap}">
						<option value="${item.key}" ${item.key == selectedRes ? 'selected="selected"' : ''}>
						${item.value}
						</option>
					</c:forEach>
				</select>
				
			</div>

			<div class="form-row">
				<label for="start-date">Event Start Date:</label><input type="date" name="start-date" id="start-date" value="${requestScope.startDate}"  />
			</div>

			<div class="form-row-time">
				<label> Event Start Time:</label><input type="number" min="0" max="23" name="start-time-hours"
					id="start-time-hours" value="${requestScope.startTimeHours}" /> : <input type="number" min="0" max="59"
					name="start-time-minutes" id="start-time-minutes" value="${requestScope.startTimeMinutes}"/>
			</div>
			

			<div class="form-row">
				<label for="end-date">Event End Date:</label><input type="date" name="end-date" id="end-date" value="${requestScope.endDate}"/>
			</div>

			<div class="form-row-time">
				<label> Event End Time:</label><input type="number" min="0" max="23" name="end-time-hours"
					id="end-time-hours" value="${requestScope.endTimeHours}" /> : <input type="number" min="0" max="59"
					name="end-time-minutes" id="end-time-minutes" value="${requestScope.endTimeMinutes}" />
			</div>

			<div class="form-row">
				<label>Status:</label>
	
					<select name="status">
					<c:forEach var="item" items="${statusMap}">
						<option value="${item.key}" ${item.key == selectedStatus ? 'selected="selected"' : ''}>
						${item.value}
						</option>
					</c:forEach>
				</select>
			</div>

			<br>
			</fieldset>
			<div class="registration-submit">
				<input class="adminWorkBtn" type="submit" value="Save changes">
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