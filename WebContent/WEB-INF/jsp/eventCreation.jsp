<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="print"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Events creation page</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
</head>
<body>
	<h1>New Events Creation</h1>

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

	<%-- <c:if test="${not empty sessionScope.eventCreationMessage}">
		<c:out value="${sessionScope.eventCreationMessage}" />
		<c:remove var="eventCreationMessage" scope="session" />
	</c:if> --%>

	<c:if test="${not empty sessionScope.result}">
		<c:if test="${sessionScope.result }">
			<c:out value="Event Successfully created." />
		</c:if>

		<c:if test="${not sessionScope.result }">
			<c:out value="Event wasn't created." />
		</c:if>
	</c:if>

	<div>
		<form action="Controller" method="post" name="event-creation">
			<div>
				<input type="hidden" name="command" value="event-creation" />
			</div>

			<div>
				<label for="name">Event Name:</label>
			</div>
			<div>
				<input type="text" name="name" id="name" />
			</div>

			<div>
				<label for="start-date">Event Start Date:</label>
			</div>
			<div>
				<input type="date" name="start-date" id="start-date" />
			</div>

			<div>
				<label> Event Start Time:</label>
			</div>
			<div>
				<input type="number" min="0" max="23" name="start-time-hours"
					id="start-time-hours" /> : <input type="number" min="0" max="59"
					name="start-time-minutes" id="start-time-minutes" />
			</div>



			<div>
				<label for="end-date">Event End Date:</label>
			</div>
			<div>
				<input type="date" name="end-date" id="end-date" />
			</div>

			<div>
				<label> Event End Time:</label>
			</div>
			<div>
				<input type="number" min="0" max="23" name="end-time-hours"
					id="end-time-hours" /> : <input type="number" min="0" max="59"
					name="end-time-minutes" id="end-time-minutes" />
			</div>

			<div>
				<label for="team-one">Team One:</label>
			</div>
			<div>
				<input type="text" name="team-one" id="team-one" />
			</div>


			<div>
				<label for="team-two">Team Two:</label>
			</div>
			<div>
				<input type="text" name="team-two" id="team-two" />
			</div>

			<br>
			<div>
				<input type="submit" value="Submit Creation">
			</div>
		</form>
	</div>

</body>
</html>