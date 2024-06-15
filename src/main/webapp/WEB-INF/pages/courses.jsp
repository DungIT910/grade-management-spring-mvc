<%-- 
    Document   : index
    Created on : Jul 7, 2023, 1:08:19 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/admin/courses" var="lecnewAction" />
<section>
    <h1 class="text-center text-info mt-1">DANH SÁCH KHÓA HỌC</h1>
    <a href="${lecnewAction}" class="btn btn-info" style="">Thêm khóa học</a>

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
                <th>Tên khóa học</th>
                <th>Tên môn học</th>
            </tr>
        </thead>
        <tbody class="">
            <c:forEach items="${courses}" var="c">
            <div class="">
                <tr>
                    <td class="">${c.name}</td>
                    <td>${c.lastName}</td>
                    <td>
                        <img src="${c.avatar}" alt="${c.avatar}" height="120" width="120" />
                    </td>
                    <td class="fw-bold fs-5">
                        <c:if test="${c.active}">
                            <span class="text-success">&#10003;</span>
                        </c:if>
                        <c:if test="${!c.active}">
                            <span class="text-danger">&#10007;</span>
                        </c:if>
                    </td>
                    <td>
                        <c:url value="/api/courses/${c.id}" var="apiDel" />
                        <a href="<c:url value="/admin/courses/${c.id}" />" class="btn btn-success">Cập nhật</a>
                        <button class="btn btn-danger" onclick="delLec('${apiDel}', ${p.id})">Xóa</button>
                    </td>
                </tr>
            </div>
        </c:forEach>
        </tbody>
    </table>
</section>

<script src="<c:url value="/js/main.js" />"></script>