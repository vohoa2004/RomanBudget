<%-- 
    Document   : tax
    Created on : Dec 21, 2023, 12:02:28 PM
    Author     : vothimaihoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tax</title>
        <link rel="stylesheet" href="./css/stylelisttab.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="content">
            <h1>List of Taxes</h1>
            <c:set var="page" value="${requestScope.page}"/>
            <div class="pagination">
                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                    <a class="${i==page?"active":""}" href="tax?page=${i}">${i}</a>
                </c:forEach>
            </div>
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Region ID</th>
                        <th>Tax Name</th>
                        <th>Period</th>
                        <th>Tax Amount</th>
                        <th>Tribute Amount</th>
                        <th>Deadline</th>
                        <th>Paid Date</th>
                            <c:if test="${sessionScope.account.usertype eq 'consul'}">
                            <th>Submit Tax</th>
                            </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.data}" var="tax">
                        <tr>
                            <td>${tax.id}</td>
                            <td>${tax.regionId}</td>
                            <td>${tax.category.name}</td>
                            <td>${tax.year}</td>
                            <td>${tax.taxAmount}</td>
                            <td>${tax.tributeAmount}</td>
                            <td>${tax.deadline}</td>
                            <c:if test="${tax.paidDate != null}"><td>${tax.paidDate}</td>
                                <c:if test="${sessionScope.account.usertype eq 'consul'}">
                                    <td style="color: red">Done</td>
                                </c:if>
                            </c:if>
                            <c:if test="${tax.paidDate == null}"><td>_</td>
                                <c:if test="${sessionScope.account.usertype eq 'consul'}">
                                    <td>
                                        <form action="tax" method="post">
                                            <input type="hidden" name="taxId" value="${tax.id}"> <!--doi thanh id lay tu session cua consul-->
                                            <button type="submit" class="btn btn-primary">Submit</button>
                                        </form>
                                    </td>
                                </c:if>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:if test="${sessionScope.account.usertype eq 'emperor'}">
                <h1>New Tax</h1>
                <form action="tax" method="post" class="container mt-5">
                    <div class="form-group">
                        <label for="region">Region</label>
                        <select class="form-control" id="regionId" name="regionId" required>
                            <c:forEach items="${sessionScope.regions}" var="i">
                                <option value="${i.id}">${i.name} (${i.id})</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="taxCategory">Tax Category</label>
                        <select class="form-control" id="taxCategory" name="taxCategory" required>
                            <c:forEach items="${sessionScope.taxCategories}" var="i">
                                <option value="${i.id}">${i.name} (${i.unitPrice * 100}%)</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="year">Period Year: </label>
                        <input type="text" class="form-control" id="year" name="year" pattern="^\d+$" title="Please enter a positive integer" required>
                    </div>

                    <div class="form-group">
                        <label for="taxAmount">Tax Amount:</label>
                        <input type="text" class="form-control" id="amount" name="taxAmount" pattern="^\d+(\.\d{1,2})?$" title="Please enter a valid decimal number" required>
                    </div>

                    <div class="form-group">
                        <label for="tributeAmount">Tribute Amount:</label>
                        <input type="text" class="form-control" id="amount" name="tributeAmount" pattern="^\d+(\.\d{1,2})?$" title="Please enter a valid decimal number" required>
                    </div>

                    <div class="form-group">
                        <label for="deadline">Deadline:</label>
                        <input type="date" class="form-control" id="amount" name="deadline" required>

                    </div>

                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>

            </c:if>

        </div>
    </body>
</html>
