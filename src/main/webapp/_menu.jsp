<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="row column">
    <div class="small secondary expanded button-group">
        <% if (request.getServletPath().contains("/user")) { %>
            <% if ((boolean)session.getAttribute("isFriend")) { %>
                <a id="friendLink" class="button" action="remove"><i class="fa fa-minus-circle" aria-hidden="true"></i> Remove friend</a>
            <% } else { %>
                <a id="friendLink" class="button" action="add"><i class="fa fa-plus-circle" aria-hidden="true"></i> Add friend</a>
            <% } %>
        <% } else { %>
            <a class="button">&nbsp;</a>
        <% } %>

        <a class="button">&nbsp;</a>

        <% if (request.getServletPath().contains("main")) { %>
            <a class="button" href="${pageContext.request.contextPath}/profile">Edit Profile</a>
        <% } else { %>
        <a class="button" href="${pageContext.request.contextPath}/">Main</a>
        <% } %>

        <a class="button" href="${pageContext.request.contextPath}/search">Search</a>

    </div>

    <div id="alertMessage" class="alert-box" data-alert="" style=display:none;"></div>
</div>