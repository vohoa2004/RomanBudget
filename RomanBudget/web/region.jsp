<%-- 
    Document   : region
    Created on : Dec 21, 2023, 8:16:09 AM
    Author     : vothimaihoa
--%>

<%@page import="model.Region"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Region</title>
        <link rel="stylesheet" href="./css/stylelisttab.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="content">
            <h1>Overview</h1>


            <c:set var="page" value="${requestScope.page}"/>
            <c:set var="currentPage" value = "${requestScope.currentPage}"/>
            <div class="pagination">
                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                    <a class="${i==page?"active":""}" href="region?page=${i}">${i}</a>
                </c:forEach>
            </div>
            <!--filter to cal budget in specific time-->
            <form action="region" method="post">
                <div class="form-row">
                    <div class="col-md-3 mb-3">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">From</span>
                            </div>
                            <input type="number" class="form-control" id="month1" name="month1" min="1" max="12" placeholder="MM" required>
                            <input type="number" class="form-control" id="year1" name="year1" min="1" placeholder="YYYY" required>
                        </div>
                    </div>
                    <div class="col-md-3 mb-3">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">To</span>
                            </div>
                            <input type="number" class="form-control" id="month2" name="month2" min="1" max="12" placeholder="MM" required>
                            <input type="number" class="form-control" id="year2" name="year2" min="1" placeholder="YYYY" required>
                        </div>
                    </div>
                    <div class="col-md-3 mb-3">
                        <button type="submit" class="btn btn-primary">Filter</button>
                    </div>
                </div>
            </form>


            <div style='color: red'>${requestScope.annouce}</div>
            <table border="1" class="table table-bordered table-striped"> <!--Du lieu dua vao table-->
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Consul</th>
                        <th>Total budget</th>
                    </tr>
                </thead>
                <c:set var="sum" value="${0}"></c:set>
                <c:forEach items="${requestScope.data}" var="c">
                    <tr>
                        <td>${c.id}</td>
                        <td>${c.name}</td>
                        <!--Tam thoi de href nhu vay, sau nay chinh lai theo dung id -->
                        <c:if test = "${c.consul != null}"><td><a href="consul">${c.consul.name}</a></td></c:if>
                        <c:if test = "${c.consul == null}"><td>Doesn't have</td></c:if>
                        <fmt:formatNumber type="currency" value="${c.totalBudget}" currencySymbol="$" var="currencyValue"/>
                        <td>${currencyValue}</td>
                        <!--tinh sum trong vong lap-->
                        <c:set var="sum" value="${sum + c.totalBudget}"></c:set>
                    </tr> 
                </c:forEach>
            </table>
            <c:if test="${sessionScope.account.usertype eq 'emperor'}">
                <h6>
                    Total budget of kingdom: 
                    <fmt:formatNumber type="currency" value="${sum}" currencySymbol="$" var="currencyValue2"/>
                    ${currencyValue2}
                </h6>
            </c:if>
        </div>
    </body>
</html>
