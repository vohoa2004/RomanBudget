<%-- 
    Document   : king-request
    Created on : Dec 21, 2023, 3:43:48 PM
    Author     : vothimaihoa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>King Request</title>
        <link rel="stylesheet" href="./css/style.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.min.js"></script>

        <script src="//cdn.ckeditor.com/4.22.1/standard/ckeditor.js"></script>
    </head>
    <body>
        <jsp:include page="layout/header.jsp" />
        <div class="content">
            <h1>List of king's requests</h1>
            <c:set var="page" value="${requestScope.page}"/>
            <div class="pagination">
                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                    <a class="${i==page?"active":""}" href="king-request?page=${i}">${i}</a>
                </c:forEach>
            </div>
            <table class="table table-bordered table-striped" border="1">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Region ID</th>
                        <th>Request Date</th>
                        <th>Request Content</th>
                        <th>Report Deadline</th>
                        <th> Report Submitted </th>
                        <c:if test="${sessionScope.account.usertype eq 'consul'}"><th> Write Report </th></c:if>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.data}" var="request">
                        <tr>
                            <td>${request.id}</td>
                            <td>${request.regionId}</td>
                            <td>${request.requestDate}</td>
                            <td>${request.requestContent}</td>
                            <td>${request.deadline}</td>
                            <c:if test="${request.done}"><th class="text-success">Yes</th></c:if>
                            <c:if test="${!request.done}"><th class="text-danger">No</th></c:if>
                                <c:if test="${sessionScope.account.usertype eq 'consul'}">      
                                <td><a href='report?id=${request.id}'><button class="btn btn-warning">Click</button></a></td> <!--Khi click vo truyen theo id cua requestva tu dong dien vao form create report ben kia-->
                            </c:if>  
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
            <c:if test="${sessionScope.account.usertype eq 'emperor'}"><h1>New King's Request</h1>
                <form action="king-request" method="post">

                    <div class="form-group">
                        <label for="regionId">Region ID:</label>
                        <select class="form-control" id="transactionType" name="regionId" required>
                            <c:forEach items="${sessionScope.regions}" var="i">
                                <option value="${i.id}" ${!empty requestScope.kingRequest && requestScope.kingRequest.regionId eq i.id ? 'selected' : ''}>
                                    ${i.name} (${i.id})
                                </option>
                            </c:forEach>
                        </select>

                    </div>

                    <div class="form-group">
                        <label for="requestContent">Request Content:</label><br>
                        <textarea class="form-control" id="requestContent" name="requestContent" rows="4" cols="50" required>
                            ${empty requestScope.kingRequest ? '' : requestScope.kingRequest.requestContent}
                        </textarea>
                        <br>
                    </div>

                    <div class="form-group">
                        <label for="deadline">Report Deadline:</label>
                        <input class="form-control" type="date" id="deadline" name="deadline" required><br><br>
                    </div>

                    <input class="btn btn-primary" type="submit" value="Submit">
                </form>
            </c:if>


        </div>
        <jsp:include page="layout/footer.jsp" />
        <script>
            var editor = '';
            $(document).ready(function () {
                editor = CKEDITOR.replace('requestContent');
            });

        </script>

    </body>
</html>
