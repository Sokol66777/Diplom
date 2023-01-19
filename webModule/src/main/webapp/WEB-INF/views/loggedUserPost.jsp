<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
        <h2>My posts</h2>
    </head>
    <body>
        <table>
            <c:forEach var="post" items = "${myPosts}">
                <tr>
                    <td><c:out value = "${post.name}"/></td>
                    <td><button onclick = "location.href='${pageContext.request.contextPath}/post/deletePost?idPost=${post.id}'">Delete post</button><td>
                    <td><button onclick = "location.href='${pageContext.request.contextPath}/post/update?idPost=${post.id}'">Update post</button><td>
                </tr>
                <tr>
                    <td><c:out value = "${post.text}"/></td>
                </tr>
                <p></p>
            </c:forEach>
        </table>
        <button onclick = "location.href='${pageContext.request.contextPath}/user/welcome'">welcome</button>
        <button onclick = "location.href='${pageContext.request.contextPath}/post/add'">Add post</button>
    </body>
</html>