<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Calculator Example</title>
</head>
<body>
	<section>
	<form method="post" action="<c:url value="/calc.do"/>">
		<fieldset>
			<legend>Example Calculator</legend>
			<div>
				<input name="add1" type="number" value="${add1}" required /> 
				<span> + </span> 
				<input name="add2" type="number" value="${add2}" required/> 
				<span> = </span> 
				<input name="addresult" type="number" value="${addResult}" />
			</div>
			<div>
				<input name="mul1" type="number" value="${mul1}" required/> 
				<span> + </span> 
				<input name="mul2" type="number" value="${mul2}" required/> 
				<span> = </span> 
				<input name="multresult" type="number" value="${mulResult}"/>
			</div>
			<div>
				<input name="submit" type="submit" value="  Submit  "/>
			</div>
		</fieldset>
	</form>
	</section>
</body>
</html>