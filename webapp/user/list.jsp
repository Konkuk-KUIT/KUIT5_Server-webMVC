<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--jstl--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<head>
    <%@ include file="../include/head.jspf" %>
</head>
<body>
<%@ include file="../include/navigation.jspf" %>
<%@ include file="../include/header.jspf" %>

    <div class="container" id="main">
        <table class="table table-striped">
            <thead class="col-md-12">
            <tr>
                <th class="col-md-3">아이디</th>
                <th class="col-md-3">이름</th>
                <th class="col-md-3">이메일</th>
                <th class="col-md-3">수정</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
            <tr>
                <th class="col-md-3">${user.userId}</th>
                <th class="col-md-3">${user.name}</th>
                <th class="col-md-3">${user.email}</th>
                <th class="col-md-3">
                    <%--로그인한 사용자의 정보만 수정가능하도록 분기 처리--%>
                    <%--세션에 저장되어있는 유저 정보와 비교--%>
                    <c:if test="${sessionScope.user.userId == user.userId}">
                        <a href="/user/updateForm?userId=${user.userId}" class="btn btn-success" role="button">수정</a>
                    </c:if>
                </th>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="../js/scripts.js"></script>
</body>
</html>