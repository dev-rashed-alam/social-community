<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Add Location</title>
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
                        <h2>Add New Location</h2>
                    </div>
                </div>
            </div>
            <form:form
                    action="/location/save"
                    method="post"
                    modelAttribute="location"
            >
                <div class="form-group">
                    <label>Location Name</label>
                    <form:input
                            path="locationName"
                            value="${locaion.locationName}"
                            type="text"
                            class="form-control"
                            required="true"
                    />
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-success" value="Save">
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
