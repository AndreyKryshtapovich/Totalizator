<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="print"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="https://fonts.googleapis.com/css?family=Jura"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="CSS/stylecss.css">
<link rel="stylesheet" type="text/css" href="CSS/registrationcss.css">
<title>user page</title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.message" var="message" />
<fmt:message bundle="${loc}" key="local.createBet" var="createBet" />
<fmt:message bundle="${loc}" key="local.minBetAmount" var="minBetAmount" />
<fmt:message bundle="${loc}" key="local.drawing" var="drawing" />
<fmt:message bundle="${loc}" key="local.betAmount" var="betAmount" />
<fmt:message bundle="${loc}" key="local.eventName" var="eventName" />
<fmt:message bundle="${loc}" key="local.gameDates" var="gameDates" />
</head>
<body>

	<c:import url="common/userHeader.jsp" />

	<div class="content">
		<div class="center clearfix">

			<h2>${message}${sessionScope.login}</h2>

			<c:if test="${empty events.list}">
				<c:out
					value="You can not make bet right now. There are no available game coupons." />
			</c:if>

			<c:if test="${not empty events.list}">
				<h3>${minBetAmount }${requestScope.minBetAmount}$</h3>
				<h3>${drawing }№:${requestScope.drawing}</h3>
			</c:if>

			<c:if test="${not empty sessionScope.result}">
				<c:if test="${sessionScope.result }">
					<c:out value="Your bet registrated successfully." />
				</c:if>

				<c:if test="${not sessionScope.result }">
					<c:out
						value="We can not acsept your bet right now. Please try again later." />
				</c:if>
			</c:if>

			<jsp:useBean id="events"
				class="by.epamtr.totalizator.bean.listbean.JSPListBean"
				scope="request" />
			<div>
				<form action="Controller" method="get" name="go-to-bet-submit">
					<div>
						<input type="hidden" name="command" value="go-to-bet-submit" />
					</div>
					
					<c:if test="${not empty events.list}">
						<print:jsptable list="${events}" colunmName1="${gameDates }"
							colunmName2="${eventName }" colunmName3="1" colunmName4="X"
							colunmName5="2" />

						<div class='form-row'>
							<label> ${betAmount }</label> <input type='number' min='1'
								name='bet-amount' required />
						</div>
						<div class="registration-submit">
							<input class="btn-register" type="submit" value="${createBet}">
						</div>
					</c:if>
				</form>
			</div>
		</div>
	</div>

	<c:import url="common/footer.jsp" />
</body>
</html>