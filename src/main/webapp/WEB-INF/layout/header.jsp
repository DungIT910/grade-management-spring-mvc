<%-- 
    Document   : header
    Created on : Jul 21, 2023, 1:12:19 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="action" />
<c:url value="/admin/lecturers/" var="lecaction" />
<c:url value="/admin/subjects/" var="subjectaction" />
<c:url value="/admin/courses/" var="courseaction" />
<nav class="navbar navbar-expand-sm bg-dark navbar-dark p-2">
    <div class="container-fluid">
        <a class="navbar-brand" href="${action}">Trang giáo vụ</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav me-auto">
                <%--<c:url value="/" var="searchUrl">--%>
                <%--<c:param name="cateId" value="${c.id}"></c:param>--%>
                <%--</c:url>--%>
                <li class="nav-item">
                    <a class="nav-link" href="${lecaction}">Giảng viên</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${subjectaction}">Môn học</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${courseaction}">Khóa học</a>
                </li>
                <li class="nav-item d-flex justify-content-end">

                </li>
            </ul>

            <form class="d-flex" action="${action}">
                <input class="form-control me-2" type="text" name="kw" placeholder="Nhập từ khóa...">
                <button class="btn btn-primary" type="submit">Tìm</button>
            </form>
        </div>
    </div>
    <div class="mx-2">
        <c:choose>
            <c:when test="${pageContext.request.userPrincipal.name != null}">
                <div class="dropdown fw-bold ">
                    <button type="button" class="btn btn-success dropdown-toggle" data-bs-toggle="dropdown">
                        ${pageContext.request.userPrincipal.name}
                    </button>
                    <ul class="dropdown-menu bg-dark">
                        <li class="nav-item text-center">
                            <a class="nav-link text-danger" href="<c:url value="/logout" />">Đăng xuất</a>
                        </li>
                    </ul>
                </div>
            </c:when>
            <c:otherwise>
                <a class="nav-link bg-success p-2 text-white text-nowrap rounded-2" href="<c:url value="/login" />">Đăng nhập</a>
            </c:otherwise>
        </c:choose>
    </div>
</nav>