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
    <title>JAVA2 | User Info</title>
    <jsp:include page="_header.jsp"></jsp:include>
</head>
<body>

<div class="off-canvas-wrapper" style="height:100% !important;background-color:#e6e6e6;">
    <div class="off-canvas-wrapper-inner" data-off-canvas-wrapper style="height:100%;background-color:#e6e6e6;">

        <div style="width: 240px; z-index:0" class="off-canvas position-left reveal-for-large" id="my-info" data-off-canvas data-position="left">
            <div class="row column">
                <br>
                <img class="thumbnail" src="${pageContext.request.contextPath}/image/${userFound.username}/avatar.jpg" id="avatarImg">
                <h5><a href="#">${userFound.firstName} ${userFound.lastName}</a></h5>
                <h6 style="font-size: 0.9rem">${userFound.country}, ${userFound.city}</h6>
                <p>${userFound.about}</p>
            </div>
        </div>

        <div class="off-canvas-content" data-off-canvas-content>
            <div class="title-bar hide-for-large">
                <div class="title-bar-left">
                    <button class="menu-icon" type="button" data-open="my-info"></button>
                    <span class="title-bar-title"><a href="#">${userFound.firstName} ${userFound.lastName}</a></span>
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

                    <div class="column" style="text-align: center"><span id="error" style="color: red">${errorString}</span><span id="success" style="color: #3eff13">${successString}</span>&nbsp;</div>
                    <div class="column"><h3>${userFound.firstName} ${userFound.lastName} Profile</h3></div>
                    <input type="hidden" name="UserID" value="${user.userId}" />
                    <div class="medium-6 columns">

                        <label>Username
                            <input type="text" name="username" value="${userFound.username}" readonly>
                        </label>
                        <label>Name
                            <input type="text" name="firstName" value="${userFound.firstName}">
                        </label>
                        <label>Last name
                            <input type="text" name="lastName" value="${userFound.lastName}">
                        </label>
                        <label>City
                            <input type="text" name="city" value="${userFound.city}">
                        </label>
                        <label>Country
                            <input type="text" name="country" value="${userFound.country}">
                        </label>
                        <label>
                            About
                            <textarea name="about" style="height: 7.5rem; line-height:1.2">${userFound.about}</textarea>
                        </label>







                    </div>
                    <div class="medium-6 columns">
                        <label>Looking for
                            <select name="looking_for" id="looking_for" value="${userFound.looking_for}">
                                <option value="F">Female</option>
                                <option value="M">Male</option>
                            </select>
                        </label>
                        <label>From
                            <input id="age_from" type="text" name="age_from" value="${userFound.age_from}">
                        </label>
                        <label>To
                            <input id="age_to" type="text" name="age_to" value="${userFound.age_to}">
                        </label>

                        <label>
                            Friend of:
                            <textarea name="Friend of:" style="height: 7.5rem; line-height:1.2">${friendsOfFriend}</textarea>
                        </label>

                    </div>

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

        if ($("#age_from").val() == 0 )
            $("#age_from").val("")
        if ($("#age_to").val() == 0 )
            $("#age_to").val("")

        $('#looking_for').attr('disabled', 'true');
        $('input,textarea').attr('readonly', 'true');


        var confirmMessage = "Are you sure?";

        $("[action='add'],[action='remove']").on('click', function (e) {
            e.preventDefault();
            //$("#alertMessage").hide();

            if ($("#friendLink").attr("action")=='add')
                var confirmMsg = 'Add ${userFound.firstName} ${userFound.lastName} to friends?';
            else
                var confirmMsg = 'Remove ${userFound.firstName} ${userFound.lastName} from friends?';
            $.confirm({
                title: 'Confirm!',
                content: confirmMsg,
                useBootstrap: false,
                buttons: {
                    confirm: function () {
                        //$.alert('Confirmed!');
                        act();
                    },
                    cancel: function () {
                        //$.alert('Canceled!');
                    }
                }
            });

            function act() {
                    var url = "/friend/" + $("#friendLink").attr("action");
                    $.ajax({
                        url : url,
                        type: "POST",
                        data : {user_id: ${user.userId}, friend_id:${userFound.userId}},
                        success: function(data, textStatus, jqXHR)
                        {
                            if (data == 'true') {
                                $("#alertMessage").html("You are now friends with ${userFound.firstName} ${userFound.lastName}");
                                $("#alertMessage").show();
                                $("#friendLink").attr("action","remove");
                                $("#friendLink").html("<i class=\"fa fa-minus-circle\" aria-hidden=\"true\"></i> Remove friend");
                            }
                            else if (data == 'false') {
                                $("#alertMessage").html("${userFound.firstName} ${userFound.lastName} was just removed from your friends.");
                                $("#alertMessage").show();
                                $("#friendLink").attr("action","add");
                                $("#friendLink").html("<i class=\"fa fa-plus-circle\" aria-hidden=\"true\"></i> Add friend");
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown)
                        {

                        }
                    });
            }

        })

    });

</script>
</body>
</html>