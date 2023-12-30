<%-- 
    Document   : consul
    Created on : Dec 21, 2023, 7:44:01 AM
    Author     : vothimaihoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consul</title>
        <link rel="stylesheet" href="./css/stylelisttab.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="content">
            <h1>List of consuls</h1>
            <c:set var="page" value="${requestScope.page}" />
            <div class="pagination">
                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                    <a class="page-link ${i == page ? 'active' : ''}" href="consul?page=${i}">${i}</a>
                </c:forEach>
            </div>
            <table class="table table-bordered table-striped"> 
                <thead class="thead-dark"> 
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Address</th>
                        <th>Term Count</th>
                        <th>Salary</th>
                        <th>Noble Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.data}" var="c">
                        <tr>
                            <td>${c.id}</td>
                            <td>${c.name}</td>
                            <td>${c.age}</td>
                            <td>${c.address}</td>
                            <td>${c.termCount}</td>
                             <fmt:formatNumber type="currency" value="${c.salary}" currencySymbol="$" var="currencyValue"/>
                            <td>${currencyValue}</td>
                            <td>${c.nobleStatus ? 'Yes' : 'No'}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
