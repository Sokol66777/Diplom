<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <link href="/css/login.css" rel="stylesheet">
    <link href="/css/navBar.css" rel="stylesheet">
    <title>Input code </title>
</head>
<body>

    <ul class="navigation">
        <li><a href="${pageContext.request.contextPath}/login" title="sing in">sign in</a></li>
        <li><a href="${pageContext.request.contextPath}/addUser" title="sing up">sign up</a></li>
        <li><a href="${pageContext.request.contextPath}/resetPassword" title="Forgot password">Forgot password</a></li>
        <li><a href="" title="Contact">Contact</a></li>
        <div class="clear"></div>

    </ul>


    <form class="login" action="${pageContext.request.contextPath}/resetPassword/setResetPassword" method="post">
    	<p><c:out value="${errorMessage}"/></p>
    	<label>New password</label>
        <input type = "password" name="password" required="required"/>
		<label>Confirm password</label>
        <input type = "password" name="confirmedPassword" required="required"/>
        <input type = "hidden" name = "username" value = "<c:out value = '${username}'/>"/>
		<input type = "submit" value="OK"/>
	</form>
</body>
</html>