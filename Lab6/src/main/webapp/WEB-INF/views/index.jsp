<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="welcome" /></title>
</head>
<body>
	<p>
		<a href="/index?lang=en">English </a>
		<a href="/index?lang=es">Spanish</a>
	</p>
	<h1>
		<spring:message code="welcome" />
	</h1>
	<h1>
		<spring:message code="select" />
	</h1>
	<p>
		<a href="books"><spring:message code="book" /></a>
	</p>
	<p>
		<a href="cars"><spring:message code="car" /></a>
	</p>
</body>
</html>