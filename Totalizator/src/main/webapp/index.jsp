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
<!--  <link rel="stylesheet" type="text/css" href="CSS/stylecss.css"> -->
<link rel="stylesheet" type="text/css" href="CSS/stylecss.css">

<title>Main Page</title>
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
				<form action="Controller" method="get" name="registration">
					<input type="hidden" name="command" value="go-to-registration" />
					<div class="user">
						<input class="btn-register" type="submit" value="${register}">
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
				<c:if test="${not empty sessionScope.result}">
					<c:if test="${sessionScope.result }">
						<c:out value="You registrated successfully." />
					</c:if>

				</c:if>
			</div>
		</section>
	</header>
	
	<c:if test="${empty sessionScope.local or sessionScope.local eq 'ru' }">
		<c:import url="PageContent/news_ru.jsp"></c:import>
	</c:if>
	
	<c:if test="${sessionScope.local eq 'en' }">
		<c:import url="PageContent/news_en.jsp"></c:import>
	</c:if>
	
	
	<c:if test="${ sessionScope.local eq 'en' }">
		<c:import url="PageContent/rules_en.jsp"></c:import>
	</c:if>
	
	<c:if test="${empty sessionScope.local or sessionScope.local eq 'ru' }">
		<c:import url="PageContent/rules_ru.jsp"></c:import>
	</c:if>


	<c:import url="WEB-INF/jsp/common/footer.jsp" />
</body>
</html>
