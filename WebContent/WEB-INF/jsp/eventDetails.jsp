<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="print"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Event Details Page</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.message" var="message" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
</head>
<body>

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


	<c:out
		value="Game Cupoun Id: ${gameCupounId} Game Dates: ${gameStartDate} - ${gameEndDate}"></c:out>
	<print:eventDetailsTable eventsList="${events}" eventName="Event Name"
		teamOne="Team One" teamTwo="TeamTwo" result="Result"
		startDate="Start Date" endDate="End Date" status="Status" />

</body>
</html>