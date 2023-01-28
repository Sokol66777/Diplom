<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>

</head>
<body>
    <h2>Oooops smth wrong, try later</h2>


    <button onclick = "location.href='${pageContext.request.contextPath}/user/welcome'"> welcome page </button>
</body>
</html>