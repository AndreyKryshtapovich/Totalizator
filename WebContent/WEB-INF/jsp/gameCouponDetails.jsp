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
<title>Game Coupon Details Page</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.message" var="message" />
</head>
<body>
	<c:import url="common/header.jsp" />
	<div class="content">
		<div class="center clearfix">

			<c:if test="${not empty sessionScope.param_result}">
				<c:if test="${not sessionScope.param_result }">
					<c:out value="Invalid parameters for updating game." />
				</c:if>
			</c:if>


			<form action="Controller" method="post" name="edit-game-coupon">
				<fieldset>
					<legend class="legendInfo">Please insert game's info</legend>
					<div>
						<input type="hidden" name="command" value="edit-game-coupon" />
					</div>

					<div class="form-row">

						<label for="name">Drawing №:</label><input type="text"
							name="gameCouponId" id="gameCouponId"
							value="${requestScope.gameCouponId}" readonly />
					</div>

					<div class="form-row">
						<c:if test="${requestScope.gameStatus eq 'In developing'}">
							<label for="start-date">Game Start Date:</label>
							<input type="date" name="start-date" id="start-date"
								value="${requestScope.startDate}" />
						</c:if>
						<c:if test="${requestScope.gameStatus ne 'In developing'}">
							<label for="start-date">Game Start Date:</label>
							<input type="date" name="start-date" id="start-date"
								value="${requestScope.startDate}" readonly />
						</c:if>
					</div>

					<div class="form-row-time">
						<c:if test="${requestScope.gameStatus eq 'In developing'}">
							<label> Game Start Time:</label>
							<input type="number" min="0" max="23" name="start-time-hours"
								id="start-time-hours" value="${requestScope.startTimeHours}" /> : <input
								type="number" min="0" max="59" name="start-time-minutes"
								id="start-time-minutes" value="${requestScope.startTimeMinutes}" />
						</c:if>
						<c:if test="${requestScope.gameStatus ne 'In developing'}">
							<label> Game Start Time:</label>
							<input type="number" min="0" max="23" name="start-time-hours"
								id="start-time-hours" value="${requestScope.startTimeHours}"
								readonly /> : <input type="number" min="0" max="59"
								name="start-time-minutes" id="start-time-minutes"
								value="${requestScope.startTimeMinutes}" readonly />
						</c:if>
					</div>


					<div class="form-row">
						<c:if test="${requestScope.gameStatus eq 'In developing'}">
							<label for="end-date">Game End Date:</label>
							<input type="date" name="end-date" id="end-date"
								value="${requestScope.endDate}" />
						</c:if>
						<c:if test="${requestScope.gameStatus ne 'In developing'}">
							<label for="end-date">Game End Date:</label>
							<input type="date" name="end-date" id="end-date"
								value="${requestScope.endDate}" readonly />
						</c:if>
					</div>

					<div class="form-row-time">
						<c:if test="${requestScope.gameStatus eq 'In developing'}">
							<label> Game End Time:</label>
							<input type="number" min="0" max="23" name="end-time-hours"
								id="end-time-hours" value="${requestScope.endTimeHours}" /> : <input
								type="number" min="0" max="59" name="end-time-minutes"
								id="end-time-minutes" value="${requestScope.endTimeMinutes}" />
						</c:if>
						<c:if test="${requestScope.gameStatus ne 'In developing'}">
							<label> Game End Time:</label>
							<input type="number" min="0" max="23" name="end-time-hours"
								id="end-time-hours" value="${requestScope.endTimeHours}"
								readonly /> : <input type="number" min="0" max="59"
								name="end-time-minutes" id="end-time-minutes"
								value="${requestScope.endTimeMinutes}" readonly />
						</c:if>
					</div>

					<div class="form-row">
						<label>Status:</label> <select name="status">
							<c:forEach var="item" items="${statusMap}">
								<option value="${item.key}"
									${item.key == selectedStatus ? 'selected="selected"' : ''}>
									${item.value}</option>
							</c:forEach>
						</select>

					</div>


					<div class="form-row">
						<c:if test="${requestScope.gameStatus eq 'In developing'}">
							<label for="minBetAmount">Min. Bet. Amount:</label>
							<input type="number" min="0" name="minBetAmount" id="minBetAmount"
								value="${requestScope.minBetAmount}" />
						</c:if>
						<c:if test="${requestScope.gameStatus ne 'In developing'}">
							<label for="minBetAmount">Min. Bet. Amount:</label>
							<input type="number" min="0" name="minBetAmount" id="minBetAmount"
								value="${requestScope.minBetAmount}" readonly />
						</c:if>
					</div>

					<div class="form-row">
						<c:if test="${requestScope.gameStatus eq 'In developing'}">
							<label for="Jackpot">Jackpot:</label>
							<input type="number" min="0" name="jackpot" id="jackpot"
								value="${requestScope.jackpot}" />
						</c:if>

						<c:if test="${requestScope.gameStatus ne 'In developing'}">
							<label for="Jackpot">Jackpot:</label>
							<input type="number" min="0" name="jackpot" id="jackpot"
								value="${requestScope.jackpot}" readonly />
						</c:if>
					</div>

					<br>
				</fieldset>
				<div class="registration-submit">
					<input class="adminWorkBtn" type="submit" value="Save changes">
				</div>
			</form>




		</div>
	</div>


	<c:import url="common/footer.jsp" />
</body>
</html>