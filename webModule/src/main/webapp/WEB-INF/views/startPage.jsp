<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
    <button onclick = "location.href='${pageContext.request.contextPath}/login'">Login </button>
	<button onclick = "location.href='${pageContext.request.contextPath}/addUser'">Add user </button>
	<button onclick = "location.href='${pageContext.request.contextPath}/resetPassword'">Forgot password </button>

</body>
</html>