<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>

    </head>
    <body>
        <p><button onclick = "location.href='${pageContext.request.contextPath}/user/welcome'">welcome</button>
        <button onclick = "location.href='${pageContext.request.contextPath}/post/add'">Add post</button></p>
        <p><h2>My posts</h2></p>


        <table>
            <c:forEach var="post" items = "${myPosts}">
                    <tr>
                        <td><c:out value = "${post.name}"/></td>

                        <td><button onclick = "location.href='${pageContext.request.contextPath}/post/delete?idPost=${post.id}'">Delete post</button><td>
                        <td><button onclick = "location.href='${pageContext.request.contextPath}/post/update?idPost=${post.id}'">Update post</button><td>
                    </tr>
                    <tr>
                        <td><c:if test="${not empty post.image }">
                            <p><img src="${pageContext.request.contextPath}/post/viewPostImage?idPost=${post.id}" width="100"/></p>
                        </c:if></td>
                    </tr>
                    <tr>
                        <td><c:out value = "${post.text}"/></td>
                    </tr>
                    <tr>
                        <td><a href="/comment/commentsOfPost?idPost=${post.id}">comments</a> <br><br></td>
                    </tr>
            </c:forEach>
        </table>
    </body>
</html>