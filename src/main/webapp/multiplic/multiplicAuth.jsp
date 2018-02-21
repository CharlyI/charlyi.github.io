<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${context}/css/multiplic/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>math: Таблица умножения</title>
</head>
<body>
<div class="content">
	<div id="multiplicAuthForm" class="board">
		<form name="usernameForm" method="POST">
			<label for="username">Имя </label><input type="text" name="username"/>
			<select name="mode">
				<option value="all">Все</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
			</select>
			<input type="submit" value="OK">
		</form>
	</div>
</div>
</body>
</html>