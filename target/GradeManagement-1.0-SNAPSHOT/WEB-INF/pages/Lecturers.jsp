<%-- 
    Document   : index
    Created on : Jul 7, 2023, 1:08:19 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/admin/lecturers/" var="lecAction" />
<c:url value="/admin/lecturers/new" var="lecnewAction" />
<section class="container">
    <h1 class="text-center text-info mt-1">DANH SÁCH GIẢNG VIÊN</h1>
    <a href="${lecnewAction}" class="btn btn-info">Thêm giảng viên</a>

    <%--<c:if test="${counter > 1}">--%>
    <!--<ul class="pagination mt-1">-->
        <!--<li class="page-item"><a class="page-link" href="<c:url value="/" />">Tất cả</a></li>-->
    <%--<c:forEach begin="1" end="${counter}" var="i">--%>
    <%--<c:url value="/" var="pageUrl">--%>
    <%--<c:param name="page" value="${i}"></c:param>--%>
    <%--</c:url>--%>
<!--<li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>-->
    <%--</c:forEach>--%>
    <!--</ul>-->
    <%--</c:if>--%>

    <table class="table table-hover">
        <thead>
            <tr>
                <th>Id</th>
                <th>Họ</th>
                <th>Tên</th>
                <th>Username</th>
                <th>Email</th>
                <th>Avatar</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${lecturers}" var="l">
                <tr>
                    <td>${l.id}</td>
                    <td>${l.lastName}</td>
                    <td>${l.firstName}</td>
                    <td>${l.username}</td>
                    <td>${l.email}</td>
                    <td>
                        <img src="${l.avatar}" alt="${l.avatar}" width="120" />
                    </td>
                    <!--<td>-->
                    <%--<c:url value="/api/lecturers/${l.id}" var="apiDel" />--%>
                    <!--<a href="<c:url value="/all-lecturers/${l.id}" />" class="btn btn-success">Cập nhật</a>-->
                    <!--<button class="btn btn-danger" onclick="delPro('${apiDel}', ${l.id})">Xóa</button>-->
                    <!--</td>-->
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>

<script src="<c:url value="/js/main.js" />"></script>