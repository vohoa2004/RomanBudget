<%-- 
    Document   : login
    Created on : Dec 20, 2023, 11:05:23 PM
    Author     : vothimaihoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container mt-5">

            <div class="row justify-content-center">
                <div class="col-md-6">
                    <h1>Login</h1>
                    <c:if test="${requestScope.error != null}">
                        <div class="alert alert-danger">
                            <p>${requestScope.error}</p>
                        </div>
                    </c:if>

                    <form action="login" method="post" class="border rounded p-4">
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text" id="username" name="username" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" id="password" name="password" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="usertype">User Type:</label>
                            <select id="usertype" name="usertype" class="form-control">
                                <option value="Consul">Consul</option>
                                <option value="Emperor">Emperor</option>
                                <!-- Thêm các user type khác nếu cần -->
                            </select>
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Login" class="btn btn-primary btn-block">
                        </div>
                    </form>
                </div>
            </div>
        </div>



    </body>
</html>
