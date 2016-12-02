<%@ page import="java.util.List" %>
<%@ page import="lv.javaguru.java2.domain.UserMessage" %><%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 29.11.2016.
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

                <c:if test="${not empty data}">
                <vaadin-grid style="padding-left:0.9375rem; height: <% List<UserMessage> list = (List<UserMessage>)request.getAttribute("data"); %><%=48*list.size()+56+200%>px">
                    <table>
                        <!-- Define the columns and their mapping to data properties. -->
                        <col name="id" sortable max-width="100">
                        <col name="created" sortable max-width="200">
                        <col name="sender" sortable max-width="200">
                        <col name="messageStripped" max-width="400">

                        <!-- Define the column headings. -->
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>Date</th>
                            <th>Sender</th>
                            <th>Message</th>
                        </tr>
                        </thead>
                    </table>
                </vaadin-grid>
                    <div id="templatewrapper" style="display: none;">
                        <template is="dom-bind">
                            <style>.userdata {-webkit-flex: 1; flex: 1; max-height: 20px;}
                            .usercolumn { margin-right:10px; display: -webkit-flex; display: flex; -webkit-flex-direction: column; flex-direction: column; }</style>
                            <div class="userdetails" style="display: -webkit-flex; display: flex; height: 170px;">
                                <div class="usercolumn">
                                    <img src="${pageContext.request.contextPath}/image/{{msg.sender}}/avatar.jpg" style="height: 100px; width: 100px;">
                                    <p>&nbsp;</p>


                                    <p><a href="${pageContext.request.contextPath}/messages/delete/{{msg.id}}">Delete message</a></p>
                                </div>
                                <div class="usercolumn">
                                    <small>Firstname</small>
                                    <div class="userdata">{{msg.firstName}}</div>
                                    <small>Lastname</small>
                                    <div class="userdata">{{msg.lastName}}</div>
                                    <small>Username</small>
                                    <div class="userdata">{{msg.sender}}</div>
                                    <small>Date</small>
                                    <div class="userdata">{{msg.created}}</div>
                                </div>
                                <div style="width:100% !important">
                                    <small>Message</small>
                                    <div id="msg" style="width:100% !important;white-space: normal; max-height: 50px !important; overflow: visible !important;">{{msg.message}}</div>
                                    <!--<div style="display: -webkit-flex; display: flex;">
                                        <button class="demo-button">MESSAGE</button>
                                        <button class="demo-button whitebutton">EDIT</button>
                                    </div>-->
                                </div>
                            </div>
                        </template>
                    </div>


                    <style type="text/css">
                    /*tr.vaadin-grid-row {
                        cursor: pointer;
                    }
                    .vaadin-grid-0 {
                        white-space: normal;
                    }*/

                    .vaadin-grid-footer {
                        display:none !important;
                    }
                    .userdetailswrapper {
                        padding: 10px;
                        width: 100%;
                        box-sizing: border-box;
                    }

                    .userdetails {
                        padding: 10px;
                        width: 100%;
                        box-sizing: border-box;
                        -webkit-box-shadow: 0px 0px 15px -3px rgba(0,0,0,0.75);
                        -moz-box-shadow: 0px 0px 15px -3px rgba(0,0,0,0.75);
                        box-shadow: 0px 0px 15px -3px rgba(0,0,0,0.75);
                    }

                    .userdetails img {
                        margin-right: 10px;
                    }

                    .userdetails small {
                        color: #AAA;
                        font-weight: 100;
                        padding-bottom: 2px;
                    }

                    .userdetails div {
                        /*text-transform: capitalize;*/
                    }


                </style>
                </c:if>
            </div>
<p>&nbsp;</p>
            <div class="row column" style="text-align:center; max-width: 100%">
                <p><small>All rights reserved to Java2 development team &copy; 2016</small></p>
            </div>

        </div>
    </div>
</div>

<jsp:include page="_footer.jsp"></jsp:include>

<script>
    $( document ).ready(function() {
        if ("" != "${messages[1]}") {
            $("#alertMessage").html("${messages[1]}");
            $("#alertMessage").show();
        }

        if ("" != "${messages[0]}") {
            $("#alertMessage2").html("${messages[0]}");
            $("#alertMessage2").show();
        }


    });
</script>
<c:if test="${not empty data}">
<script>
    var templateWrapper = document.getElementById('templatewrapper');
    var template = templateWrapper.querySelector('template');
</script>
<script>


    function getUserDetails(message) {
        template.msg = message;
        //template.msg.message = $.parseHTML(template.msg.message);
        //alert(template.msg.id);
        //alert(templateWrapper.querySelector('.userdetails').outerHTML);
        return templateWrapper.querySelector('.userdetails').outerHTML;
    }
</script>
<script>
    // The Web Components polyfill introduces a custom event we can
    // use to determine when the custom elements are ready to be used.
    window.addEventListener("WebComponentsReady", function () {

        // Reference to the grid element.
        var grid = document.querySelector("vaadin-grid");
        var regex = /(<([^>]+)>)/ig
        grid.items = [
            <c:forEach items="${data}" var="message" >
            { "id": "${message.id}", "created": "${fn:substring(message.created, 0, 16)}", "sender": "${message.sender}", "message": `${message.text}`, "messageStripped": `${message.text}`.replace(regex, "") },
            </c:forEach>
        ];
        grid.rowDetailsGenerator = function(rowIndex) {
            var elem = document.createElement('div');
            elem.setAttribute('class', 'userdetailswrapper');
            grid.getItem(rowIndex, function(error, item) {
                if (!error) {
                    var details = $.parseHTML($('<textarea/>').html(getUserDetails(item)).html())[0].textContent;
                    elem.innerHTML = details;
                }
            });
            return elem;
        };
        var detailsOpenIndex = -1;

        grid.addEventListener('selected-items-changed', function() {
            grid.setRowDetailsVisible(detailsOpenIndex, false);
            var selected = grid.selection.selected();
            if (selected.length == 1) {
                grid.setRowDetailsVisible(selected[0], true);
                detailsOpenIndex = selected[0];
            }
        });

        grid.rowClassGenerator = function(row) {

            var rowIndex = row.index;
            row.element.setAttribute("id",grid.items[rowIndex].id);

            row.element.onclick = function() {
                //location.href = "${pageContext.request.contextPath}/messages/" + grid.items[rowIndex].id;
                /*if (grid.selection.selected().indexOf(rowIndex) === -1) {
                 grid.selection.select(rowIndex);
                 } else {
                 grid.selection.deselect(rowIndex);
                 }*/
                //alert(row.element.cells[1].innerText + " " + grid.items[rowIndex].id);
            };
            return "";

        }


    });

</script>
</c:if>
</body>
</html>