<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="buttonText" value="${subject.id == null ? 'Thêm môn học' : 'Cập nhật môn học'}" />
<h1 class="text-center text-info mt-1">${buttonText}</h1>

<c:url value="/admin/subjects" var="action" />
<form:form method="post" action="${action}" modelAttribute="subject" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" 
                    path="subjectname" id="subjectname" placeholder="Tên môn học..." />
        <label for="subjectName">Tên môn học</label>
        <form:errors path="subjectname" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-info" type="submit">${buttonText}</button>
    </div>
</form:form>
