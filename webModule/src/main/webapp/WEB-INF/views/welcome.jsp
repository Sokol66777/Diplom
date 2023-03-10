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
       <c:if test="${not empty user.image }">
            <p><img src="${pageContext.request.contextPath}/user/imageOnWelcomePage?idUser=${user.id}" width="100"/></p>
       </c:if>

    <p></p>
    <button onclick = "location.href='${pageContext.request.contextPath}/user/logout'"> logout </button>
    <button onclick = "location.href='${pageContext.request.contextPath}/user/update?updateUsersID=${user.id}'">Update</button>
    <button onclick = "location.href='${pageContext.request.contextPath}/post/myPosts?idUserPost=${user.id}'"> My posts </button>
    <button onclick = "location.href='${pageContext.request.contextPath}/user/preSearchUser'">Search User</button>
    <p></p>
    <button onclick = "location.href='${pageContext.request.contextPath}/subscribeRequest/currentUserNotifications?idUser=${user.id}'">My notifications</button>


    <security:authorize access = "hasRole('ROLE_admin')">
       <button onclick = "location.href='${pageContext.request.contextPath}/user/allUsers?adminName=${user.username}'">All users</button>
    </security:authorize>

    <br></br><h2><p>New posts</p></h2>

<c:forEach var="post" items = "${allPosts}">
    <c:if test="${post.hide=='false'}">
        <table>

                <tr>
                    <td><a href="${pageContext.request.contextPath}/user/friendUser?idFriendUser=${post.user.ID}"><c:out value="${post.user.username}"/></a></td>
                    <td><c:out value = "${post.name}"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><c:if test="${not empty post.image }">
                        <p><img src="${pageContext.request.contextPath}/post/viewPostImage?idPost=${post.ID}" width="100"/></p>
                    </c:if></td>
                </tr>
                <tr>
                    <td></td>
                    <td><c:out value = "${post.text}"/> </td>
                </tr>
                <tr>
                    <td><a href="/comment/commentsOfPost?idPost=${post.ID}">comments</a> <br><br></td>
                </tr>
        </table>
    </c:if>
</c:forEach>

<c:if test="${totalPages>0}">
    <ul>
        <c:forEach begin="0" end ="${totalPages-1}" var="page">
                <a href="${pageContext.request.contextPath}/user/welcome?page=${page}"><c:out value="${page+1}" /></a>
      </c:forEach>
    </ul>
</c:if>

</body>
</html>