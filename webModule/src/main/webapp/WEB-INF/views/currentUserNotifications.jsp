<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
    <link href="/css/login.css" rel="stylesheet">
    <link href="/css/navBar.css" rel="stylesheet">
    <title>Notifications</title>
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

    <c:forEach var="subRequest" items = "${subRequests}">

        <form class="login" action="${pageContext.request.contextPath}/user/subscribe" method="post">
            	<table>
        			<tr>
        			    <td>
        			        <a href="${pageContext.request.contextPath}/user/friendUser?idFriendUser=${subRequest.idSubscriber}"><c:out value="${subRequest.usernameSubscriber}"/></a></td>
                		    <input type = "hidden" name = "id" value = "<c:out value = '${subRequest.id}'/>"/>
                		    <input type = "hidden" name = "idSubscriber" value = "<c:out value = '${subRequest.idSubscriber}'/>"/>
                		    <input type = "hidden" name = "idChanel" value = "<c:out value = '${subRequest.idChanel}'/>"/>
        			    </td>
        			    <td>
        			        <p><input name="status" type="radio" value="true"> accept</p>
                            <p><input name="status" type="radio" value="false" checked> denied</p></textarea>
        			    </td>
        			</tr>
                	<tr>
                		<td><input type = "submit" value="confirm"/></td>
        			</tr>
        		</table>
        	</form>
    </c:forEach>

    <c:if test="${empty pages}">

        <div class="pagination">

            <ul>
                <c:if test="${currentPage>0 }">
                    <li><a href="${pageContext.request.contextPath}/subscribeRequest/currentUserNotifications?page=${currentPage-1}&idUser=${user.id}">&NestedLessLess;</a></li>
                </c:if>
                <c:forEach begin="0" end ="${totalPages-1}" var="page">
                    <c:if test="${currentPage==page }">
                        <li class="active"><a href="${pageContext.request.contextPath}/subscribeRequest/currentUserNotifications?page=${page}&idUser=${user.id}"><c:out value="${page+1}" /></a></li>
                    </c:if>
                    <c:if test="${currentPage!=page }">
                        <li><a href="${pageContext.request.contextPath}/subscribeRequest/currentUserNotifications?page=${page}&idUser=${user.id}"><c:out value="${page+1}" /></a></li>
                    </c:if>
                </c:forEach>
                    <c:if test="${currentPage<totalPages-1 }">
                        <li><a href="${pageContext.request.contextPath}/subscribeRequest/currentUserNotifications?page=${currentPage+1}&idUser=${user.id}">&NestedGreaterGreater;</a></li>
                    </c:if>
            </ul>
        </div>
    </c:if>

</body>
</html>