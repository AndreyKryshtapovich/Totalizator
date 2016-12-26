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
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.createBet" var="createBet" />
<fmt:message bundle="${loc}" key="local.betAmount" var="betAmount" />
<fmt:message bundle="${loc}" key="local.creditCardNumber" var="creditCardNumber" />

<title>Bet confirmation</title>
</head>
<body>
	<c:import url ="common/userHeader.jsp"/>

	<div class="content">
		<div class="center clearfix">

			<jsp:useBean id="events"
				class="by.epamtr.totalizator.bean.listbean.JSPListBean"
				scope="request" />

			<jsp:useBean id="userResultMap"
				class="by.epamtr.totalizator.bean.listbean.JSPMapBean"
				scope="request" />

			<c:if test="${not empty sessionScope.result}">

				<c:if test="${sessionScope.result }">
					<print:betSubmitTable list="${events}" colunmName1="Event"
						colunmName2="Result" userResultsMap="${userResultMap }"
						betAmount="${requestScope.betAmount }" />

					<div>
						<form action="Controller" method="post" name="make-bet">
							<div>
								<input type="hidden" name="command" value="make-bet" />
							</div>

							<div class="form-row">
								<label>${creditCardNumber }</label> <input type="text"
									pattern="[0-9]{16}" name="credit-card" id="credit-card"
									required title="16 numbers of credit card number" />
							</div>

							<div class='form-row'>
								<label> ${betAmount }</label> <input type='number' min='1'
									name='bet-amount' value=${requestScope.betAmount } readonly />
							</div>

							<div class="registration-submit">
								<input class="btn-register" type="submit" value="${createBet} ">
							</div>

						</form>
					</div>
				</c:if>
				<c:if test="${not sessionScope.result }">
					<c:out
						value="Please provide result for each event on the previous page." />
					<c:if test="${not sessionScope.betAmountResult }">
						<c:out value="Bet amount is less than min. bet amount." />
					</c:if>
				</c:if>
			</c:if>
		</div>
	</div>


	<c:import url ="common/footer.jsp"/>
</body>
</html>