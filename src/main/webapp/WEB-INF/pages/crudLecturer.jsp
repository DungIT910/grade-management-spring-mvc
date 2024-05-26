<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="buttonText" value="${lecturer.id == null ? 'Thêm giảng viên' : 'Cập nhật giảng viên'}" />
<h1 class="text-center text-info mt-1">${buttonText}</h1>

<c:url value="/admin/lecturers" var="action" />
<form:form method="post" action="${action}" modelAttribute="lecturer" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="userRole" />
    <form:hidden path="avatar" />
    <form:hidden path="active" />
    <form:hidden path="deletedId"/>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" 
                    path="id" id="id" placeholder="Mã giảng viên..." />
        <label for="id">Mã giảng viên</label>
        <form:errors path="id" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" 
                    path="firstName" id="firstName" placeholder="Họ giảng viên..." />
        <label for="name">Họ giảng viên</label>
        <form:errors path="firstName" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" 
                    path="lastName" id="lastName" placeholder="Tên giảng viên..." />
        <label for="name">Tên giảng viên</label>
        <form:errors path="lastName" element="div" cssClass="text-danger" />
    </div> 
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" 
                    path="email" id="email" placeholder="Email..." />
        <label for="name">Email giảng viên</label>
        <form:errors path="email" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" 
                    path="username" id="username" placeholder="Username..." />
        <label for="name">Username</label>
        <form:errors path="username" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" 
                    path="password" id="password" placeholder="Password..." />
        <label for="name">Password</label>
        <form:errors path="password" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" 
                    path="file" id="file"  />
        <label for="file">Ảnh giảng viên</label>
        <c:if test="${lecturer.avatar != null}">
            <img src="${lecturer.avatar}" width="200rem"/>
        </c:if>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-info" type="submit">${buttonText}</button>
    </div>
</form:form>
