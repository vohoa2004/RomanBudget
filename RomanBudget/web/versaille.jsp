<%-- 
    Document   : versaille
    Created on : Dec 21, 2023, 9:55:04 AM
    Author     : vothimaihoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Versailles</title>
        <link rel="stylesheet" href="./css/style.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="content">
            <h1>List of versailles</h1>
            <c:set var="page" value="${requestScope.page}"/>
            <div class="pagination">
                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                    <a class="${i==page?"active":""}" href="versaille?page=${i}">${i}</a>
                </c:forEach>
            </div>
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>Name</th>
                        <th>Market Value</th>
                        <th>Growth Rate</th>
                        <th>Revolt Rate</th>
                        <th>Food Supply</th>
                        <th>Period</th>
                    </tr>
                </thead>   
                <c:forEach items="${requestScope.data2}" var="c">
                    <tr>
                        <td>${c.versaille.name}</td>
                        <td>${c.marketValue}</td>
                        <td>${c.growthRate}</td>
                        <td>${c.revoltRate}</td>
                        <td>${c.foodSupply}</td>
                        <td>Q${c.quarter}/${c.year}</td>
                    </tr>
                </c:forEach>
            </table>

            <div class="row">
                <c:forEach items="${requestScope.data1}" var="i">
                    <div class="col-md-4">
                        <h4>${i.name}</h4>
                        <img src='./images/${i.image}' class="img-fluid"/>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
