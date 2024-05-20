<%-- 
    Document   : index
    Created on : Jul 7, 2023, 1:08:19 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section class="container">
    <h1 class="text-center text-info mt-1">DANH SÁCH GIẢNG VIÊN</h1>
    <a href="<c:url value="/lecturers" />" class="btn btn-info">Thêm giảng viên</a>

    <table class="table table-hover">
        <thead>
            <tr>
                <th></th>
                <th>Id</th>
                <th>Tên giảng viên</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${lecturers}" var="l">
                <tr>
                    <td>
                        <img src="${l.image}" alt="${l.name}" width="120" />
                    </td>
                    <td>${l.id}</td>
                    <td>${l.name}</td>
                    <td>${l.price}</td>
                    <td>
                        <c:url value="/api/lecturers/${l.id}" var="apiDel" />
                        <a href="<c:url value="/lecturers/${l.id}" />" class="btn btn-success">Cập nhật</a>
                        <button class="btn btn-danger" onclick="delPro('${apiDel}', ${p.id})">Xóa</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>
    
    <script src="<c:url value="/js/main.js" />"></script>