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
<link rel="stylesheet" type="text/css" href="CSS/registrationcss.css">
<link rel="stylesheet" type="text/css" href="CSS/stylecss.css">
<link rel="stylesheet" type="text/css" href="CSS/admincss.css">
<title>Matching page</title>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
</head>
<body>

	<c:import url ="common/header.jsp"/>

	<div class="content">
		<div class="center clearfix">
	<jsp:useBean id="games"
		class="by.epamtr.totalizator.bean.listbean.JSPGameListBean"
		scope="request" />


	<form action='Controller' method='get' name='search-matching-events'>
		<input type="hidden" name="command" value="search-matching-events" />
		<div class="form-row">
			<label>Game In Development:</label>
			<print:dropdownGamePopulation gamesList="${games}" />
		</div>
		<div class="user">
		<input type='submit' class="adminWorkBtn" value='Search Events'>
		</div>
	</form>
	</div>
	</div>
	<c:import url ="common/footer.jsp"/>
</body>
</html>