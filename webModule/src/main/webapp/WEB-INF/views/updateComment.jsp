<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <h2>Update comment</h2>
</head>
<body>
    <p><c:out value="${errorMassage}"/></p>
    <p><c:out value="${message}"/></p>

    <form action="${pageContext.request.contextPath}/comment/update" method="post">
    	<table>
			<tr>
        		<input type = "hidden" name = "id" value = "<c:out value = '${updateComment.id}'/>"/>
			</tr>
            <tr>
                <td>Text</td>
                <td><textarea name = "text" rows = "7" cols ="100" required="required"><c:out value='${updateComment.text}'/></textarea></td>
            </tr>
        	<tr>
        		<td><input type = "submit" value="Update"/></td>
			</tr>
		</table>
	</form>

    <button onclick = "location.href='${pageContext.request.contextPath}/user/welcome'">welcome</button>
    <button onclick = "location.href='${pageContext.request.contextPath}/user/logout'"> logout </button>
</body>
</html>