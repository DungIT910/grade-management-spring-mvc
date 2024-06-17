
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/admin/courses" var="coursenewAction" />
<section>
    <h1 class="text-center text-info mt-1">DANH SÁCH MÔN HỌC</h1>
    <a href="${coursenewAction}" class="btn btn-info" style="">Thêm khóa học</a>

    <c:if test="${counter > 1}">
        <ul class="pagination mt-1">
            <c:forEach begin="1" end="${counter}" var="i">
                <c:url value="/admin/courses/" var="pageUrl">
                    <c:param name="page" value="${i}"></c:param>
                </c:url>
                <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>

    <table class="table table-hover" style="text-align: center; vertical-align: middle; margin-top: 2rem;">
        <thead>
            <tr>
                <th>Id khóa học</th>
                <th>Tên khóa học</th>
                <th>Tên Môn học</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody class="">
            <c:forEach items="${courses}" var="c">
            <div class="">
                <tr>
                    <td class="">${c.id}</td>
                    <td class="">${c.name}</td>
                    <td class="">${c.subjectId.subjectname}</td>
                    <td>
                        <c:url value="/api/courses/${c.id}" var="apiDel" />
                        <a href="<c:url value="/admin/courses/${c.id}" />" class="btn btn-success">Cập nhật</a>
                        <button class="btn btn-danger" onclick="delPro('${apiDel}', ${c.id})">Xóa</button>
                    </td>
                </tr>
            </div>
        </c:forEach>
        </tbody>
    </table>
</section>

<script src="<c:url value="/js/main.js" />"></script>