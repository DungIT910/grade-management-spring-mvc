<%-- 
    Document   : login
    Created on : Aug 4, 2023, 2:17:34 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/login" var="action" />
<c:if test="${param.error != null}">
    <div class="alert alert-danger">
        <spring:message code="user.login.error1" />
    </div>

</c:if>
<c:if test="${param.accessDenied != null}">       
    <div class="alert alert-danger">
        <spring:message code="user.login.error2" />
    </div>

</c:if>
<form method="post" action="${action}">
    <div class="form-floating mb-3 mt-3">
        <input type="text" class="form-control" id="username" placeholder="Nhập username..." name="username">
        <label for="username">Tên đăng nnhập</label>
    </div>

    <div class="form-floating mt-3 mb-3">
        <input type="text" class="form-control" id="pwd" placeholder="Nhập mật khẩu..." name="password">
        <label for="pwd">Mật khẩu</label>
    </div>

    <div class="form-floating mt-3 mb-3">
        <input type="submit" value="Đăng nhập" class="btn btn-danger" />
    </div>
</form>
