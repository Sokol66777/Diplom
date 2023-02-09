<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <link href="/css/login.css" rel="stylesheet">
        <link href="/css/navBar.css" rel="stylesheet">
        <link href="/css/addFile.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Add post page</title>
    </head>
    <body>
        <ul class="navigation">
        <c:if test="${not empty user.image }">
            <li><img src="${pageContext.request.contextPath}/user/imageOnWelcomePage?idUser=${user.id}" class="avatar"/></li>
        </c:if>
        <li><a href="" ><c:out value="${user.username}"/></a>
            <ul>
                <li><a href="${pageContext.request.contextPath}/post/myPosts?idUserPost=${user.id}" title="My posts">My posts</a></li>
                <li><a href="${pageContext.request.contextPath}/user/update?updateUsersID=${user.id}" title="Update">Update</a></li>
                <li><a href="${pageContext.request.contextPath}/subscribeRequest/currentUserNotifications?idUser=${user.id}" title="My notifications">My notifications</a></li>

            </ul>
        </li>
        <li><a href="${pageContext.request.contextPath}/user/preSearchUser" title="Search User">Search user</a></li>
        <li><a href="${pageContext.request.contextPath}/user/welcome" title="Welcome">Welcome</a></li>
        <li><a href="${pageContext.request.contextPath}/user/logout" title="Logout">Logout</a></li>
        <security:authorize access = "hasRole('ROLE_admin')">
            <li><a href="${pageContext.request.contextPath}/user/allUsers?adminName=${user.username}" title="All users">All users</a></li>
        </security:authorize>
        <div class="clear"></div>
    </ul>

        <form class="login" action="${pageContext.request.contextPath}/post/add" method="post">
                <h1><c:out value="${errorMassage}"/></h1>
                <h1><c:out value="${message}"/></h1>
    			Name Post
            	<input type = "text" name="name"  required="required"/>

            	<p>Text</p>
                <textarea class="login" name = "text" rows = "7" cols ="100" required="required"></textarea>

                <input type = "hidden" name = "idUser" value = "<c:out value = '${user.id}'/>"/>
                <p><input name="hide" type="radio" value="true"> hide</p>
                <input name="hide" type="radio" value="false" checked> not hide
            	<p><input type = "submit" value="Add"/></p>
    	</form>

    	<form class="login" action="${pageContext.request.contextPath}/post/uploadPhoto" enctype="multipart/form-data" method="post">
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