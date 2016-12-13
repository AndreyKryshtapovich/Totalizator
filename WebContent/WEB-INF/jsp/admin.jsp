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

<fmt:message bundle="${loc}" key="local.signOut" var="signOut" />
<fmt:message bundle="${loc}" key="local.createGame" var="createGame" />
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

	<h1>this is admin page</h1>
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
		<form action="Controller" method="get" name="create-game">
			<div>
				<input type="hidden" name="command" value="go-to-event-creation" />
			</div>
			<div class="user">
				<input class="adminWorkBtn" type="submit" value="CreateEvents">
			</div>
		</form>
	</div>

	<div>
		<form action="Controller" method="get" name="matching">
			<div>
				<input type="hidden" name="command" value="go-to-search-event" />
			</div>
			<div class="user">
				<input class="adminWorkBtn" type="submit" value="Match Events To Games">
			</div>
		</form>
	</div>

	<div>
		<form action="Controller" method="get" name="searchEdit">
			<div>
				<input type="hidden" name="command" value="go-to-edit-search-event" />
			</div>
			<div class="user">
				<input class="adminWorkBtn" type="submit" value="Edit/Search Event">
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