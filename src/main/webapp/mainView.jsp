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
    <title>JAVA2 | Welcome</title>
    <jsp:include page="_header.jsp"></jsp:include>
</head>
<body>

<div class="off-canvas-wrapper" style="height:100% !important;background-color:#e6e6e6;">
    <div class="off-canvas-wrapper-inner" data-off-canvas-wrapper style="height:100%;background-color:#e6e6e6;">

        <div style="width: 240px; z-index:0;height:100%" class="off-canvas position-left reveal-for-large" id="my-info" data-off-canvas data-position="left">
            <div class="row column">
                <br>
                <img class="thumbnail" src="${pageContext.request.contextPath}/image/${user.username}/avatar.jpg" id="avatarImg">
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


            <div class="row small-up-2 medium-up-3 large-up-4">


                <c:forEach items="${usersFound}" var="userFound" >

                    <div class="column" style="text-align: center">
                        <a href="${pageContext.request.contextPath}/user/${userFound.userId}"><img class="thumbnail" src="${pageContext.request.contextPath}/image/${userFound.username}/avatar.jpg"></a>
                        <h6><i id="dot${userFound.userId}" class="fa fa-circle" aria-hidden="true" style="color:#e6e6e6"></i> <a href="${pageContext.request.contextPath}/user/${userFound.userId}">${userFound.firstName}, ${userFound.lastName}</a></h6>
                        <h6>${userFound.city}, ${userFound.country}</h6>
                    </div>

                </c:forEach>

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
        if ("" != "${errorString}") {
            $("#alertMessage").html("You have no friends :(");
            $("#alertMessage").show();
        }
        if ("" != "${successString}") {
            if ("already" == "${successString}") {
                $("#alertMessage2").removeClass();
                $("#alertMessage2").addClass("alert-box warning round");
                $("#alertMessage2").html("You are already friends!");
            } else if ("expired" == "${successString}") {
                $("#alertMessage2").removeClass();
                $("#alertMessage2").addClass("alert-box warning round");
                $("#alertMessage2").html("Invitation expired!");
            } else if ("accepted" == "${successString}") {
                $("#alertMessage2").removeClass();
                $("#alertMessage2").addClass("alert-box success radius");
                $("#alertMessage2").html("Invitation accepted!");

            }
            $("#alertMessage2").show();
        }
        <% session.setAttribute("successString",""); %>
        <c:forEach items="${online}" var="userOnline"><c:if test="${userOnline.userId > 0}">$("#dot${userOnline.userId}").css('color', 'green');</c:if></c:forEach>

    });
</script>
</body>
</html>