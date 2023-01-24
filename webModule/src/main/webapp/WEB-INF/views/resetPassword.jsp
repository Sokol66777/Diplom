<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <h2>Reset password page</h2>
</head>
<body>
    <p><c:out value="${errorMessage}"/></p>
    <form action="${pageContext.request.contextPath}/resetPassword" method="post">
    	<table>
			<tr>
    			<td>Write your username and we will send code for reset password on email</td>
        		<td><input type = "text" name="username" required="required"/></td>
			</tr>
        	<tr>
        		<td><input type = "submit" value="OK"/></td>
			</tr>
		</table>
	</form>
    <button onclick = "location.href='${pageContext.request.contextPath}/user/logout'"> start page </button>
</body>
</html>