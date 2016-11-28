<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="print"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>user page</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.message" var="message" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />

<fmt:message bundle="${loc}" key="local.signOut" var="signOut" />
<fmt:message bundle="${loc}" key="local.createBet" var="createBet" />
</head>
<body>
	<h1>this is a user page</h1>
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
	


	<jsp:useBean id="events"
		class="by.epamtr.totalizator.bean.listbean.JSPListBean"
		scope="request" />
	<div>
		<form action="Controller" method="get" name="create-bet">
			<div>
				<input type="hidden" name="command" value="create-bet" />
			</div>
			<print:jsptable list="${events}" colunmName1="Date"
				colunmName2="Event" colunmName3="1" colunmName4="X" colunmName5="2" />
			<div>
				<input type="submit" value="${createBet}">
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