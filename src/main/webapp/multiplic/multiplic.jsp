<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${context}/css/multiplic/style.css">
<script type="text/javascript" src="${context}/javascript/jquery-3.2.1.js"></script>
<script type="text/javascript" src="${context}/javascript/multiplic.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>math: Таблица умножения</title>
</head>
<body>
<div class="content">
	<div id="multiplicTable">
		<div id="msg">Удачи!</div>
			<!-- multiplication table -->
		<div class="board">
			<span id="varA"></span>
			<span id="varA"></span>
			<span> x </span>
			<span id="varB"></span>
			<span> = </span>
			<input type="text" id="answer" size="3" />
			<input type="submit" value="OK" id="OKButton"/>
		</div>
		<div id="progress">
		<span id="count"></span>
		<span> из </span>
		<span id="total"></span>
		</div>
	</div>
	
</div>
<input type="hidden" id="context" value="${context}">
<input type="hidden" id="mode" value="${mode}">
</body>
</html>