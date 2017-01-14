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

<title>Matching page</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.gameCouponId" var="gameCouponId" />
<fmt:message bundle="${loc}" key="local.gameDates" var="gameDates" />
<fmt:message bundle="${loc}" key="local.unmatchedEvent" var="unmatchedEvent" />
<fmt:message bundle="${loc}" key="local.teamOne" var="teamOne" />
<fmt:message bundle="${loc}" key="local.teamTwo" var="teamTwo" />
<fmt:message bundle="${loc}" key="local.startDate" var="startDate" />
<fmt:message bundle="${loc}" key="local.endDate" var="endDate" />
<fmt:message bundle="${loc}" key="local.eventName" var="eventName" />
<fmt:message bundle="${loc}" key="local.submitMatching" var="submitMatching" />


</head>
<body>

	<c:import url ="common/header.jsp"/>


	<div class="content">
		<div class="center clearfix">

			<jsp:useBean id="events"
				class="by.epamtr.totalizator.bean.listbean.JSPListBean"
				scope="request" />

			<jsp:useBean id="matchedEvents"
				class="by.epamtr.totalizator.bean.listbean.JSPListBean"
				scope="request" />

			<c:out
				value="${gameCouponId } ${gameCupounId} ${gameDates } ${gameStartDate} - ${gameEndDate}"></c:out>
			<form action="Controller" method="post" name="event-game-matching">
				<input type="hidden" name="command" value="event-game-matching" />
				<div>
					<label>${unmatchedEvent }</label>
					<div class="form-row">
						<print:dropdownEventPopulation eventsList="${events}" submitMatching="${submitMatching }"  />
					</div><span class="err"
						id="err-dropdown"></span>
					<print:jspEventsTable matchedEventsList="${matchedEvents}" eventName="${eventName }" teamOne="${teamOne }"
					teamTwo="${teamTwo }" startDate="${startDate }" endDate="${endDate }" />
				</div>
			</form>
		</div>
	</div>
	<c:import url ="common/footer.jsp"/>
	<script src="JS/dropdownEventValidation.js"></script>
</body>
</html>