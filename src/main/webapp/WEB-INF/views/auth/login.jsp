<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth.css" />
</head>

<body>
<section class="area-login">
    <div class="login">
        <div>
            <p>SCP</p>
        </div>
        <form action="/login-process" method="post">
            <input type="text" name="email" placeholder="Email" autofocus />
            <input type="password" name="password" placeholder="Password" />
            <input type="submit" value="Submit" />
        </form>
        <p>Have no account? <a href="/user/add">Register</a></p>
    </div>
</section>
</body>

</html>