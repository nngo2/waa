<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add a Book</title>
</head>
<body>
	<form action="<c:url value='/books/${book.id}'/>" method="post">
		<table>
			<tr>
				<td>Title:</td>
				<td><input type="text" name="title" value="${book.title}" /></td>
			</tr>
			<tr>
				<td>ISBN:</td>
				<td><input type="text" name="ISBN" value="${book.ISBN}" /></td>
			</tr>
			<tr>
				<td>Author:</td>
				<td><input type="text" name="author" value="${book.author}" />
				</td>
			</tr>
			<tr>
				<td>Price:</td>
				<td><input type="number" name="price" value="${book.price}" />
				</td>
			</tr>
		</table>
		<input type="submit" value="Update" />
	</form>
	<form action="<c:url value='/books/delete?bookId=${book.id}'/>" method="post">
		<button type="submit">Delete</button>
	</form>
</body>
</html>