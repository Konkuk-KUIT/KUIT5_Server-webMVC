<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<head>
    <%@ include file="../jspfs/head.jspf" %>
</head>
<body>
<%@ include file="../jspfs/navigation.jspf" %>
<%@ include file="../jspfs/header.jspf" %>
<nav class="navbar navbar-expand-md navbar-dark bg-dark" aria-label="Fourth navbar example">
    <div class="container-fluid">
        <a class="navbar-brand" href="/"> KUIT </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample04"
                aria-controls="navbarsExample04" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="container">
            <main class="form-signin">
                <form name="login" method="post" action="/user/userLogin">
                    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

                    <div class="form-floating">
                        <input type="text" class="form-control" id="loginId" name="userId" placeholder="id">
                        <label for="loginId">ID</label>
                    </div>
                    <div class="form-floating">
                        <input type="password" class="form-control" id="loginPassword" name="password"
                               placeholder="Password">
                        <label for="loginPassword">Password</label>
                    </div>

                    <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
                </form>
            </main>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="../js/scripts.js"></script>
</body>
</html>