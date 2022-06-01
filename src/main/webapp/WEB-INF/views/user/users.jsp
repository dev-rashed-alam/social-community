<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Locations</title>
    <jsp:include page="../include/css.jsp"/>
</head>
<body>
<jsp:include page="../include/navbar.jsp"/>
<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Manage Users</h2>
                    </div>
                    <div class="col-sm-6">
                        <a href="${pageContext.request.contextPath}/addUser" class="btn btn-success">
                            <i class="material-icons">&#xE147;</i>
                            <span>Add New User</span>
                        </a>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${empty users}">
                    <p class="text-center">No Data Found!</p>
                </c:when>
                <c:otherwise>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>SL</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Location</th>
                            <th>Picture</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${users}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${user.name}</td>
                                <td>${user.email}</td>
                                <td>${user.getLocation().getLocationName()}</td>
                                <td>${user.getAttachment().getAttachmentPath()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
