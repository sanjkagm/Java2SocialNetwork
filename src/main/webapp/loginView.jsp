<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 13.11.2016.
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>JAVA2 | Login</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <script>
        function display_error()
        {
            alert(`${errorString}`);
            document.loginForm.username.focus();
        }
    </script>
</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>

            <h1 class="logo-name">JAVA2</h1>

        </div>
        <h3>Welcome to Java2</h3>
        <p>Perfectly designed and precisely prepared social network web app.</p>
        <p>Login in. To see it in action.</p>
        <form class="m-t" role="form" method="post" action="doLogin" name="loginForm">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Username" required="" name="username" value= "${user.username}">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="Password" required="" name="password" value= "${user.password}">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">Login</button>

            <p class="text-muted text-center"><small>Do not have an account?</small></p>
            <a class="btn btn-sm btn-white btn-block" href="${pageContext.request.contextPath}/addUser">Create an account</a>
        </form>
        <p class="m-t"> <small>All rights reserved to Java2 development team &copy; 2016</small> </p>

    </div>
</div>

<!-- Mainly scripts -->
<script src="js/jquery-2.1.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>
    $( document ).ready(function() {
        if ("" != `${errorString}`)
            display_error();

    });
</script>

</body>

</html>
