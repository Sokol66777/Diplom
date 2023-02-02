<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <link href="/css/login.css" rel="stylesheet">
    <link href="/css/navBar.css" rel="stylesheet">
    <link href="/css/addFile.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <title>Registration page</title>
</head>
<body>

    <ul class="navigation">
        <li><a href="${pageContext.request.contextPath}/login" title="sing in">sign in</a></li>
        <li><a href="${pageContext.request.contextPath}/addUser" title="sing up">sign up</a></li>
      <li><a href="${pageContext.request.contextPath}/resetPassword" title="Forgot password">Forgot password</a></li>
      <li><a href="" title="Contact">Contact</a></li>
      <div class="clear"></div>
    </ul>

    <p><c:out value="${errorMassage}"/></p>
    <form class="login" action="${pageContext.request.contextPath}/addUser" method="post">

        <c:if test="${not empty imageForm.image }">
            <img src="${pageContext.request.contextPath}/user/viewImage" width="100"/>
        </c:if>

    	<p><label>User Name</label>
        <input type = "text" name="username" required="required"/></p>

        <p><label>Password</label></p>
        <input type = "password" name="password" required="required"/>

        <p><label>Confirm password</label>
        <input type = "password" name="confirmedPassword" required="required"/></p>

        <p><label>Email</label></p>
        <input type = "email" name="email" required="required"/>
        <input type="hidden" name="role" value="user">

        <input type = "submit" value="Registration"/>
	</form>

	<form class="login" action="${pageContext.request.contextPath}/user/uploadPhoto" enctype="multipart/form-data" method="post">
        <div class="addFile">
          <div class="form-group">
            <label class="label">
              <i class="material-icons">attach_file</i>
              <span class="title">Добавить файл</span>
              <input type="file" name="fileData">
            </label>
          </div>
        </div>
        <input type="submit" value="Загрузить">
    </form>
</body>
</html>