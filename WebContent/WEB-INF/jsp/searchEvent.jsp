<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="print"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Matching page</title>
</head>
<body>
	<jsp:useBean id="games"
		class="by.epamtr.totalizator.bean.listbean.JSPGameListBean"
		scope="request" />

<%-- 	<jsp:useBean id="events"
		class="by.epamtr.totalizator.bean.listbean.JSPListBean"
		scope="request" /> --%>



	<form action='Controller' method='get' name='search-matching-events'>
		<input type="hidden" name="command" value="search-matching-events" />
		<div>
			<label>Game In Development:</label>
			<print:dropdownGamePopulation gamesList="${games}" />
		</div>
		<input type='submit' value='Search Events'>
	</form>
	<%-- 	<div>
		<label>Unmatched Event:</label>
		<print:dropdownEventPopulation eventsList="${events}" />
	</div>	
		<input type='submit' value='Submit Matching'>
	</form> --%>

</body>
</html>