<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>

</head>
<body>
    <p><c:out value="${errorMassage}"/></p>
    <p>Welcome,
       <c:out value="${user.username}"/></p>
       <p><img src="${pageContext.request.contextPath}/user/imageOnWelcomePage" width="100"/></p>



    <security:authorize access = "hasRole('ROLE_admin')">

    </security:authorize>

    <security:authorize access = "hasRole('ROLE_user')">

    </security:authorize>
    <p></p>
    <button onclick = "location.href='${pageContext.request.contextPath}/user/logout'"> logout </button>
    <button onclick = "location.href='${pageContext.request.contextPath}/user/update?updateUsersID=${user.id}'">Update</button>
    <button onclick = "location.href='${pageContext.request.contextPath}/post/myPosts?idUserPost=${user.id}'"> my posts </button>

    <security:authorize access = "hasRole('ROLE_admin')">
       <button onclick = "location.href='${pageContext.request.contextPath}/user/allUsers?adminName=${user.username}'">All users</button>
    </security:authorize>




</body>
</html>