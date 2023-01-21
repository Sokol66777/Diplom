<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <h2>Update page</h2>
</head>
<body>
    <p><c:out value="${errorMassage}"/></p>
    <p><c:out value="${message}"/></p>

    <form action="${pageContext.request.contextPath}/post/update" method="post">
    	<table>
    	    <tr>
                <td><c:if test="${not empty updatePostForm.image }">
                    <p><img src="${pageContext.request.contextPath}/post/viewPostImage?idPost=${updatePostForm.id}" width="100"/></p>
                </c:if></td>
            </tr>
			<tr>
    			<td>Post name</td>
        		<td><input type = "text" name="name"  required="required"  value="<c:out value='${updatePostForm.name}'/>"/></td>
        		<input type = "hidden" name = "id" value = "<c:out value = '${updatePostForm.id}'/>"/>
        		<input type = "hidden" name = "idUser" value = "<c:out value = '${updatePostForm.idUser}'/>"/>
			</tr>
            <tr>
                <td>Text</td>
                <td><textarea name = "text" rows = "7" cols ="100" required="required"><c:out value='${updatePostForm.text}'/></textarea></td>
            </tr>
        	<tr>
        		<td><input type = "submit" value="Update"/></td>
			</tr>
		</table>
	</form>

	<form action="${pageContext.request.contextPath}/post/uploadPhoto?idPost=${updatePostForm.id}" enctype="multipart/form-data" method="post">
        <p>Выберите ваше фото для поста</p>
        <p><input type="file" name="fileData" >
        <input type="submit" value="Загрузить"></p>
    </form>

    <button onclick = "location.href='${pageContext.request.contextPath}/user/welcome'">welcome</button>
    <button onclick = "location.href='${pageContext.request.contextPath}/user/logout'"> logout </button>
</body>
</html>