<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add a Car</title>
</head>
<body>
	<form action="/cars" method="post">
	<table>
		<tr>
			<td>Make:</td>
			<td><input type="text" name="make" /> </td>
		</tr>
		<tr>
			<td>Model:</td>
			<td><input type="text" name="model" /> </td>
		</tr>
		<tr>
			<td>Year:</td>
			<td><input type="text" name="year" /> </td>
		</tr>
		<tr>
			<td>Color:</td>
			<td><input type="text" name="color" /> </td>
		</tr>
	</table>
	<input type="submit"/>
	<sec:csrfInput/>
	</form>
</body>
</html>