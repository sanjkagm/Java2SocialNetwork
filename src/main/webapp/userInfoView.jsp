<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 13.11.2016.
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>JAVA2 | Welcome</title>
    <link rel="stylesheet" href="http://dhbhdrzi4tiry.cloudfront.net/cdn/sites/foundation.min.css">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">
</head>
<body>

<div class="off-canvas-wrapper" style="height:100% !important;background-color:#e6e6e6;">
    <div class="off-canvas-wrapper-inner" data-off-canvas-wrapper style="height:100%;background-color:#e6e6e6;">

        <div style="width: 240px; z-index:0;height:100%" class="off-canvas position-left reveal-for-large" id="my-info" data-off-canvas data-position="left">
            <div class="row column">
                <br>
                <img class="thumbnail" src="${pageContext.request.contextPath}/image/${user.username}/avatar.jpg" id="avatarImg" style="max-width: 215px;">
                <h5><a href="${pageContext.request.contextPath}/editUser">${user.firstName} ${user.lastName}</a></h5>
                <h6 style="font-size: 0.9rem">${user.country}, ${user.city}</h6>
                <p>${user.about}</p>
            </div>
        </div>

        <div class="off-canvas-content" data-off-canvas-content>
            <div class="title-bar hide-for-large">
                <div class="title-bar-left">
                    <button class="menu-icon" type="button" data-open="my-info"></button>
                    <span class="title-bar-title"><a href="${pageContext.request.contextPath}/editUser">${user.firstName} ${user.lastName}</a></span>
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

            <div class="row column" style="margin-top: -1em; max-width: 100%">
                <ul class="menu">
                    <li><a href="#">Friends</a></li>
                    <li><a href="#">Messages</a></li>
                    <li><a href="#">Search</a></li>
                </ul>
            </div>

            <div class="row small-up-2 medium-up-3 large-up-4">
                <div class="column">
                    <img class="thumbnail" src="http://placehold.it/550x550">
                    <h5>My Site</h5>
                </div>
                <div class="column">
                    <img class="thumbnail" src="http://placehold.it/550x550">
                    <h5>My Site</h5>
                </div>
                <div class="column">
                    <img class="thumbnail" src="http://placehold.it/550x550">
                    <h5>My Site</h5>
                </div>
                <div class="column">
                    <img class="thumbnail" src="http://placehold.it/550x550">
                    <h5>My Site</h5>
                </div>
                <div class="column">
                    <img class="thumbnail" src="http://placehold.it/550x550">
                    <h5>My Site</h5>
                </div>
                <div class="column">
                    <img class="thumbnail" src="http://placehold.it/550x550">
                    <h5>My Site</h5>
                </div>
                <div class="column">
                    <img class="thumbnail" src="http://placehold.it/550x550">
                    <h5>My Site</h5>
                </div>
                <div class="column">
                    <img class="thumbnail" src="http://placehold.it/550x550">
                    <h5>My Site</h5>
                </div>
                <div class="column">
                    <img class="thumbnail" src="http://placehold.it/550x550">
                    <h5>My Site</h5>
                </div>
                <div class="column">
                    <img class="thumbnail" src="http://placehold.it/550x550">
                    <h5>My Site</h5>
                </div>
                <div class="column">
                    <img class="thumbnail" src="http://placehold.it/550x550">
                    <h5>My Site</h5>
                </div>
                <div class="column">
                    <img class="thumbnail" src="http://placehold.it/550x550">
                    <h5>My Site</h5>
                </div>
            </div>

            <div class="row column" style="text-align:center; max-width: 100%">
                <p><small>All rights reserved to Java2 development team &copy; 2016</small></p>
            </div>

        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="http://dhbhdrzi4tiry.cloudfront.net/cdn/sites/foundation.js"></script>
<script>
    $(document).foundation();
</script>
</body>
</html>