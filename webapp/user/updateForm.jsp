<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../WEB-INF/views/include/header.jspf" %>
<%@ include file="../WEB-INF/views/include/navigation.jspf" %>

<div class="container" id="main">
    <main class="form-signin">

        <form name="update-user" method="post" action="/user/update">
            <div class="form-floating">
                <input type="text" class="form-control" id="userId" name="userId"
                       value="${user.userId}" placeholder="Id" readonly>
                <label for="userId">User Id</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="password" name="password"
                       value="${user.password}" placeholder="Password">
                <label for="password">Password</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="name" name="name"
                       value="${user.name}" placeholder="Name">
                <label for="name">Name</label>
            </div>
            <div class="form-floating">
                <input type="email" class="form-control" id="email" name="email"
                       value="${user.email}" placeholder="Email">
                <label for="email">Email</label>
            </div>
            <div style="height:10px;"></div>
            <button class="w-100 btn btn-lg btn-primary" type="submit">Update</button>
        </form>
    </main>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/scripts.js"></script>
</body>
</html>