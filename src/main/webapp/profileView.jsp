<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 13.11.2016.
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>JAVA2 | Edit info</title>
    <jsp:include page="_header.jsp"></jsp:include>
</head>
<body>

<div class="off-canvas-wrapper" style="height:100% !important;background-color:#e6e6e6;">
    <div class="off-canvas-wrapper-inner" data-off-canvas-wrapper style="height:100%;background-color:#e6e6e6;">

        <div style="width: 240px; z-index:0" class="off-canvas position-left reveal-for-large" id="my-info" data-off-canvas data-position="left">
            <div class="row column">
                <br>
                <form name="uploadForm" id="uploadForm" method="POST" action="doProfile" enctype="multipart/form-data">
                    <input type="hidden" name="uploadForm" value="true">
                    <input type="file" id="avatar" name="avatar" style="display:none;"/>
                    <!--<input type="submit" value="upload" />-->
                </form>
                <img class="thumbnail" src="${pageContext.request.contextPath}/image/${user.username}/avatar.jpg" id="avatarImg" style="cursor:pointer;">
                <h5><a href="${pageContext.request.contextPath}/profile">${user.firstName} ${user.lastName}</a></h5>
                <h6 style="font-size: 0.9rem">${user.country}, ${user.city}</h6>
                <p>${user.about}</p>
            </div>
        </div>

        <div class="off-canvas-content" data-off-canvas-content>
            <div class="title-bar hide-for-large">
                <div class="title-bar-left">
                    <button class="menu-icon" type="button" data-open="my-info"></button>
                    <span class="title-bar-title"><a href="${pageContext.request.contextPath}/profile">${user.firstName} ${user.lastName}</a></span>
                </div>
            </div>
            <div class="callout primary">
                <div style="position: absolute; right: 20px;top:20px;"><a href="${pageContext.request.contextPath}/logout"><strong><i class="fa fa-sign-out" aria-hidden="true"></i>
                    Logout</strong></a></div>
                <div class="row column">
                    <h1>JAVA2 Social Network</h1>
                    <p class="lead">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus luctus urna sed urna ultricies ac tempor dui sagittis. In condimentum facilisis porta.</p>
                </div>
            </div>

            <jsp:include page="_menu.jsp"></jsp:include>

            <div class="row">
                <form name="editform" method="POST" action="doProfile">
                <div class="column" style="text-align: center"><span id="error" style="color: red">${errorString}</span><span id="success" style="color: #3eff13">${successString}</span>&nbsp;</div>
                <div class="column"><h3>My Profile</h3></div>
                <input type="hidden" name="UserID" value="${user.userId}" />
                <div class="medium-6 columns">

                    <label>Username
                        <input type="text" placeholder="Name" name="username" value="${user_in_edit.username}" readonly>
                    </label>
                    <label>Name
                        <input type="text" placeholder="Name" name="firstName" value="${user_in_edit.firstName}">
                    </label>
                    <label>Last name
                        <input type="text" placeholder="Last name" name="lastName" value="${user_in_edit.lastName}">
                    </label>
                    <label>City
                        <input type="text" placeholder="City" name="city" value="${user_in_edit.city}">
                    </label>
                    <label>Country
                        <input type="text" placeholder="Country" name="country" value="${user_in_edit.country}">
                    </label>



                </div>
                <div class="medium-6 columns">
                    <label>Looking for
                        <select name="looking_for" id="looking_for" value="${user_in_edit.looking_for}">
                            <option value="F">Female</option>
                            <option value="M">Male</option>
                        </select>
                    </label>
                    <label>From
                        <input placeholder="From" id="age_from" type="text" name="age_from" value="${user_in_edit.age_from}">
                    </label>
                    <label>To
                        <input placeholder="To" id="age_to" type="text" name="age_to" value="${user_in_edit.age_to}">
                    </label>

                    <label>
                        About
                        <textarea placeholder="About" name="about" style="height: 3.9rem; line-height:1.2">${user_in_edit.about}</textarea>
                    </label>
                    <input type="submit" class="button expanded" value="Submit">
                </div>
                </form>
            </div>

            <div class="row">
                    <div class="column"><h3>Profile</h3></div>
                    <form name="editPasswordForm" method="POST" action="doProfile">
                        <input type="hidden" name="passwordForm" value="true" />
                        <input type="hidden" name="username" value="${user.username}" />
                        <input type="hidden" name="UserID" value="${user.userId}" />

                    <div class="medium-6 columns">
                        <label>Password
                            <input type="text" placeholder="New password" name="password" value="">
                        </label>
                    </div>
                    <div class="medium-6 columns">
                        <label>Password repeat
                            <input placeholder="Repeat new password" type="text" name="password_repeat" value="">
                        </label>
                        <input type="submit" class="button expanded" value="Submit">
                    </div>
                </form>
            </div>

            <div class="row column" style="text-align:center; max-width: 100%">
                <p><small>All rights reserved to Java2 development team &copy; 2016</small></p>
            </div>

        </div>
    </div>
</div>

<jsp:include page="_footer.jsp"></jsp:include>
<script>
    $( document ).ready(function() {
        $("#sex").val("${user_in_edit.sex}");
        $("#looking_for").val("${user_in_edit.looking_for}");
        if ($("#age_from").val() == 0 )
            $("#age_from").val("")
        if ($("#age_to").val() == 0 )
            $("#age_to").val("")

        $('#avatarImg').on('click', function() {
            document.getElementById('avatar').click(); return false;
        });




        $(function () {
            $(":file").change(function () {
                if (this.files && this.files[0]) {
                    var reader = new FileReader();

                    var file = this.files[0];
                    var fileType = file["type"];
                    var ValidImageTypes = ["image/gif", "image/jpeg", "image/png"];
                    if ($.inArray(fileType, ValidImageTypes) < 0) {
                        // invalid file type code goes here.
                    } else {
                        reader.onload = imageIsLoaded;
                        reader.readAsDataURL(this.files[0]);
                    }

                }
            });
        });

        function imageIsLoaded(e) {
            $('#avatarImg').attr('src', e.target.result);
        };


        $('#avatar').on('change', function() {
            var file_data = $('#avatar').prop('files')[0];
            var form_data = new FormData();
            form_data.append('file', file_data);
            //alert(form_data);
            $.ajax({
                url: '/upload', // point to server-side PHP script
                dataType: 'text',  // what to expect back from the PHP script, if anything
                cache: false,
                contentType: false,
                processData: false,
                data: form_data,
                type: 'post',
                success: function(php_script_response){
                    if (php_script_response == 1) {
                        $('#error').text('');
                        $('#success').text('File Uploaded Successfully');
                    }
                    else if (php_script_response == 2) {
                        $('#success').text('');
                        $('#error').text('Selected file is not an image!');
                    }
                    else {
                        $('#success').text('');
                        $('#error').text(php_script_response);
                    }

                    // display response from the PHP script, if any
                }
            });
        });


    });





</script>
</body>
</html>