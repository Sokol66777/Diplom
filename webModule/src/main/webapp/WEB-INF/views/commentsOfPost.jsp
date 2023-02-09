<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <link href="/css/login.css" rel="stylesheet">
    <link href="/css/navBar.css" rel="stylesheet">
    <title>Comments</title>
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


    <p><c:out value="${errorMassage}"/></p>
    <p><c:out value="${message}"/></p>

     <div class="login">
        <c:if test="${not empty post.user.image }">
            <img src="${pageContext.request.contextPath}/user/imageOnWelcomePage?idUser=${post.user.ID}" class="avatar"/>
        </c:if>

        <a href="${pageContext.request.contextPath}/user/friendUser?idFriendUser=${post.user.ID}"><c:out value="${post.user.username}"/></a>

        <p><c:out value = "${post.name}"/></p>

        <c:if test="${not empty post.image }">
            <p><img src="${pageContext.request.contextPath}/post/viewPostImage?idPost=${post.ID}" width="300" height="400"/></p>
        </c:if>

        <c:out value = "${post.text}"/>
     </div>


     <form class="login" action="${pageContext.request.contextPath}/comment/add" method="post">
             <h1>Add comment</h1>
             <label class="label">Text</label>
             <textarea name = "text" rows = "7" cols ="100" required="required"></textarea>

             <input type = "hidden" name = "idUser" value = "<c:out value = '${user.id}'/>"/>
             <input type = "hidden" name = "idPost" value = "<c:out value = '${post.ID}'/>"/>
             <input type = "hidden" name = "name" value = "<c:out value = '${user.username}'/>"/>
             <input type = "submit" value="Add"/>
     </form>


     <div class="login">
        <h1><p>Comments</p></h1>

        <c:forEach var="comment" items = "${comments}">

            <p><c:if test="${not empty comment.user.image }">
                <img src="${pageContext.request.contextPath}/user/imageOnWelcomePage?idUser=${comment.user.ID}" class="avatar"/>
            </c:if>

           <a href="/user/friendUser?idFriendUser=${comment.user.ID}"><c:out value="${comment.user.username}"/></a>

            <c:if test="${comment.user.ID==user.id or user.role=='admin'}">
                <button onclick = "location.href='${pageContext.request.contextPath}/comment/delete?idComment=${comment.ID}&idPost=${comment.post.ID}'">delete</button>
            </c:if>

            <c:if test="${comment.user.ID==user.id}">
                <button onclick = "location.href='${pageContext.request.contextPath}/comment/update?idComment=${comment.ID}'">update</button>
            </c:if></p>


            <c:out value = "${comment.text}"/>

        </c:forEach>

     </div>





