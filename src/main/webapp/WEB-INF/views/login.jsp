<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<link
	href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/common.css" />"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<form method="POST" action="/login" class="form-signin">
			<div class="form-group">
				<input type="text" path="username" id="username" name="username"
					class="form-control" placeholder="Tên đăng nhập"></input>
			</div>

			<div class="form-group">
				<input type="password" path="password" id="password" name="password"
					class="form-control" placeholder="Mật khẩu"></input>
			</div>
			<c:if test="${incorrect_account != null}">
				<p>${incorrect_account}</p>
			</c:if>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Đăng
				nhập</button>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
</body>
</html>