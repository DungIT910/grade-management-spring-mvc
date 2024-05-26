<%-- 
    Document   : index
    Created on : Jul 7, 2023, 1:08:19 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/admin/lecturers" var="lecnewAction" />
<section>
    <h1 class="text-center text-info mt-1">DANH SÁCH GIẢNG VIÊN</h1>
    <a href="${lecnewAction}" class="btn btn-info" style="">Thêm giảng viên</a>

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

    <table class="table table-hover" style="text-align: center; vertical-align: middle; margin-top: 2rem;">
        <thead>
            <tr>
                <th>Id</th>
                <th>Họ</th>
                <th>Tên</th>
                <th>Username</th>
                <th>Email</th>
                <th>Avatar</th>
                <th>Active</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody class="">
            <c:forEach items="${lecturers}" var="l">
            <div class="">
                <tr>
                    <td class="">${l.id}</td>
                    <td>${l.lastName}</td>
                    <td>${l.firstName}</td>
                    <td>${l.username}</td>
                    <td>${l.email}</td>
                    <td>
                        <img src="${l.avatar}" alt="${l.avatar}" height="120" width="120" />
                    </td>
                    <td class="fw-bold fs-5">
                        <c:if test="${l.active}">
                            <span class="text-success">&#10003;</span>
                        </c:if>
                        <c:if test="${!l.active}">
                            <span class="text-danger">&#10007;</span>
                        </c:if>
                    </td>
                    <td>
                        <c:url value="/api/lecturers/${l.id}" var="apiDel" />
                        <a href="<c:url value="/admin/lecturers/${l.id}" />" class="btn btn-success">Cập nhật</a>
                        <button class="btn btn-danger" onclick="delLec('${apiDel}', ${p.id})">Xóa</button>
                    </td>
                </tr>
            </div>
        </c:forEach>
        </tbody>
    </table>
</section>

<script src="<c:url value="/js/main.js" />"></script>