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
        <title>Report</title>
        <link rel="stylesheet" href="./css/stylelisttab.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.min.js"></script>
        <script src="//cdn.ckeditor.com/4.22.1/standard/ckeditor.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp" />
        <div class="content">
            <c:if test="${sessionScope.account.usertype eq 'consul'}">
                <h1>Create new report</h1>
                <form action="report" method="post" class="container mt-5">
                    <div class="form-group">
                        <label for="requestId">Request Id</label>
                        <input type="number" class="form-control" id="requestId" name="requestId" 
                               <c:if test="${requestScope.req_id != null}">value='${requestScope.req_id}'</c:if>>

                        </div>

                        <div class="form-group">
                            <label for="writtenDate">Written Date:</label>
                            <input type="date" class="form-control" id="writtenDate" name="writtenDate" required>
                        </div>

                        <div class="form-group">
                            <label for="location">Location:</label>
                            <input type="text" class="form-control" id="location" name="location" required>
                        </div>

                        <div class="form-group">
                            <label for="title">Report title:</label>
                            <input type="text" class="form-control" id="title" name="title" required>
                        </div>

                        <div class="form-group">
                            <label>Choose way to report</label><br>
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="textType" name="link" value="text" required>
                                <label class="form-check-label" for="textType">Text</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="radio" class="form-check-input" id="linkType" name="link" value="link" required>
                                <label class="form-check-label" for="linkType">Link</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="reportData">Report Data:</label><br>
                            <textarea class="form-control" id="reportData" name="reportData" rows="4" cols="50" required="Content must be filled"></textarea>
                        </div>

                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
            </c:if>
            <br/>

            <h1>List of reports</h1>
            <c:set var="page" value="${requestScope.page}"/>
            <div class="pagination">
                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                    <a class="${i==page?"active":""}" href="report?page=${i}">${i}</a>
                </c:forEach>
            </div>
            <table border="1" class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>Report ID</th>
                        <th>Request ID</th>
                        <th>Request content</th>
                        <th>Location</th>
                        <th>Report title</th>
                        <th>Writer</th>
                        <th>Report Data</th>
                        <th>Written Date</th>
                        <th>Delivered Date</th>
                        <th>Status</th>
                        <th>King's Comment</th>
                    </tr>
                </thead>
                <c:forEach items="${requestScope.data}" var="report" varStatus="loop">
                    <tr>
                        <td>${report.reportId}</td>
                        <td>${report.request.id}</td> <!--de link dan toi cho request-->
                        <td>${report.request.requestContent}</td> <!--de link dan toi cho request-->
                        <td>${report.location}</td>
                        <td>${report.title}</td>
                        <td>${report.consul.name}</td>
                        <td>
                            <c:choose>
                                <c:when test="${report.link}">
                                    <a href="${report.reportData}">Link to the report</a>
                                </c:when>
                                <c:when test="${!report.link}">
                                    ${report.reportData}
                                </c:when>
                            </c:choose>
                        </td>
                        <td>${report.writtenDate}</td>
                        <td>${report.deliveredDate}</td>
                        <td>
                            <span class=" badge 
                                  <c:choose>
                                      <c:when test="${report.status eq 'pending'}">bg-warning</c:when>
                                      <c:when test="${report.status eq 'rejected'}">bg-danger</c:when>
                                      <c:when test="${report.status eq 'approved'}">bg-success</c:when>
                                  </c:choose>
                                  ">
                                ${report.status}
                            </span>
                            <br/><br/>
                            <c:if test="${sessionScope.account.usertype eq 'emperor'}"><!-- Button để mở modal chỉnh sửa status -->
                                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal1-${loop.index}">Edit</button>
                                <div class="modal" id="modal1-${loop.index}">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">Edit status</div>
                                            <div class="modal-body"><form id="statusForm" action="update" method="get">
                                                    <input type="hidden" id="reportIdModal1" name="reportId1" value="${report.reportId}">
                                                    <label for="statusSelect">Select Status:</label>
                                                    <select class="form-control" id="statusSelect" name="selectedStatus">
                                                        <option value="pending">Pending</option>
                                                        <option value="rejected">Rejected</option>
                                                        <option value="approved">Approved</option>
                                                    </select>
                                                    <button type="submit" class="btn btn-primary mt-3">Save</button>
                                                </form></div>
                                            <div class="modal-footer"></div>
                                        </div>
                                    </div>
                                </div></c:if>
                            </td>
                            <td>
                            ${report.comment}<br/>
                            <!-- Button để mở modal chỉnh sửa comment -->
                            <c:if test="${sessionScope.account.usertype eq 'emperor'}">
                                <button class="btn btn-info" data-bs-toggle="modal" data-bs-target="#modal2-${loop.index}">
                                    Edit
                                </button>
                                <div class="modal" id="modal2-${loop.index}">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">Edit comment</div>
                                            <div class="modal-body">
                                                <form id="commentForm" action="update" method="post">
                                                    <input type="hidden" id="reportIdModal2" name="reportId2" value="${report.reportId}">
                                                    Comment: <textarea rows="8" cols="60" id="comment" name="comment"></textarea>
                                                    <button type="submit" class="btn btn-primary mt-3">Save</button>
                                                </form></div>
                                            <div class="modal-footer"></div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>

                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>
        <script>
            var editor = '';
            $(document).ready(function () {
                editor = CKEDITOR.replace('reportData');
            });

        </script>
    </body>
</html>
