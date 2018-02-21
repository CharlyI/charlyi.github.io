<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>math: Регистрация</title>
</head>
<body>
<form method="POST">
	<label for="role">Ученик</label><input name="role" type="radio" value="student" <c:if test = "${role == 'student'}">checked="checked"</c:if>>
	<label for="role">Учитель</label><input name="role" type="radio" value="teacher" <c:if test = "${role == 'teacher'}">checked="checked"</c:if>><br/>
	<label for="email">E-mail</label><input type="text" name="email" placeholder="ivan@example.com" value="<c:out value="${email}" />"> <br/>
	<label for="lastname">Фамилия:</label><input type="text" name="lastname" value="<c:out value="${lastname}" />"> <br/>
	<label for="firstname">Имя:</label><input type="text" name="firstname" value="<c:out value="${firstname}" />"> <br/>
	<label for="patronymic">Отчество:</label><input type="text" name="patronymic" value="<c:out value="${patronymic}" />"> <br/>
	<label for="password">Пароль:</label><input type="password" name="password" placeholder="Ваш пароль" value="<c:out value="${password}" />"> <br/>
	<label for="repeat_password">Подтвердите пароль:</label><input type="password" name="repeat_password" placeholder="Ваш пароль" value="<c:out value="${repeat_password}" />"> <br/>
	<label><c:out value="${msg}" /></label> <br/>
	<input type="submit" value="Зарегистрироваться">
</form>
</body>
</html>