<%-- 
    Document   : financial
    Created on : Dec 21, 2023, 10:20:08 AM
    Author     : vothimaihoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Financial</title>
        <link rel="stylesheet" href="./css/style.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="layout/header.jsp" />
        <div class="content">
            <h1>List of financial transactions</h1>
            <c:set var="page" value="${requestScope.page}"/>
            <div class="pagination">
                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                    <a class="${i==page?"active":""}" href="financial-transaction?page=${i}">${i}</a>
                </c:forEach>
            </div>
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Region ID</th>
                        <th>Transaction Type</th>
                        <th>Amount</th>
                        <th>Description</th>
                        <th>Report Date</th>
                            <c:if test="${sessionScope.account.usertype eq 'emperor'}">
                            <th>
                                Is suspicious
                            </th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.data}" var="transaction">
                        <tr>
                            <td>${transaction.id}</td>
                            <td><a href="region">${transaction.regionId}</a></td> <!--Link toi thang region-->
                            <c:if test="${transaction.transactionType == 'outcome'}"><td><span class=" badge bg-danger text-light"> ${transaction.transactionType} </span></td></c:if>
                            <c:if test="${transaction.transactionType != 'outcome'}"><td><span class=" badge bg-success text-light"> ${transaction.transactionType} </span></td></c:if>
                            <fmt:formatNumber type="currency" value="${transaction.amount}" currencySymbol="$" var="currencyValue"/>
                            <td>${currencyValue}</td>

                            <td>${transaction.description}</td>
                            <td>${transaction.transactionDate}</td>
                            <c:if test="${sessionScope.account.usertype eq 'emperor'}">
                                <td>

                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <c:if test="${sessionScope.account.usertype eq 'consul'}">
                <h1>New Transaction</h1>
                <form action="financial-transaction" method="post" class="container mt-5">
                    <div class="form-group">
                        <label for="transactionType">Transaction Type:</label>
                        <select class="form-control" id="transactionType" name="transactionType" required>
                            <option value="income">Income</option>
                            <option value="outcome">Outcome</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="amount">Amount:</label>
                        <input type="text" class="form-control" id="amount" name="amount" pattern="^\d+(\.\d{1,2})?$" title="Please enter a valid decimal number" required>
                    </div>

                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea class="form-control" id="description" name="description" rows="4" cols="50" required></textarea>
                    </div>

                    <input type="hidden" name="regionId" value="${sessionScope.region.id}"> <!--doi thanh id lay tu session cua consul-->

                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </c:if>
        </div>
        <jsp:include page="layout/footer.jsp" />
    </body>
</html>
