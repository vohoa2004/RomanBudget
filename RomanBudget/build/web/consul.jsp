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
        <link rel="stylesheet" href="./css/style.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="layout/header.jsp" />
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
                        <th>Username</th>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Address</th>
                        <th>Term Count</th>
                        <th>Salary</th>
                        <th>Noble Status</th>
                        <th>Assign New Region</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${requestScope.data}" var="c">
                        <tr>
                            <td>${c.id}</td>
                            <th>${c.username}</th>
                            <td>${c.name}</td>
                            <td>${c.age}</td>
                            <td>${c.address}</td>
                            <td>${c.termCount}</td>
                            <fmt:formatNumber type="currency" value="${c.salary}" currencySymbol="$" var="currencyValue"/>
                            <td>${currencyValue}</td>
                            <td style="<c:if test="${c.nobleStatus == true}">color: green; font-weight: bold;</c:if>
                                <c:if test="${c.nobleStatus == false}">color: red; font-weight: bold;</c:if>">
                                ${c.nobleStatus ? 'Yes' : 'No'}</td>
                            <td><a href="region?action=assign&consulId=${c.id}">Assign to region</a></td>
                        </tr>

                    </c:forEach>
                </tbody>
            </table>
            <br/>
            <c:if test="${sessionScope.account.usertype eq 'emperor'}">
            <h3 class="text-primary">Add new consul</h3>
            <form action="consul" method="post">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="birthYear">Birth Year: </label>
                    <input type="number" class="form-control" id="birthYear" name="birthYear" required>
                </div>
                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" class="form-control" id="address" name="address" required>
                </div>
                <div class="form-group">
                    <label for="termCount">Term Count:</label>
                    <input type="number" class="form-control" id="termCount" name="termCount" required>
                </div>
                <div class="form-group">
                    <label for="salary">Salary:</label>
                    <input type="number" class="form-control" id="salary" name="salary" step="0.01" required>
                </div>
                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="nobleStatus" name="nobleStatus">
                    <label class="form-check-label" for="nobleStatus">Noble Status</label>
                </div>
                <div class="form-group">
                    <label for="image">Image URL:</label>
                    <input type="text" class="form-control" id="image" name="image">
                </div>
                <br/>
                <h6 class="text-warning">Warning: Default password for new user is: 000</h6>               
                <button type="submit" class="btn btn-primary">Create Consul</button>

            </form>
            </c:if>
        </div>


        <jsp:include page="layout/footer.jsp" />
    </body>
</html>
