<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 13.11.2016.
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>JAVA2 | Register</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script>
        <!--
        function message1(str)
        {
            alert(str);
            document.registerForm.username.focus();
        }

        function message2(str)
        {
            alert(str);
            document.registerForm.loginBut.focus();
        }

        //-->
    </script>
</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen   animated fadeInDown">
    <div>
        <div>

            <h1 class="logo-name">JAVA2</h1>

        </div>
        <h3>Register to Social Network</h3>
        <p>Create account to see it in action.</p>
        <form class="m-t" role="form" action="doAddUser" method="post" name="registerForm">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Username" required="" name="username"  value="${user.username}">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="Password" required="" name="password" value="${user.password}">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="Repeat password" required="" name="password_repeat">
            </div>

            <div class="form-group">
                <input type="text" class="form-control" placeholder="First name" required="" name="firstName" value="${user.firstName}">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Last name" required="" name="lastName" value="${user.lastName}">
            </div>

            <div class="form-group">
                <input type="text" class="form-control" placeholder="Date of birth" required="" id="datePicker" name="date_of_birth" readonly value="${user.date_of_birth}" style="cursor: pointer">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Country" required="" name="country" value="${user.country}">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="City" required="" name="city" value="${user.city}">
            </div>
            <div class="form-group">
                <select class="form-control" id="sex" name="sex" value="${user.sex}">
                    <option value="M">Male</option>
                    <option value="F">Female</option>
                </select>
            </div>
            <div class="form-group">
                <select class="form-control" id="looking_for" name="looking_for" value="${user.looking_for}">
                    <option value="F">Female</option>
                    <option value="M">Male</option>
                </select>
            </div>

            <div class="form-group">
                <input type="text" class="form-control" id="age_from" placeholder="Age from" name="age_from" value="${user.age_from}">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="age_to" placeholder="Age to" name="age_to" value="${user.age_to}">
            </div>
            <div class="form-group">
                <textarea class="form-control" placeholder="About" name="about" value="${user.about}"></textarea>
            </div>

            <div class="form-group">
                <div class="checkbox i-checks"><label> <input type="checkbox" class="form-control" required=""><i></i> Agree the terms and policy </label></div>
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b" >Register</button>

            <p class="text-muted text-center"><small>Already have an account?</small></p>
            <a class="btn btn-sm btn-white btn-block" href="${pageContext.request.contextPath}/login" name="loginBut">Login</a>
        </form>
        <p class="m-t"> <small>All rights reserved to Java2 development team &copy; 2016</small> </p>
    </div>
</div>

<!-- Mainly scripts -->
<script src="js/jquery-2.1.1.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="js/plugins/iCheck/icheck.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $( function() {
        $( "#datePicker" ).datepicker({
            dateFormat: "yy-mm-dd",
            changeMonth: true,
            changeYear: true
        });
    } );
</script>
<script>
    $(document).ready(function(){
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });


        if ("" != "${user.sex}")
            $("#sex").val("${user.sex}");
        if ("" != "${user.looking_for}")
            $("#looking_for").val("${user.looking_for}");

        if ($("#age_from").val() == 0 )
            $("#age_from").val("")
        if ($("#age_to").val() == 0 )
            $("#age_to").val("")


        if ("" != `${errorString}`)
            message1(`${errorString}`);
        if ("" != `${successString}`)
            message2(`${successString}`);


    });
</script>
</body>

</html>

