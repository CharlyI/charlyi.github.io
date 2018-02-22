<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>math: <c:out value="${teacher.getFullName()}" /> - Личный кабинет учителя</title>
</head>
<body>
<div><c:out value="${teacher.getFullName()}" /></div>
<div><a href="tests">Мои тесты</a></div>
</body>
</html>