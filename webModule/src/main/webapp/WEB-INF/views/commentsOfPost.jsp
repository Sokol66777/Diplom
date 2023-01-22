<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
    <p><c:out value="${errorMassage}"/></p>
    <p><c:out value="${message}"/></p>

     <table>
        <tr>
            <td><c:out value = "${post.name}"/></td>
        </tr>
        <tr>
            <td><c:if test="${not empty post.image }">
                <p><img src="${pageContext.request.contextPath}/post/viewPostImage?idPost=${post.id}" width="100"/></p>
            </c:if></td>
        </tr>
        <tr>
            <td><c:out value = "${post.text}"/> <br></br> </td>
        </tr>
     </table>
     <button onclick = "location.href='${pageContext.request.contextPath}/user/welcome'">welcome</button>


     <h2>Comments</h2>

     <form action="${pageContext.request.contextPath}/comment/add" method="post">
        <table>
            <tr>
                <td>Text</td>
                <td><textarea name = "text" rows = "7" cols ="100" required="required"></textarea></td>
            </tr>
            <input type = "hidden" name = "idUser" value = "<c:out value = '${user.id}'/>"/>
            <input type = "hidden" name = "idPost" value = "<c:out value = '${post.id}'/>"/>
            <input type = "hidden" name = "name" value = "<c:out value = '${user.username}'/>"/>
            <tr>
                <td><input type = "submit" value="Add"/></td>
            </tr>
        </table>
     </form>



    <table>
        <c:forEach var="comment" items = "${comments}">
            <tr>
                <td><a href="/user/friendUser?idFriendUser=${comment.idUser}"><c:out value="${comment.username}"/></a></td>

                <td><c:if test="${comment.idUser==user.id or user.role=='admin'}">
                    <button onclick = "location.href='${pageContext.request.contextPath}/comment/delete?idComment=${comment.id}&idPost=${comment.idPost}'">delete</button>
                </c:if></td>

                <td><c:if test="${comment.idUser==user.id}">
                    <button onclick = "location.href='${pageContext.request.contextPath}/comment/update?idComment=${comment.id}'">update</button>
                </c:if></td>

            </tr>
            <tr>
                <td><c:out value = "${comment.text}"/> <br></br></td>
            </tr>
        </c:forEach>
    </table>
