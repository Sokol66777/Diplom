<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>

</head>
<body>
    <p><c:out value="${errorMassage}"/></p>

    <form action="${pageContext.request.contextPath}/user/searchUser" method="post">
    	<table>
			<tr>
    			<td>Write username for search</td>
        		<td><input type = "text" name="username" required="required"/></td>
			</tr>
        	<tr>
        		<td><input type = "submit" value="Search"/></td>
			</tr>
		</table>
	</form>
    <button onclick = "location.href='${pageContext.request.contextPath}/user/welcome'">welcome</button>

<c:forEach var="user" items = "${findUsers}">
        <table>
                <tr>
                    <td></td>
                    <td><c:if test="${not empty user.image }">
                        <p><img src="${pageContext.request.contextPath}/user/imageOnWelcomePage?idUser=${user.ID}" width="100"/></p>
                    </c:if></td>

                    <td><a href="${pageContext.request.contextPath}/user/friendUser?idFriendUser=${user.ID}"><c:out value="${user.username}"/></a></td>
                </tr>
        </table>
</c:forEach>

<c:if test="${totalPages>0}">
    <ul>
        <c:forEach begin="0" end ="${totalPages-1}" var="page">
                <a href="${pageContext.request.contextPath}/user/searchUser?page=${page}"><c:out value="${page+1}" /></a>
      </c:forEach>
    </ul>
</c:if>

</body>
</html>