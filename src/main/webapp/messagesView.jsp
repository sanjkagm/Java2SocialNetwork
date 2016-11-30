<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 29.11.2016.
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>JAVA2 | Messages</title>
    <jsp:include page="_header.jsp"></jsp:include>
    <!-- Bower -->
    <script src="${pageContext.request.contextPath}/bower_components/webcomponentsjs/webcomponents-lite.min.js"></script>
    <link href="${pageContext.request.contextPath}/bower_components/vaadin-grid/vaadin-grid.html" rel="import">
</head>
<body>

<div class="off-canvas-wrapper" style="height:100% !important;background-color:#e6e6e6;">
    <div class="off-canvas-wrapper-inner" data-off-canvas-wrapper style="height:100%;background-color:#e6e6e6;">

        <div style="width: 240px; z-index:0;height:100%" class="off-canvas position-left reveal-for-large" id="my-info" data-off-canvas data-position="left">
            <div class="row column">
                <br>
                <img class="thumbnail" src="${pageContext.request.contextPath}/image/${loginedUser.username}/avatar.jpg" id="avatarImg">
                <h5><a href="${pageContext.request.contextPath}/profile">${loginedUser.firstName} ${loginedUser.lastName}</a></h5>
                <h6 style="font-size: 0.9rem">${loginedUser.country}, ${loginedUser.city}</h6>
                <p>${loginedUser.about}</p>
            </div>
        </div>

        <div class="off-canvas-content" data-off-canvas-content>
            <div class="title-bar hide-for-large">
                <div class="title-bar-left">
                    <button class="menu-icon" type="button" data-open="my-info"></button>
                    <span class="title-bar-title"><a href="${pageContext.request.contextPath}/profile">${loginedUser.firstName} ${loginedUser.lastName}</a></span>
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


                <vaadin-grid selection-mode="multi">
                    <table>
                        <!-- Define the columns and their mapping to data properties. -->
                        <col name="created" sortable sort-direction="asc">
                        <col name="sender">
                        <col name="message">

                        <!-- Define the column headings. -->
                        <thead>
                        <tr>
                            <th>Date</th>
                            <th>Sender</th>
                            <th>Message</th>
                        </tr>
                        </thead>
                    </table>
                </vaadin-grid>

                <script>
                    // The Web Components polyfill introduces a custom event we can
                    // use to determine when the custom elements are ready to be used.
                    document.addEventListener("WebComponentsReady", function () {

                        // Reference to the grid element.
                        var grid = document.querySelector("vaadin-grid");

                        // Add some example data as an array.
                        grid.items = [
                            <c:forEach items="${data}" var="message" >
                            { "created": "${message.created}", "sender": "${message.sender}", "message": "${message.text}" },
                            </c:forEach>
                        ];
                    });
                </script>

            </div>
<p>&nbsp;</p>
            <div class="row column" style="text-align:center; max-width: 100%">
                <p><small>All rights reserved to Java2 development team &copy; 2016</small></p>
            </div>

        </div>
    </div>
</div>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>