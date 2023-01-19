<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <h2>Registration page</h2>
</head>
<body>
    <p><c:out value="${errorMassage}"/></p>
    <form action="${pageContext.request.contextPath}/addUser" method="post">
    	<table>
			<tr>
    			<td>User Name</td>
        		<td><input type = "text" name="username" required="required"/></td>
			</tr>
    		<tr>
    			<td>Password</td>
        		<td><input type = "password" name="password" required="required"/></td>
			</tr>
			<tr>
                <td>Confirm password</td>
                <td><input type = "password" name="confirmedPassword" required="required"/></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type = "email" name="email" required="required"/></td>
                <td><input type="hidden" name="role" value="user"></td>
            </tr>
            <tr>
                <td><img src="${pageContext.request.contextPath}/user/viewImage" width="100"/></td>
            </tr>
        	<tr>
        		<td><input type = "submit" value="Registration"/></td>
			</tr>
		</table>
	</form>

	<form action="${pageContext.request.contextPath}/user/uploadPhoto" enctype="multipart/form-data" method="post">
        <p>Выберите ваше фото</p>
        <p><input type="file" name="fileData" >
        <input type="submit" value="Загрузить"></p>
    </form>

		<button onclick = "location.href='${pageContext.request.contextPath}/'">Start Page </button>
</body>
</html>