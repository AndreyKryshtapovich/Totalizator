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
</head>
<body>
	<c:import url="common/header.jsp" />
	<div class="content">
		<div class="center clearfix">

			<jsp:useBean id="events"
				class="by.epamtr.totalizator.bean.listbean.JSPListBean"
				scope="request" />


			<c:out
				value="Game Cupoun Id: ${gameCupounId} Game Dates: ${gameStartDate} - ${gameEndDate}"></c:out>
			<print:eventDetailsTable eventsList="${events}"
				eventName="Event Name" teamOne="Team One" teamTwo="TeamTwo"
				result="Result" startDate="Start Date" endDate="End Date"
				status="Status" />

			<c:if test="${requestScope.InProgressFlag }">
				<div>
					<form action="Controller" method="post" name="close-game-coupon">
						<div>
							<input type="hidden" name="command" value="close-game-coupon" />
							<input type="hidden" name="gameCouponId" value= "${gameCupounId}" />
						</div>
						<div class="registration-submit">
							<input class="adminWorkBtn" type="submit" value="Close Cur. GC ">
						</div>
					</form>
				</div>

			</c:if>
		</div>
	</div>


	<c:import url="common/footer.jsp" />
</body>
</html>