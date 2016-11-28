<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
<link href="https://fonts.googleapis.com/css?family=Jura"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="CSS/stylecss.css">
<link rel="stylesheet" type="text/css" href="CSS/registrationcss.css">
<title>User Registration Page</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
	
<fmt:message bundle="${loc}" key="local.login" var="login" />
<fmt:message bundle="${loc}" key="local.password" var="password" />
<fmt:message bundle="${loc}" key="local.signIn" var="signIn" />
<fmt:message bundle="${loc}" key="local.register" var="register" />
<fmt:message bundle="${loc}" key="local.payment" var="payment" />
<fmt:message bundle="${loc}" key="local.news" var="news" />
<fmt:message bundle="${loc}" key="local.results" var="results" />
<fmt:message bundle="${loc}" key="local.about" var="about" />
<fmt:message bundle="${loc}" key="local.language" var="language" />
<fmt:message bundle="${loc}" key="local.name" var="name" />
<fmt:message bundle="${loc}" key="local.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.repPassword" var="repPassword" />
<fmt:message bundle="${loc}" key="local.age" var="age" />
<fmt:message bundle="${loc}" key="local.eMail" var="eMail" />
<fmt:message bundle="${loc}" key="local.country" var="country" />
<fmt:message bundle="${loc}" key="local.city" var="city" />
<fmt:message bundle="${loc}" key="local.address" var="address" />
<fmt:message bundle="${loc}" key="local.sex" var="sex" />
<fmt:message bundle="${loc}" key="local.M" var="M" />
<fmt:message bundle="${loc}" key="local.F" var="F" />
<fmt:message bundle="${loc}" key="local.unknown" var="unknown" />
</head>
<body>
	<header>
		<section class="clearfix">
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

				<form action="Controller" method="get" name="go-to-registration">
					<input type="hidden" name="command" value="go-to-registration" />
					<div class="user">
						<input class="btn-register" type="submit"
							value="${register}">
					</div>
				</form>

				<form action="Controller" method="post" name="sign-in">
					<div>
						<input type="hidden" name="command" value="sign-in" />
						<div class="user">
							<input type="text" name="login" id="login" placeholder="${login}"
								required />
						</div>
						<div class="user">
							<input type="password" name="password" id="password"
								placeholder="${password}" required />
						</div>
						<div class="user">
							<input class="btn-login" type="submit" value="${signIn}">
						</div>
					</div>
				</form>
			</div>
		</section>
	</header>

	<aside class="sidebar-right">
		<article>
			<h4>
				<a
					href="https://www.overbetting.net/news/agames/unibet-dostig-istoricheskogo-maksimuma-rentabelnosti.html">
					Unibet достиг исторического максимума рентабельности</a>
			</h4>
			<p>Корпорация Unibet сообщила о рекордной рентабельности в
				третьем квартале 2016 года: валовой доход достиг максимума £142 300
				000, увеличившись в годовом исчислении на 65%.</p>
			<time datetime="2016-11-04">11.04.2016</time>
		</article>
	</aside>


	<main class="content">

	<div class="center clearfix">
		<form action="Controller" method="post" name="registration">
			<fieldset id="ancestor">
				<legend>Персональные Данные:</legend>
				<div>
					<input type="hidden" name="command" value="registration-user" />
				</div>

				<div class="form-row">
					<label for="firstName">${name }</label> <input type="text"
						name="firstName" id="firstName" /> <span class="err"
						id="err-firstName"></span>
				</div>


				<div class="form-row">
					<label for="lastName">${surname }</label> <input type="text"
						name="lastName" id="lastName" /> <span class="err"
						id="err-lastName"></span>
				</div>

				<div class="form-row">
					<label for="register-login">${login}</label> <input type="text"
						name="register-login" id="register-login" /> <span class="err"
						id="err-login"></span>
				</div>

				<div class="form-row">
					<label for="register-password">${password}</label> <input
						type="password" name="register-password" id="register-password" />
					<span class="err" id="err-password"></span>
				</div>

				<div class="form-row">
					<label for="rep-password">${repPassword }</label> <input
						type="password" name="rep-password" id="rep-password" /> <span
						class="err" id="err-rep-password"></span>
				</div>

				<div class="form-row">
					<label for="age">${age}</label> <input type="number" name="age"
						id="age" min="18" max="120" /> <span class="err" id="err-age"></span>
				</div>

				<div class="form-row" id="eMailContainer">
					<label for="e-mail">${eMail }</label> <input type="text"
						name="e-mail" id="e-mail" /> <span class="err" id="err-e-mail"></span>
					<!-- <button id="addBtn" type="button" class="addEmailBtn" name="addBtn"
						onclick="return addSecondEmail()">Добавить e-mail</button> -->
				</div>

				<div class="form-row">
					<label for="country">${country }</label> <input type="text"
						name="country" id="country" /> <span class="err" id="err-country"></span>
				</div>

				<div class="form-row">
					<label for="city">${city }</label> <input type="text" name="city"
						id="city" />
				</div>

				<div class="form-row">
					<label for="address">${address }</label> <input type="text"
						name="address" id="address" />
				</div>
				<div class="form-row">
					<label>${sex }</label> <select name="sex">
						<option value="M">${M }</option>
						<option value="F">${F }</option>
						<option value="Unknown" selected>${unknown}</option>
					</select> <span class="err" id="err-sex"></span>
				</div>
			</fieldset>
			<div class="registration-submit">
				<input class="btn-register"
					onclick="return validateRegistrationForm()" type="submit"
					value="${register}">
			</div>
		</form>
	</div>
	</main>

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
	<script src="JS/registrationValidation.js"></script>
</body>
</html>
