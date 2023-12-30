<%-- 
    Document   : update-pass
    Created on : Dec 30, 2023, 5:32:09 PM
    Author     : vothimaihoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/style.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.min.js"></script>
        <title>Update password</title>
    </head>
    <body>
        <jsp:include page="layout/header.jsp" />
        <br/>
        <div class="container">
            <h1>Update password</h1>
            <form action="logout" method="post">
                <c:if test="${requestScope.message!=null}">
                    <div class="alert alert-danger">
                        <p>${requestScope.message}</p>
                    </div>
                </c:if>
                <div class="form-group">
                    <label for="currentPassword">Current Password:</label>
                    <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                </div>

                <div class="form-group">
                    <label for="newpass">New Password:</label>
                    <input type="password" class="form-control" id="newpass" name="newpass" required>
                </div>
                <div class="form-group">
                    <label for="confirm">Confirm New Password:</label>
                    <input type="password" class="form-control" id="confirm" name="confirm" required>
                </div>
                <button type="submit" class="btn btn-primary">Change Password</button>
            </form>
        </div>
        <br/><br/><br/><br/><br/><br/><br/>
        <jsp:include page="layout/footer.jsp" />

    </body>
</html>
