
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/admin/subjects" var="subjectnewAction" />
<section>
    <h1 class="text-center text-info mt-1">DANH SÁCH MÔN HỌC</h1>
    <a href="${subjectnewAction}" class="btn btn-info" style="">Thêm môn học</a>

    <c:if test="${counter > 1}">
        <ul class="pagination mt-1">
            <li class="page-item"><a class="page-link" href="<c:url value="/admin/subjects/" />">Tất cả</a></li>
                <c:forEach begin="1" end="${counter}" var="i">
                    <c:url value="/admin/subjects/" var="pageUrl">
                        <c:param name="page" value="${i}"></c:param>
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>

    <table class="table table-hover" style="text-align: center; vertical-align: middle; margin-top: 2rem;">
        <thead>
            <tr>
                <th>Id môn học</th>
                <th>Tên môn học</th>
            </tr>
        </thead>
        <tbody class="">
            <c:forEach items="${subjects}" var="s">
            <div class="">
                <tr>
                    <td class="">${s.id}</td>
                    <td class="">${s.subjectname}</td>
                    <td>
                        <c:url value="/api/subjects/${s.id}" var="apiDel" />
                        <a href="<c:url value="/products/${s.id}" />" class="btn btn-success">Cập nhật</a>
                        <button class="btn btn-danger" onclick="delPro('${apiDel}', ${s.id})">Xóa</button>
                    </td>
                </tr>
            </div>
        </c:forEach>
        </tbody>
    </table>
</section>

<script src="<c:url value="/js/main.js" />"></script>