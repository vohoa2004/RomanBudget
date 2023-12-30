<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consul</title>
        <link rel="stylesheet" href="./css/style.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </head>

    <body>
        <jsp:include page="layout/header.jsp" />
        <div class="container mt-5">
            <div class="card">
                <div class="card-header">
                    <h5>Profile</h5>
                </div>
                <c:set var="consul" value="${requestScope.consul}"/>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-4">
                            <!-- Image -->
                            <img src='./images/${consul.image}' alt="Consul Image" class="img-fluid"/>
                        </div>
                        <div class="col-md-8">
                            <!-- Consul Information -->
                            <ul class="list-group">
                                <c:if test="${consul.id eq 9}">
                                    <li class="list-group-item"><h4 style='color: orange'>Emperor</h4></li>
                                    </c:if>
                                <li class="list-group-item">ID: ${consul.id}</li>
                                <li class="list-group-item">Username: ${consul.username}</li>
                                <li class="list-group-item">Name: ${consul.name}</li>
                                <li class="list-group-item">Age: ${consul.age}</li>
                                <li class="list-group-item">Address: ${consul.address}</li>
                                <li class="list-group-item">Term Count: ${consul.termCount}</li>
                                <li class="list-group-item">Salary: ${consul.salary}</li>
                                <li class="list-group-item">Noble Status: ${consul.nobleStatus}</li>
                            </ul>
                           <a href="update-pass.jsp"> <button class='btn btn-warning' >
                                Change password
                               </button></a>
                        </div>

                    </div>
                </div>
            </div>

        </div>

        <!-- Bootstrap JS and jQuery (optional, if needed) -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <jsp:include page="layout/footer.jsp" />
    </body>

</html>
