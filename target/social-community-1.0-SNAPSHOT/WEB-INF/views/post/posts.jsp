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
    <div class="row mt-2">
        <div class="col-md-12">
            <div class="row">
                <c:forEach var="post" items="${posts}">
                    <div class="col-md-3">
                        <div class="card">
                            <div class="custom-card-img">
                                <img
                                        src="/file/get/${post.getStoryAttachments()[0].getId()}"
                                        class="card-img-top"
                                        alt="post_img"
                                />
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">${post.title}</h5>
                                <p class="card-text">${post.description}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
