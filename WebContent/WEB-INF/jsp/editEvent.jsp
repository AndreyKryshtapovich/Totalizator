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

</head>
<body>
	<c:import url ="common/header.jsp"/>

	<div class="content">
		<div class="center clearfix">
		
		<c:if test="${not empty sessionScope.result}">
					<c:if test="${not sessionScope.result }">
						<c:out value="Invalid status of Game Coupon for unmatching." />
					</c:if>
		</c:if>
		
		<c:if test="${not empty sessionScope.result}">
					<c:if test="${sessionScope.result }">
						<c:out value="Invalid status of Game Coupon for deletting event." />
					</c:if>
		</c:if>
		
		<c:if test="${not empty sessionScope.param_result}">
					<c:if test="${not sessionScope.param_result }">
						<c:out value="Invalid parameters for updating event." />
					</c:if>
		</c:if>
		
			
			<form action="Controller" method="post" name="edit-event">
				<fieldset>
					<legend class="legendInfo">Please insert event's info</legend>
					<div>
						<input type="hidden" name="command" value="edit-event" /> <input
							type="hidden" name="eventId" value="${requestScope.eventId }" />
					</div>

					<div class="form-row">
					<c:if test="${requestScope.gameStatus eq 'In developing'}">
						<label for="name">Event Name:</label><input type="text"
							name="name" id="name" value="${requestScope.eventName}" />
					</c:if>
					<c:if test="${requestScope.gameStatus ne 'In developing'}">
						<label for="name">Event Name:</label><input type="text"
							name="name" id="name" value="${requestScope.eventName}" readonly/>
					</c:if>
					</div>

					<div class="form-row">
						<label for="gameCouponID">Game Coupon Id:</label><input
							type="number" name="gameCouponId" id="gameCouponId"
							value="${requestScope.gameId}" readonly />
					</div>

					<div class="form-row">
					<c:if test="${requestScope.gameStatus eq 'In developing'}">
						<label for="team-one">Team One:</label><input type="text"
							name="team-one" id="team-one" value="${requestScope.teamOne}" />
					</c:if>
					<c:if test="${requestScope.gameStatus ne 'In developing'}">
						<label for="team-one">Team One:</label><input type="text"
							name="team-one" id="team-one" value="${requestScope.teamOne}" readonly/>
					</c:if>
					</div>


					<div class="form-row">
					<c:if test="${requestScope.gameStatus eq 'In developing'}">
						<label for="team-two">Team Two:</label><input type="text"
							name="team-two" id="team-two" value="${requestScope.teamTwo}" />
					</c:if>
					<c:if test="${requestScope.gameStatus ne 'In developing'}">
						<label for="team-two">Team Two:</label><input type="text"
							name="team-two" id="team-two" value="${requestScope.teamTwo}" readonly/>
					</c:if>
					</div>

					<div class="form-row">
					<c:if test="${requestScope.gameStatus eq 'In developing' or requestScope.gameStatus eq 'Opened' or requestScope.gameStatus eq 'In progress'}">
						<label>Result:</label> <select name="resultId">
							<c:forEach var="item" items="${resultsMap}">
								<option value="${item.key}"
									${item.key == selectedRes ? 'selected="selected"' : ''}>
									${item.value}</option>
							</c:forEach>
						</select>
					</c:if>
					<c:if test="${requestScope.gameStatus eq 'Closed' or requestScope.gameStatus eq 'Canselled'}">
						<label>Result:</label> <select name="resultId" disabled>
							<c:forEach var="item" items="${resultsMap}">
								<option value="${item.key}"
									${item.key == selectedRes ? 'selected="selected"' : ''}>
									${item.value}</option>
							</c:forEach>
						</select>
					</c:if>
					</div>
					
					<div class="form-row">
					<c:if test="${requestScope.gameStatus eq 'In developing' or requestScope.gameStatus eq 'Opened' or requestScope.gameStatus eq 'In progress'}">
						<label for="start-date">Event Start Date:</label><input
							type="date" name="start-date" id="start-date"
							value="${requestScope.startDate}" />
					</c:if>
					<c:if test="${requestScope.gameStatus eq 'Closed' or requestScope.gameStatus eq 'Canselled'}">
						<label for="start-date">Event Start Date:</label><input
							type="date" name="start-date" id="start-date"
							value="${requestScope.startDate}" readonly/>
					</c:if>
					</div>

					<div class="form-row-time">
					<c:if test="${requestScope.gameStatus eq 'In developing' or requestScope.gameStatus eq 'Opened' or requestScope.gameStatus eq 'In progress'}">
						<label> Event Start Time:</label><input type="number" min="0"
							max="23" name="start-time-hours" id="start-time-hours"
							value="${requestScope.startTimeHours}" /> : <input type="number"
							min="0" max="59" name="start-time-minutes"
							id="start-time-minutes" value="${requestScope.startTimeMinutes}" />
					</c:if>
					<c:if test="${requestScope.gameStatus eq 'Closed' or requestScope.gameStatus eq 'Canselled'}">
							<label> Event Start Time:</label><input type="number" min="0"
							max="23" name="start-time-hours" id="start-time-hours"
							value="${requestScope.startTimeHours}" readonly/> : <input type="number"
							min="0" max="59" name="start-time-minutes"
							id="start-time-minutes" value="${requestScope.startTimeMinutes}" readonly/>
					</c:if>
					</div>


					<div class="form-row">
					<c:if test="${requestScope.gameStatus eq 'In developing' or requestScope.gameStatus eq 'Opened' or requestScope.gameStatus eq 'In progress'}">
						<label for="end-date">Event End Date:</label><input type="date"
							name="end-date" id="end-date" value="${requestScope.endDate}" />
					</c:if>
					<c:if test="${requestScope.gameStatus eq 'Closed' or requestScope.gameStatus eq 'Canselled'}">
						<label for="end-date">Event End Date:</label><input type="date"
							name="end-date" id="end-date" value="${requestScope.endDate}" readonly/>
					</c:if>
					</div>

					<div class="form-row-time">
					<c:if test="${requestScope.gameStatus eq 'In developing' or requestScope.gameStatus eq 'Opened' or requestScope.gameStatus eq 'In progress'}">
						<label> Event End Time:</label><input type="number" min="0"
							max="23" name="end-time-hours" id="end-time-hours"
							value="${requestScope.endTimeHours}" /> : <input type="number"
							min="0" max="59" name="end-time-minutes" id="end-time-minutes"
							value="${requestScope.endTimeMinutes}" />
					</c:if>
					<c:if test="${requestScope.gameStatus eq 'Closed' or requestScope.gameStatus eq 'Canselled'}">
						<label> Event End Time:</label><input type="number" min="0"
							max="23" name="end-time-hours" id="end-time-hours"
							value="${requestScope.endTimeHours}" readonly/> : <input type="number"
							min="0" max="59" name="end-time-minutes" id="end-time-minutes"
							value="${requestScope.endTimeMinutes}" readonly/>
					</c:if>
					</div>

					<div class="form-row">
					<c:if test="${requestScope.gameStatus eq 'In developing' or requestScope.gameStatus eq 'Opened' or requestScope.gameStatus eq 'In progress'}">
						<label>Status:</label> <select name="status">
							<c:forEach var="item" items="${statusMap}">
								<option value="${item.key}"
									${item.key == selectedStatus ? 'selected="selected"' : ''}>
									${item.value}</option>
							</c:forEach>
						</select>
					</c:if>
					
					<c:if test="${requestScope.gameStatus eq 'Closed' or requestScope.gameStatus eq 'Canselled'}">
						<label>Status:</label> <select name="status" disabled>
							<c:forEach var="item" items="${statusMap}">
								<option value="${item.key}"
									${item.key == selectedStatus ? 'selected="selected"' : ''}>
									${item.value}</option>
							</c:forEach>
						</select>
					</c:if>
					</div>

					<br>
				</fieldset>
				<div class="registration-submit">
					<input class="adminWorkBtn" type="submit" value="Save changes">
				</div>
			</form>

			<form action="Controller" method="post" name="unmatch-event">
				<input type="hidden" name="command" value="unmatch-event" /> <input
					type="hidden" name="eventId" value="${requestScope.eventId }" />
					<input type="hidden" name="gameId" value="${requestScope.gameId }" />

				<div class="registration-submit">
					<input class="adminWorkBtn" type="submit" value="Unmatch">
				</div>
			</form>
			
				<form action="Controller" method="post" name="delete-event">
				<input type="hidden" name="command" value="delete-event" /> <input
					type="hidden" name="eventId" value="${requestScope.eventId }" />
					<input type="hidden" name="gameId" value="${requestScope.gameId }" />
				<div class="registration-submit">
					<input class="adminWorkBtn" type="submit" value="Delete Event">
				</div>
			</form>
			
		</div>
	</div>


	<c:import url ="common/footer.jsp"/>

</body>
</html>