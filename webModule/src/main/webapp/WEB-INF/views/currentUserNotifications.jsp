<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>

</head>
<body>

    <h2><p>My notifications</p></h2>

    <c:forEach var="subRequest" items = "${subRequests}">

        <form action="${pageContext.request.contextPath}/user/subscribe" method="post">
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

<c:if test="${totalPages>0}">
    <ul>
        <c:forEach begin="0" end ="${totalPages-1}" var="page">
                <a href="${pageContext.request.contextPath}/subscribeRequest/currentUserNotifications?page=${page}&idUser=${user.id}"><c:out value="${page+1}" /></a>
      </c:forEach>
    </ul>
</c:if>

<button onclick = "location.href='${pageContext.request.contextPath}/user/welcome'">welcome</button>

</body>
</html>