<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="buttonText" value="${course.id == null ? 'Thêm khóa học' : 'Cập nhật khóa học'}" />
<h1 class="text-center text-info mt-1">${buttonText}</h1>

<c:url value="/admin/courses" var="action" />
<form:form method="post" action="${action}" modelAttribute="course" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" 
                    path="name" id="name" placeholder="Tên khóa học..." />
        <label for="name">Tên khóa học</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="subjectId" name="subjectId" path="subjectId">
            <c:forEach items="${subjects}" var="s">
                <c:choose>
                    <c:when test="${s.id == course.subjectId.id}">
                        <option value="${s.id}" selected>${s.subjectname}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${s.id}">${s.subjectname}</option>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </form:select>
        <label for="subjectId" class="form-label">Tên môn học:</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="lecturerId" name="lecturerId" path="lecturerId">
            <c:forEach items="${lecturers}" var="l">
                <c:choose>
                    <c:when test="${l.id == course.lecturerId.id}">
                        <option value="${l.id}" selected>${l.id} - ${l.firstName} ${l.lastName}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${l.id}">${l.id} - ${l.firstName} ${l.lastName}</option>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </form:select>
        <label for="subjectId" class="form-label">Giảng viên phụ trách</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-info" type="submit">${buttonText}</button>
    </div>

</form:form>
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
        <c:forEach items="${students}" var="stu">
        <div class="">
            <tr>
                <td class="">${stu.id}</td>
                <td>${stu.lastName}</td>
                <td>${stu.firstName}</td>
                <td>${stu.username}</td>
                <td>${stu.email}</td>
                <td>
                    <img src="${stu.avatar}" alt="${stu.avatar}" height="120" width="120" />
                </td>
                <td class="fw-bold fs-5">
                    <c:if test="${stu.active}">
                        <span class="text-success">&#10003;</span>
                    </c:if>
                    <c:if test="${!stu.active}">
                        <span class="text-danger">&#10007;</span>
                    </c:if>
                </td>
                <td>
                    <c:url value="/api/lecturers/${stu.id}" var="apiDel" />
                    <a href="<c:url value="/admin/lecturers/${stu.id}" />" class="btn btn-success">Cập nhật</a>
                </td>
            </tr>
        </div>
    </c:forEach>
</tbody>
</table>   