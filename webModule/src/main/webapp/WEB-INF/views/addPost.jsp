<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <p2>Add post page</p2>
    </head>
    <body>
        <p><c:out value="${errorMassage}"/></p>
        <p><c:out value="${message}"/></p>
        <form action="${pageContext.request.contextPath}/post/add" method="post">
        	<table>
    			<tr>
        			<td>Name Post</td>
            		<td><input type = "text" name="name"  required="required"/></td>
    			</tr>
                <tr>
                    <td>Text</td>
                    <td><textarea name = "text" rows = "7" cols ="100" required="required"></textarea></td>
                </tr>
                <input type = "hidden" name = "idUser" value = "<c:out value = '${user.id}'/>"/>
            	<tr>
            		<td><input type = "submit" value="Add"/></td>
    			</tr>
    		</table>
    	</form>

    	<form action="${pageContext.request.contextPath}/post/uploadPhoto" enctype="multipart/form-data" method="post">
            <p>Выберите ваше фото для поста</p>
            <p><input type="file" name="fileData" >
            <input type="submit" value="Загрузить"></p>
        </form>
    <button onclick = "location.href='${pageContext.request.contextPath}/user/welcome'">welcome</button>
    </body>
</html>