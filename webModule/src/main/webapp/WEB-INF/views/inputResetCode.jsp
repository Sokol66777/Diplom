<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <h2>input code page</h2>
</head>
<body>
    <p><c:out value="${errorMessage}"/></p>
    <form action="${pageContext.request.contextPath}/resetPassword/checkResetCode" method="post">
    	<table>
			<tr>
    			<td>Write code which we send to your email</td>
        		<td><input type = "text" name="resetCode" required="required"/></td>
        		<input type = "hidden" name = "username" value = "<c:out value = '${username}'/>"/>
			</tr>
        	<tr>
        		<td><input type = "submit" value="OK"/></td>
			</tr>
		</table>
	</form>
    <button onclick = "location.href='${pageContext.request.contextPath}/user/logout'"> start page </button>

</body>
</html>