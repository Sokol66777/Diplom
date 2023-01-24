<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>

</head>
<body>
    <p><c:out value="${errorMassage}"/></p>
    <p><c:out value="${friendUser.username}"/></p>
    <p><img src="${pageContext.request.contextPath}/user/imageOnWelcomePage?idUser=${friendUser.id}" width="100"/></p>

    <p></p>
    <c:if test = "${isSubscribe=='true'}">
        <button onclick = "location.href='${pageContext.request.contextPath}/user/unsubscribe?idChanel=${friendUser.id}&idUser=${user.id}'"> unsubscribe </button>
    </c:if>
    <c:if test = "${isSubscribe=='false'}">
        <button onclick = "location.href='${pageContext.request.contextPath}/user/subscribe?idChanel=${friendUser.id}&idUser=${user.id}'"> subscribe </button>
    </c:if>

    <button onclick = "location.href='${pageContext.request.contextPath}/user/welcome'"> welcome page </button>
    <button onclick = "location.href='${pageContext.request.contextPath}/post/postsOfFriend?idFriendUser=${friendUser.id}'"> Posts </button>