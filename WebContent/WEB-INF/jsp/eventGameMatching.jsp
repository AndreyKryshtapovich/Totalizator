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
				value="Game Cupoun Id: ${gameCupounId} Game Dates: ${gameStartDate} - ${gameEndDate}"></c:out>
			<form action="Controller" method="post" name="event-game-matching">
				<input type="hidden" name="command" value="event-game-matching" />
				<div>
					<label>Unmatched Event:</label>
					<div class="form-row">
						<print:dropdownEventPopulation eventsList="${events}" />
					</div>
					<print:jspEventsTable matchedEventsList="${matchedEvents}" />
				</div>
			</form>
		</div>
	</div>
	<c:import url ="common/footer.jsp"/>
</body>
</html>