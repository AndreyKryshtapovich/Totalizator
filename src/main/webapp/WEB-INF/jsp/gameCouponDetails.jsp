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
<fmt:message bundle="${loc}" key="local.minBetAmount" var="minBetAmount" />
<fmt:message bundle="${loc}" key="local.gameStartTime"
	var="gameStartTime" />
<fmt:message bundle="${loc}" key="local.gameEndTime" var="gameEndTime" />
<fmt:message bundle="${loc}" key="local.startDate" var="startDate" />

<fmt:message bundle="${loc}" key="local.endDate" var="endDate" />
<fmt:message bundle="${loc}" key="local.insertGameInfo" var="insertGameInfo" />
<fmt:message bundle="${loc}" key="local.drawing" var="drawing" />
<fmt:message bundle="${loc}" key="local.status" var="status" />
<fmt:message bundle="${loc}" key="local.jackpot" var="jackpot" />
<fmt:message bundle="${loc}" key="local.saveChanges" var="saveChanges" />

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
					<legend class="legendInfo">${insertGameInfo }</legend>
					<div>
						<input type="hidden" name="command" value="edit-game-coupon" />
					</div>

					<div class="form-row">

						<label for="name">${drawing } №:</label><input type="text"
							name="gameCouponId" id="gameCouponId"
							value="${requestScope.gameCouponId}" readonly />
					</div>

					<div class="form-row">
						<c:if test="${requestScope.gameStatus eq 'In developing'}">
							<label for="start-date">${startDate }:</label>
							<input type="date" name="start-date" id="start-date"
								value="${requestScope.startDate}" />
						</c:if>
						<c:if test="${requestScope.gameStatus ne 'In developing'}">
							<label for="start-date">${startDate }:</label>
							<input type="date" name="start-date" id="start-date"
								value="${requestScope.startDate}" readonly />
						</c:if>
					</div>

					<div class="form-row-time">
						<c:if test="${requestScope.gameStatus eq 'In developing'}">
							<label> ${gameStartTime }</label>
							<input type="number" min="0" max="23" name="start-time-hours"
								id="start-time-hours" value="${requestScope.startTimeHours}" /> : <input
								type="number" min="0" max="59" name="start-time-minutes"
								id="start-time-minutes" value="${requestScope.startTimeMinutes}" />
						</c:if>
						<c:if test="${requestScope.gameStatus ne 'In developing'}">
							<label> ${gameStartTime }</label>
							<input type="number" min="0" max="23" name="start-time-hours"
								id="start-time-hours" value="${requestScope.startTimeHours}"
								readonly /> : <input type="number" min="0" max="59"
								name="start-time-minutes" id="start-time-minutes"
								value="${requestScope.startTimeMinutes}" readonly />
						</c:if>
					</div>


					<div class="form-row">
						<c:if test="${requestScope.gameStatus eq 'In developing'}">
							<label for="end-date">${endDate }:</label>
							<input type="date" name="end-date" id="end-date"
								value="${requestScope.endDate}" />
						</c:if>
						<c:if test="${requestScope.gameStatus ne 'In developing'}">
							<label for="end-date">${endDate }:</label>
							<input type="date" name="end-date" id="end-date"
								value="${requestScope.endDate}" readonly />
						</c:if>
					</div>

					<div class="form-row-time">
						<c:if test="${requestScope.gameStatus eq 'In developing'}">
							<label> ${gameEndTime }</label>
							<input type="number" min="0" max="23" name="end-time-hours"
								id="end-time-hours" value="${requestScope.endTimeHours}" /> : <input
								type="number" min="0" max="59" name="end-time-minutes"
								id="end-time-minutes" value="${requestScope.endTimeMinutes}" />
						</c:if>
						<c:if test="${requestScope.gameStatus ne 'In developing'}">
							<label> ${gameEndTime }</label>
							<input type="number" min="0" max="23" name="end-time-hours"
								id="end-time-hours" value="${requestScope.endTimeHours}"
								readonly /> : <input type="number" min="0" max="59"
								name="end-time-minutes" id="end-time-minutes"
								value="${requestScope.endTimeMinutes}" readonly />
						</c:if>
					</div>

					<div class="form-row">
						<label>${status }:</label> <select name="status">
							<c:forEach var="item" items="${statusMap}">
								<option value="${item.key}"
									${item.key == selectedStatus ? 'selected="selected"' : ''}>
									${item.value}</option>
							</c:forEach>
						</select>

					</div>


					<div class="form-row">
						<c:if test="${requestScope.gameStatus eq 'In developing'}">
							<label for="minBetAmount">${minBetAmount }</label>
							<input type="number" min="0" name="minBetAmount" id="minBetAmount"
								value="${requestScope.minBetAmount}" />
						</c:if>
						<c:if test="${requestScope.gameStatus ne 'In developing'}">
							<label for="minBetAmount">${minBetAmount }</label>
							<input type="number" min="0" name="minBetAmount" id="minBetAmount"
								value="${requestScope.minBetAmount}" readonly />
						</c:if>
					</div>

					<div class="form-row">
						<c:if test="${requestScope.gameStatus eq 'In developing'}">
							<label for="Jackpot">${jackpot }:</label>
							<input type="number" min="0" name="jackpot" id="jackpot"
								value="${requestScope.jackpot}" />
						</c:if>

						<c:if test="${requestScope.gameStatus ne 'In developing'}">
							<label for="Jackpot">${jackpot }:</label>
							<input type="number" min="0" name="jackpot" id="jackpot"
								value="${requestScope.jackpot}" readonly />
						</c:if>
					</div>

					<br>
				</fieldset>
				<div class="registration-submit">
					<input class="adminWorkBtn" type="submit" value="${saveChanges }">
				</div>
			</form>




		</div>
	</div>


	<c:import url="common/footer.jsp" />
</body>
</html>