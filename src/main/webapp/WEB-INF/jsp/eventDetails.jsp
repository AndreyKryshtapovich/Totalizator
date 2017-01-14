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
<link rel="stylesheet" type="text/css" href="CSS/admincss.css">
<title>Event Details Page</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.message" var="message" />
<fmt:message bundle="${loc}" key="local.gameCouponId" var="gameCouponId" />
<fmt:message bundle="${loc}" key="local.gameDates" var="gameDates" />
<fmt:message bundle="${loc}" key="local.teamOne" var="teamOne" />
<fmt:message bundle="${loc}" key="local.teamTwo" var="teamTwo" />

<fmt:message bundle="${loc}" key="local.editGame" var="editGame" />
<fmt:message bundle="${loc}" key="local.closeGame" var="closeGame" />

<fmt:message bundle="${loc}" key="local.result" var="result" />
<fmt:message bundle="${loc}" key="local.startDate" var="startDate" />
<fmt:message bundle="${loc}" key="local.endDate" var="endDate" />
<fmt:message bundle="${loc}" key="local.status" var="status" />
<fmt:message bundle="${loc}" key="local.eventName" var="eventName" />
<fmt:message bundle="${loc}" key="local.edit" var="edit" />

</head>
<body>
	<c:import url="common/header.jsp" />
	<div class="content">
		<div class="center clearfix">

			<jsp:useBean id="events"
				class="by.epamtr.totalizator.bean.listbean.JSPListBean"
				scope="request" />


			<c:out
				value="${gameCouponId } ${gameCupounId} ${gameDates } ${gameStartDate} - ${gameEndDate}"></c:out>
		<br>
			<c:if test="${not empty sessionScope.param_result}">
				<c:if test="${sessionScope.param_result }">
					<c:out value="Game was successfully updated." />
				</c:if>
			</c:if>


			<print:eventDetailsTable eventsList="${events}"
				eventName="${eventName }" teamOne="${teamOne }" teamTwo="${teamTwo }"
				result="${result }" startDate="${startDate }" endDate="${endDate }"
				status="${status }" edit="${edit }" />

			<c:if test="${requestScope.InProgressFlag }">
				<div>
					<form action="Controller" method="post" name="close-game-coupon">
						<div>
							<input type="hidden" name="command" value="close-game-coupon" />
							<input type="hidden" name="gameCouponId" value="${gameCupounId}" />
						</div>
						<div class="registration-submit">
							<input class="adminWorkBtn" type="submit" value="${closeGame }">
						</div>
					</form>
				</div>

			</c:if>


			<div>
				<form action="Controller" method="get"
					name="go-to-game-coupon-details">
					<div>
						<input type="hidden" name="command"
							value="go-to-game-coupon-details" /> <input type="hidden"
							name="gameCouponId" value="${gameCupounId}" />
					</div>
					<div class="registration-submit">
						<input class="adminWorkBtn" type="submit" value="${editGame }">
					</div>
				</form>
			</div>


		</div>
	</div>


	<c:import url="common/footer.jsp" />
</body>
</html>