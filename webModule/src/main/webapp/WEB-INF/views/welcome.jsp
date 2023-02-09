<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
    <link href="/css/login.css" rel="stylesheet">
    <link href="/css/navBar.css" rel="stylesheet">
    <title>Welcome</title>
</head>
<body>

    <ul class="navigation">
        <c:if test="${not empty user.image }">
            <li><img src="${pageContext.request.contextPath}/user/imageOnWelcomePage?idUser=${user.id}" class="avatar"/></li>
        </c:if>
        <li><a href="${pageContext.request.contextPath}/user/welcome" ><c:out value="${user.username}"/></a>
            <ul>
                <li><a href="${pageContext.request.contextPath}/post/myPosts?idUserPost=${user.id}" title="My posts">My posts</a></li>
                <li><a href="${pageContext.request.contextPath}/user/update?updateUsersID=${user.id}" title="Update">Update</a></li>
                <li><a href="${pageContext.request.contextPath}/subscribeRequest/currentUserNotifications?idUser=${user.id}" title="My notifications">My notifications</a></li>

            </ul>
        </li>
        <li><a href="${pageContext.request.contextPath}/user/preSearchUser" title="Search User">Search user</a></li>
        <li><a href="${pageContext.request.contextPath}/user/logout" title="Logout">Logout</a></li>
        <security:authorize access = "hasRole('ROLE_admin')">
            <li><a href="${pageContext.request.contextPath}/user/allUsers?adminName=${user.username}" title="All users">All users</a></li>
        </security:authorize>
        <div class="clear"></div>
    </ul>

<c:forEach var="post" items = "${allPosts}">
    <c:if test="${post.hide=='false'}">
        <div class="login">
            <c:if test="${not empty post.user.image }">
                <img src="${pageContext.request.contextPath}/user/imageOnWelcomePage?idUser=${post.user.ID}" class="avatar"/>
            </c:if>

            <a href="${pageContext.request.contextPath}/user/friendUser?idFriendUser=${post.user.ID}"><c:out value="${post.user.username}"/></a>

            <p><c:out value = "${post.name}"/></p>

            <c:if test="${not empty post.image }">
                <p><img src="${pageContext.request.contextPath}/post/viewPostImage?idPost=${post.ID}" width="300" height="400"/></p>
            </c:if>

            <c:out value = "${post.text}"/>

            <p><a href="/comment/commentsOfPost?idPost=${post.ID}">comments</a></p>

        </div>
    </c:if>
