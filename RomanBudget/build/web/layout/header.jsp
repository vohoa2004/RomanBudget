<%-- 
    Document   : header
    Created on : Dec 21, 2023, 8:55:43 PM
    Author     : vothimaihoa
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="./css/style.css"/>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-success ">
            <a class="navbar-brand" href="index.html">Roman Budget</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav mr-auto menu ">
                    <li class="nav-item ${currentPage == 'region' ? 'active' : ''}">
                        <a class="nav-link text-light" href="region">Region</a>
                    </li>
                    <li class="nav-item ${currentPage == 'versaille' ? 'active' : ''}">
                        <a class="nav-link text-light" href="versaille">Versailles</a>
                    </li>
                    
                    <c:if test="${sessionScope.account.usertype eq 'emperor'}">
                        <li class="nav-item ${currentPage == 'consul' ? 'active' : ''}">
                        <a class="nav-link text-light" href="consul?action=list">Consul</a>
                    </c:if>
                    
                    </li>
                    <li class="nav-item ${currentPage == 'financial-transaction' ? 'active' : ''}">
                        <a class="nav-link text-light" href="financial-transaction">Financial Transaction</a>
                    </li>
                    <li class="nav-item ${currentPage == 'tax' ? 'active' : ''}">
                        <a class="nav-link text-light" href="tax">Taxes</a>
                    </li>
                    <li class="nav-item ${currentPage == 'king-request' ? 'active' : ''}">
                        <a class="nav-link text-light" href="king-request">King Request</a>
                    </li>
                    <li class="nav-item ${currentPage == 'report' ? 'active' : ''}">
                        <a class="nav-link text-light" href="report">Report</a>
                    </li>
                </ul>
            </div>
                    <c:if test="${sessionScope.account.usertype eq 'consul'}"><a href="consul?action=info"><p class="text-success badge bg-warning">${sessionScope.consul.name}</p></a> </c:if>
            <c:if test="${sessionScope.account.usertype eq 'emperor'}"><a href="consul?action=info&id=9"><p class="text-success badge bg-warning">Emperor Augustus</p></a>   </c:if>
            &nbsp;&nbsp;&nbsp;
            <a href="logout"><button class="btn btn-danger">Logout</button> </a>   

        </nav>
    </body>
</html>