<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
        <h2>Posts of <c:out value="${usernameOfFriend}"/></h2>
</head>
    <body>
        <button onclick = "location.href='${pageContext.request.contextPath}/user/welcome'">welcome</button>

        <c:forEach var="post" items = "${postsOfFriend}">
            <c:if test="${post.hide=='false'}">
                <table>
                        <tr>
                            <td><c:out value = "${post.name}"/></td>
                            <security:authorize access = "hasRole('ROLE_admin')">
                                <td><button onclick = "location.href='${pageContext.request.contextPath}/post/delete?idPost=${post.id}'">Delete post</button><td>
                            </security:authorize>
                        </tr>
                        <tr>
                            <td><c:if test="${not empty post.image }">
                                <p><img src="${pageContext.request.contextPath}/post/viewPostImage?idPost=${post.id}" width="100"/></p>
                            </c:if></td>
                        </tr>
                        <tr>
                            <td><c:out value = "${post.text}"/> </td>
                        </tr>
                        <tr>
                            <td><a href="/comment/commentsOfPost?idPost=${post.id}">comments</a> <br></br></td>
                        </tr>
                </table>
            </c:if>
        </c:forEach>

    </body>
</html>