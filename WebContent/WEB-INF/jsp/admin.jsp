<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
</head>
<body>
	<h1>this is admin page</h1>
	<h2>${message}${sessionScope.login}</h2>

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
	<div>
		<form action="Controller" method="get" name="create-game">
			<div>
				<input type="hidden" name="command" value="go-to-game-creation" />
			</div>
			<div>
				<input type="submit" value="${createGame}">
			</div>
		</form>
	</div>


	<div>
		<form action="Controller" method="get" name="create-game">
			<div>
				<input type="hidden" name="command" value="go-to-event-creation" />
			</div>
			<div>
				<input type="submit" value="CreateEvents">
			</div>
		</form>
	</div>

	<div>
		<form action="Controller" method="get" name="matching">
			<div>
				<input type="hidden" name="command" value="go-to-search-event" />
			</div>
			<div>
				<input type="submit" value="Match Events To Games">
			</div>
		</form>
	</div>

	<div>
		<form action="Controller" method="get" name="searchEdit">
			<div>
				<input type="hidden" name="command" value="go-to-edit-search-event" />
			</div>
			<div>
				<input type="submit" value="Edit/Search Event">
			</div>
		</form>
	</div>


	<div>
		<form action="Controller" method="post" name="sign-out">
			<div>
				<input type="hidden" name="command" value="sign-out" />
			</div>
			<div>
				<input type="submit" value="${signOut}">
			</div>
		</form>
	</div>

</body>
</html>