</c:forEach>

    <c:if test="${totalPages>0}">

        <div class="pagination">
            <c:choose>

                <c:when test="${totalPages<6}">
                    <ul>
                        <c:if test="${currentPage>0 }">
                            <li><a href="${pageContext.request.contextPath}/user/welcome?page=${currentPage-1}">&NestedLessLess;</a></li>
                       </c:if>
                       <c:forEach begin="0" end ="${totalPages-1}" var="page">
                            <c:if test="${currentPage==page }">
                                <li class="active"><a href="${pageContext.request.contextPath}/user/welcome?page=${page}"><c:out value="${page+1}" /></a></li>
                            </c:if>
                            <c:if test="${currentPage!=page }">
                                <li><a href="${pageContext.request.contextPath}/user/welcome?page=${page}"><c:out value="${page+1}" /></a></li>
                            </c:if>
                       </c:forEach>
                       <c:if test="${currentPage<totalPages-1 }">
                            <li><a href="${pageContext.request.contextPath}/user/welcome?page=${currentPage+1}">&NestedGreaterGreater;</a></li>
                       </c:if>
                    </ul>
                </c:when>

                <c:otherwise>
                    <ul>
                        <c:if test="${currentPage>0 }">
                            <li><a href="${pageContext.request.contextPath}/user/welcome?page=${currentPage-1}">&NestedLessLess;</a></li>
                        </c:if>
                        <c:choose>
                            <c:when test="${currentPage>3}">
                                    <li><a href="${pageContext.request.contextPath}/user/welcome?page=0">1</a></li>
                                    <li class="disable"><a href="#">..</a></li>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${currentPage==0 }">
                                    <li class="active"><a href="${pageContext.request.contextPath}/user/welcome?page=0">1</a></li>
                                </c:if>
                                <c:if test="${currentPage!=0 }">
                                    <li><a href="${pageContext.request.contextPath}/user/welcome?page=0">1</a></li>
                                </c:if>
                                <c:if test="${currentPage==1 }">
                                    <li class="active"><a href="${pageContext.request.contextPath}/user/welcome?page=1">2</a></li>
                                </c:if>
                                <c:if test="${currentPage!=1 }">
                                    <li><a href="${pageContext.request.contextPath}/user/welcome?page=1">2</a></li>
                                </c:if>
                                <c:if test="${currentPage==2 }">
                                    <li class="active"><a href="${pageContext.request.contextPath}/user/welcome?page=2">3</a></li>
                                </c:if>
                                <c:if test="${currentPage!=2 }">
                                    <li><a href="${pageContext.request.contextPath}/user/welcome?page=2">3</a></li>
                                </c:if>
                            </c:otherwise>
                        </c:choose>

                        <c:if test="${currentPage>3 &&  currentPage<totalPages-2}">
                            <li><a href="${pageContext.request.contextPath}/user/welcome?page=${currentPage-2}"><c:out value="${currentPage+1-2}"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/user/welcome?page=${currentPage-1}"><c:out value="${currentPage+1-1}"/></a></li>
                        </c:if>

                        <c:if test="${currentPage>2 && currentPage<totalPages-3}">
                            <li class="active"><a href="${pageContext.request.contextPath}/user/welcome?page=${currentPage}"><c:out value="${currentPage+1}"/></a></li>
                        </c:if>

                        <c:if test="${currentPage>1 && currentPage<totalPages-4}">
                            <li><a href="${pageContext.request.contextPath}/user/welcome?page=${currentPage+1}"><c:out value="${currentPage+1+1}"/></a></li>
                            <li><a href="${pageContext.request.contextPath}/user/welcome?page=${currentPage+2}"><c:out value="${currentPage+1+2}"/></a></li>
                        </c:if>

                        <c:choose>

                            <c:when test="${currentPage<totalPages-4}">
                                    <li class="disable"><a href="${pageContext.request.contextPath}/user/welcome?page=0">..</a></li>
                                    <li><a href="${pageContext.request.contextPath}/user/welcome?page=${totalPages-1}"><c:out value="${totalPages}"/></a></li>
                            </c:when>

                            <c:otherwise>
                                <c:if test="${currentPage==totalPages-3 }">
                                    <li class="active"><a href="${pageContext.request.contextPath}/user/welcome?page=${totalPages-3}"><c:out value="${totalPages+1-3}"/></a></li>
                                </c:if>
                                <c:if test="${currentPage!=totalPages-3 }">
                                    <li><a href="${pageContext.request.contextPath}/user/welcome?page=${totalPages-3}"><c:out value="${totalPages+1-3}"/></a></li>
                                </c:if>
                                <c:if test="${currentPage==totalPages-2 }">
                                    <li class="active"><a href="${pageContext.request.contextPath}/user/welcome?page=${totalPages-2}"><c:out value="${totalPages+1-2}"/></a></li>
                                </c:if>
                                <c:if test="${currentPage!=totalPages-2 }">
                                    <li><a href="${pageContext.request.contextPath}/user/welcome?page=${totalPages-2}"><c:out value="${totalPages+1-2}"/></a></li>
                                </c:if>
                                <c:if test="${currentPage==totalPages-1 }">
                                    <li class="active"><a href="${pageContext.request.contextPath}/user/welcome?page=${totalPages-1}"><c:out value="${totalPages+1-1}"/></a></li>
                                </c:if>
                                <c:if test="${currentPage!=totalPages-1 }">
                                    <li><a href="${pageContext.request.contextPath}/user/welcome?page=${totalPages-1}"><c:out value="${totalPages+1-1}"/></a></li>
                                </c:if>
                            </c:otherwise>

                        </c:choose>
                        <c:if test="${currentPage<totalPages-1 }">
                            <li><a href="${pageContext.request.contextPath}/user/welcome?page=${currentPage+1}">&NestedGreaterGreater;</a></li>
                        </c:if>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>

    </c:if>



</body>
</html>