<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Add Post</title>
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
                        <h2>Add New Post</h2>
                    </div>
                </div>
            </div>
            <form:form
                    action="/savePost"
                    enctype="multipart/form-data"
                    method="post"
                    modelAttribute="story"
            >
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>Title</label>
                            <form:input
                                    path="title"
                                    value="${story.title}"
                                    type="text"
                                    class="form-control"
                                    required="true"
                            />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="location">Location</label>
                            <br/>
                            <select class="form-select form-control" name="locationId" id="location">
                                <option selected disabled>Select Location</option>
                                <c:forEach var="location" items="${locations}">
                                    <option value="${location.id}">${location.locationName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="form-group">
                            <label>Description</label>
                            <form:textarea
                                    path="description"
                                    value="${story.description}"
                                    type="text"
                                    class="form-control"
                                    required="true"
                            />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group file-field">
                            <input class="form-control" type="file" name="attachment" id="contact-attachment">
                            <label class="form-label" for="contact-attachment">
                                <i class="fas fa-folder mr-2"></i>
                                Upload Attachment...
                            </label>
                        </div>
                    </div>
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
