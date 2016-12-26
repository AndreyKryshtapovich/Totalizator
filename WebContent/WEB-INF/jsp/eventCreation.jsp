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
<title>Events creation page</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
</head>
<body>
	<c:import url ="common/header.jsp"/>

	<div class="content">
	<div class="center clearfix">
		<div>
			<c:if test="${not empty sessionScope.result}">
				<c:if test="${sessionScope.result }">
					<c:out value="Event Successfully created." />
				</c:if>

				<c:if test="${not sessionScope.result }">
					<c:out value="Event wasn't created." />
				</c:if>
			</c:if>

		</div>
		<br>

		<form action="Controller" method="post" name="event-creation">
			<fieldset>
				<legend class="legendInfo">New Events Creation</legend>
				<div>
					<input type="hidden" name="command" value="event-creation" />
				</div>

				<div class="form-row">
					<label for="name">Event Name:</label> <input type="text"
						name="name" id="name" />
				</div>

				<div class="form-row">
					<label for="start-date">Event Start Date:</label> <input
						type="date" name="start-date" id="start-date" />
				</div>

				<div class="form-row-time">
					<label> Event Start Time:</label> <input type="number" min="0"
						max="23" name="start-time-hours" id="start-time-hours" /> : <input
						type="number" min="0" max="59" name="start-time-minutes"
						id="start-time-minutes" />
				</div>

				<div class="form-row">
					<label for="end-date">Event End Date:</label> <input type="date"
						name="end-date" id="end-date" />
				</div>


				<div class="form-row-time">
					<label> Event End Time:</label> <input type="number" min="0"
						max="23" name="end-time-hours" id="end-time-hours" /> : <input
						type="number" min="0" max="59" name="end-time-minutes"
						id="end-time-minutes" />
				</div>

				<div class="form-row">
					<label for="team-one">Team One:</label> <input type="text"
						name="team-one" id="team-one" />
				</div>



				<div class="form-row">
					<label for="team-two">Team Two:</label> <input type="text"
						name="team-two" id="team-two" />
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