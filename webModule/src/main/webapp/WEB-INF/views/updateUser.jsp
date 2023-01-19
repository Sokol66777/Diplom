<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <h2>Update page</h2>
</head>
<body>
    <p><c:out value="${errorMassage}"/></p>
    <form action="update" method="post">
    	<table>
			<tr>
    			<td>User Name</td>
        		<td><input type = "text" name="newUsername"  required="required"  value="<c:out value='${updateUserForm.username}'/>"/></td>
			</tr>
            <tr>
                <td>Email</td>
                <td><input type = "email" name="newEmail"  required="required" value="<c:out value='${updateUserForm.email}'/>"/></td>
                <td><input type = "hidden" name= "id" value="<c:out value='${updateUserForm.id}'/>"/></td>
            </tr>
    		<tr>
    		    <c:if test="${user.username==updateUserForm.username}">
    		        <tr>
    			        <td>new Password (not required)</td>
        		        <td><input type = "password" name="newPassword"  /></td>
        		    </tr>
                    <tr>
                        <td><img src="${pageContext.request.contextPath}/user/viewImage" width="100"</td>
                    </tr>
			    </c:if>
			</tr>
        	<tr>
        		<td><input type = "submit" value="update"/></td>
			</tr>
		</table>
	</form>

    <c:if test="${user.username==updateUserForm.username}">
	    <form action="uploadPhoto" enctype="multipart/form-data" method="post">
              <p>Выберите ваше фото</p>
              <p><input type="file" name="fileData" >
              <input type="submit" value="Загрузить"></p>
        </form>
    </c:if>

    <button onclick = "location.href='${pageContext.request.contextPath}/user/welcome'">welcome</button>
    <button onclick = "location.href= '${pageContext.request.contextPath}/user/logout'"> logout </button>
	 <c:if test="${role=='admin'}">
       <button onclick = "location.href='${pageContext.request.contextPath}/user/allUsers?adminName=${user.username}'">All users</button>
     </c:if>

</body>
</html>