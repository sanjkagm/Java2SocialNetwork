<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="nojs html css_verticalspacer" lang="en-US">
<head>

    <meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
    <meta name="generator" content="2015.2.1.352"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>



    <title>my-page</title>
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="media/css/site_global.css?crc=338158355"/>
    <link rel="stylesheet" type="text/css" href="media/css/master_a-master.css?crc=3867736355"/>
    <link rel="stylesheet" type="text/css" href="media/css/my-page.css?crc=3948355035" id="pagesheet"/>
    <!-- IE-only CSS -->
    <!--[if lt IE 9]>
    <link rel="stylesheet" type="text/css" href="media/css/iefonts_my-page.css?crc=4138974196"/>
    <![endif]-->
    <!-- Other scripts -->
    <script type="text/javascript">
        var __adobewebfontsappname__ = "muse";
    </script>
    <!-- JS includes -->
    <script type="text/javascript">
        document.write('\x3Cscript src="' + (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//webfonts.creativecloud.com/dosis:n3,n4:default.js" type="text/javascript">\x3C/script>');
    </script>
</head>
<body>

<div class="clearfix borderbox" id="page"><!-- column -->
    <div class="clearfix colelem" id="pu104"><!-- group -->
        <div class="browser_width grpelem" id="u104-bw">
            <div class="gradient" id="u104"><!-- column -->
                <div class="clearfix" id="u104_align_to_page">
                    <div class="clearfix colelem" id="pu1797-4"><!-- group -->
                        <div class="clearfix grpelem" id="u1797-4"><!-- content -->
                            <p><a href="${pageContext.request.contextPath}/editUser">${user.firstName} ${user.lastName}, Age</a></p>
                        </div>
                        <a class="nonblock nontext gradient rounded-corners clearfix grpelem" id="u971" href="${pageContext.request.contextPath}/logout"><!-- group --><div class="clearfix grpelem" id="u974-4"><!-- content --><p>LogOut</p></div></a>
                        <img class="grpelem" id="u972" alt="" width="30" height="30" src="media/images/48-u972.png?crc=4006277573"/><!-- rasterized frame -->
                    </div>
                    <div class="clearfix colelem" id="u1799-4"><!-- content -->
                        <p>${user.country}, ${user.city}</p>
                    </div>
                    <div class="clearfix colelem" id="u1798-4"><!-- content -->
                        <p>Online</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="browser_width grpelem" id="u107-bw">
            <div class="gradient" id="u107"><!-- group -->
                <div class="clearfix" id="u107_align_to_page">
                    <a class="nonblock nontext transition clearfix grpelem" id="u989-4" href="friends-page.html"><!-- content --><p>Friends</p></a>
                    <div class="transition clearfix grpelem" id="u1001-4"><!-- content -->
                        <p>Massages</p>
                    </div>
                    <div class="clearfix grpelem" id="pu966-4"><!-- group -->
                        <a class="nonblock nontext transition clearfix grpelem" id="u966-4" href="${pageContext.request.contextPath}/"><!-- content --><p>Main</p></a>
                        <a class="nonblock nontext transition clearfix grpelem" id="u1089-4" href="search-page.html"><!-- content --><p>Search</p></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="clearfix grpelem" id="u110"><!-- group -->
            <div class="clearfix grpelem" id="u1086-6"><!-- content -->
                <p>My</p>
                <p>Photo</p>
            </div>
            <div class="grpelem" id="u1735"><!-- content -->
                <div class="fluid_height_spacer"></div>
            </div>
        </div>
    </div>
    <div class="clearfix colelem" id="u997-4"><!-- content -->
        <p>Profile</p>
    </div>
    <form name="editform" method="POST" action="doEditUser">
    <div class="rgba-background clearfix colelem" id="u1344"><!-- column -->
        <div class="clearfix colelem" id="u1766-4"><!-- content -->
            <p>${errorString}<span style="color: #3eff13">${successString}</span></p>
        </div>
        <input type="hidden" name="username" value="${user.username}" />
        <input type="hidden" name="UserID" value="${user.userId}" />
        Username: ${user.username}
        <div class="clearfix colelem" id="pu1345-4"><!-- group -->
            <div class="clearfix grpelem" id="u1345-4"><!-- content -->
                <p>Name:</p>
            </div>
            <div class="grpelem" id="u1353"><input type="text" name="firstName" value="${user_in_edit.firstName}" /></div>
            <div class="clearfix grpelem" id="u1346-4"><!-- content -->
                <p>Last Name:</p>
            </div>
            <div class="grpelem" id="u1354"><input type="text" name="lastName" value="${user_in_edit.lastName}" /></div>
        </div>
        <div class="clearfix colelem" id="pu1347-4"><!-- group -->
            <div class="clearfix grpelem" id="u1347-4"><!-- content -->
                <p>City:</p>
            </div>
            <div class="grpelem" id="u1355"><input type="text" name="city" value="${user_in_edit.city}" /></div>
            <div class="clearfix grpelem" id="u1348-4"><!-- content -->
                <p>Country:</p>
            </div>
            <div class="grpelem" id="u1356"><input type="text" name="country" value="${user_in_edit.country}" /></div>
        </div>
        <div class="clearfix colelem" id="pu1349-4"><!-- group -->
            <div class="clearfix grpelem" id="u1349-4"><!-- content -->
                <p>Looking for:</p>
            </div>
            <div class="grpelem" id="u1357">
                <select name="looking_for" id="looking_for" style="width: 100%;height: 100%;">
                    <option value="F">Female</option>
                    <option value="M">Male</option>
                </select>
            </div>
        </div>
        <div class="clearfix colelem" id="pu1350-4"><!-- group -->
            <div class="clearfix grpelem" id="u1350-4"><!-- content -->
                <p>From:</p>
            </div>
            <div class="grpelem" id="u1358"><input id="age_from" type="text" name="age_from" value="${user_in_edit.age_from}" /></div>
            <div class="clearfix grpelem" id="u1351-4"><!-- content -->
                <p>To:</p>
            </div>
            <div class="grpelem" id="u1359"><input id="age_to" type="text" name="age_to" value="${user_in_edit.age_to}" /></div>
        </div>
        <div class="clearfix colelem" id="pu1352-4"><!-- group -->
            <div class="clearfix grpelem" id="u1352-4"><!-- content -->
                <p>About me:</p>
            </div>
            <div class="grpelem" id="u1360"><textarea name="about">${user_in_edit.about}</textarea></div>
            <div class="transition clearfix grpelem" id="u1785-4"><!-- content -->
                <p onclick="editform.submit()" style="cursor: pointer">Save Changes</p></a>
            </div>
        </div>
    </div>
    </form>

    <div class="clearfix colelem" id="u1491-4"><!-- content -->
        <p>Password change</p>
    </div>

    <form name="editPasswordForm" method="POST" action="doEditUser">

        <input type="hidden" name="passwordForm" value="true" />
        <input type="hidden" name="username" value="${user.username}" />
        <input type="hidden" name="UserID" value="${user.userId}" />

    <div class="rgba-background clearfix colelem" id="u1470"><!-- group -->



        <div class="clearfix grpelem" id="u1473-4"><!-- content -->
            <p>Password:</p>
        </div>
        <div class="grpelem" id="u1475"><input type="text" name="password" value="" /></div>
        <div class="clearfix grpelem" id="u1474-4"><!-- content -->
            <p>Repeat Password:</p>
        </div>
        <div class="grpelem" id="u1476"><input type="text" name="password_repeat" value="" /></div>
        <div class="transition clearfix grpelem" id="u1485-4"><!-- content -->
            <p onclick="editPasswordForm.submit()" style="cursor: pointer">Change</p>
        </div>
    </div>
    </form>

    <div class="verticalspacer" data-offset-top="1128" data-content-above-spacer="1127" data-content-below-spacer="84"></div>
    <div class="clearfix colelem" id="u123-4"><!-- content -->
        <p>Copyright 2016 All rights reserved to Java2 development team</p>
    </div>
</div>
</form>
<!-- Other scripts -->

<script src="media/scripts/jquery-1.8.3.min.js" type="text/javascript"></script>
<script>
    $( document ).ready(function() {
        $("#sex").val("${user_in_edit.sex}");
        $("#looking_for").val("${user_in_edit.looking_for}");
        if ($("#age_from").val() == 0 )
            $("#age_from").val("")
        if ($("#age_to").val() == 0 )
            $("#age_to").val("")
    });
</script>
</body>
</html>
