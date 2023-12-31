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
        <link rel="stylesheet" href="./css/style.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    </head>
    <body>
        <jsp:include page="layout/header.jsp" />
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
            <form action="region?action=filter" method="post">
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
                        <th>Region ID</th>
                        <th>Name</th>
                        <th>Total budget</th>
                        <th>Consul</th>
                        <th>Term start year</th>
                        <th>Status</th>

                    </tr>
                </thead>
                <c:forEach items="${requestScope.data}" var="c">
                    <tr>
                        <td>${c.region.id}</td>
                        <td>${c.region.name}</td>

                        <c:if test = "${c.consul == null}"><td>Doesn't have</td></c:if>
                        <fmt:formatNumber type="currency" value="${c.region.totalBudget}" currencySymbol="$" var="currencyValue"/>
                        <td>${currencyValue}</td>
                        <c:if test = "${c.consul != null}">
                            <td><c:if test = "${sessionScope.account.usertype eq 'emperor'}">
                                    <a href="consul?action=info&id=${c.consul.id}">
                                        ${c.consul.name}
                                    </a>
                                </c:if>
                                <c:if test = "${sessionScope.account.usertype eq 'consul'}">
                                    ${c.consul.name}
                                </c:if>
                            </td>
                        </c:if>
                        <td>${c.startYear}</td>
                        <td>${c.status}</td>
                    </tr> 
                </c:forEach>
                
            </table>
            <br/>


            <c:if test="${sessionScope.account.usertype eq 'emperor'}">
                <div style="text-align: center">
                    <h5 style="color: red">                    
                        Total budget of kingdom: 
                    </h5>
                    <h5 style="color: blue">
                        <fmt:formatNumber type="currency" value="${requestScope.sum}" currencySymbol="$" var="currencyValue2"/>
                        ${currencyValue2}
                    </h5>
                </div>
                     <!--Assign new consul to a region (optional)
           1. Checkbox: assign or not
           2. If yes => do post submit form and insert new consul to consul_region 
           3. If no => do nothing with table consul_region-->
            <h3 class="text-primary">Assign consul to region</h3>
            <form action="region?action=assign" method="post">
                <div class="form-group">
                    <label for="startYear">Consul ID: </label>
                    <input type="number" class="form-control" id="consulId" name="consulId" value="<c:if test="${requestScope.consulId != null}">requestScope.consulId</c:if>" required>
                    </div>
                    <div class="form-group">
                        <label for="regionId">Region ID:</label>
                        <select class="form-control" id="regionId" name="regionId">
                        <c:forEach items="${sessionScope.regions}" var="i">
                            <option value="${i.id}">
                                ${i.name} (${i.id})
                            </option>
                        </c:forEach>
                    </select>

                </div>
                <div class="form-group">
                    <label for="startYear">Term Start Year: </label>
                    <input type="number" class="form-control" id="startYear" name="startYear">
                </div> 
                <div class="col-md-3 mb-3">
                    <button type="submit" class="btn btn-primary">Assign</button>
                </div>
            </form>
            </c:if>

           
        </div>        
        <jsp:include page="layout/footer.jsp"/>
    </body>
</html>
