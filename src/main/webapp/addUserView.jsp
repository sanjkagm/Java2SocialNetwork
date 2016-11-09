<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="nojs html css_verticalspacer" lang="en-US">
<head>

    <meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
    <meta name="generator" content="2015.2.1.352"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <script type="text/javascript">
        // Update the 'nojs'/'js' class on the html node
        document.documentElement.className = document.documentElement.className.replace(/\bnojs\b/g, 'js');

        // Check that all required assets are uploaded and up-to-date
        if(typeof Muse == "undefined") window.Muse = {}; window.Muse.assets = {"required":["jquery-1.8.3.min.js", "museutils.js", "museconfig.js", "jquery.watch.js", "require.js", "registration-page.css"], "outOfDate":[]};
    </script>

    <title>registration-page</title>
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="media/css/site_global.css?crc=338158355"/>
    <link rel="stylesheet" type="text/css" href="media/css/master_a-master.css?crc=3867736355"/>
    <link rel="stylesheet" type="text/css" href="media/css/registration-page.css?crc=4180579876" id="pagesheet"/>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <!-- IE-only CSS -->
    <!--[if lt IE 9]>
    <link rel="stylesheet" type="text/css" href="media/css/iefonts_registration-page.css?crc=4132994238"/>
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

<form name="registerform" method="POST" action="doAddUser">
<div class="clearfix borderbox" id="page"><!-- column -->
    <div class="clearfix colelem" id="pu104"><!-- group -->
        <div class="browser_width grpelem" id="u104-bw">
            <div id="u104"><!-- simple frame --></div>
        </div>

        <div class="browser_width grpelem" id="u107-bw">
            <div id="u107"><!-- group -->
                <div class="clearfix" id="u107_align_to_page">
                    <a class="nonblock nontext transition clearfix grpelem" id="u1113-4" style="cursor: pointer;" onclick="registerform.submit()"><!-- content --><p>Submit</p></a>
                    <a class="nonblock nontext transition clearfix grpelem" id="u1112-4" href="${pageContext.request.contextPath}/"><!-- content --><p>Back</p></a>
                </div>
            </div>
        </div>

        <div class="clearfix grpelem" id="u110"><!-- group -->
            <div class="clearfix grpelem" id="u1102-6"><!-- content -->
                <p>Upload</p>
                <p>Photo</p>
            </div>
            <div class="grpelem" id="u1763"><!-- content -->
                <div class="fluid_height_spacer"></div>
            </div>
        </div>
    </div>
    <div class="clearfix colelem" id="u1099-4"><!-- content -->
        <p>Profile</p>
    </div>
    <div class="rgba-background clearfix colelem" id="u1100"><!-- column -->
        <div class="clearfix colelem" id="u1766-4"><!-- content -->
            <p>${errorString}<span style="color: #3eff13">${successString}</span></p>
        </div>
        <div class="clearfix colelem" id="pu1464-4"><!-- group -->
            <div class="clearfix grpelem" id="u1464-4"><!-- content -->
                <p>*Username:</p>
            </div>
            <div class="grpelem" id="u1465"><input type="text" name="username" value="${user.username}" /></div>
            <div class="clearfix grpelem" id="u1788-4"><!-- content -->
                <p>*Date of birth:</p>
            </div>
            <div class="grpelem" id="u1789"><input type="text" id="datepicker" name="date_of_birth" readonly value="${user.date_of_birth}" placeholder="YYYY-MM-DD"/></div>
        </div>
        <div class="clearfix colelem" id="pu1452-4"><!-- group -->
            <div class="clearfix grpelem" id="u1452-4"><!-- content -->
                <p>*Password:</p>
            </div>
            <div class="grpelem" id="u1454"><input type="text" name="password" value="${user.password}" /></div>
            <div class="clearfix grpelem" id="u1453-4"><!-- content -->
                <p>*Repeat Password:</p>
            </div>
            <div class="grpelem" id="u1455"><input type="text" name="password_repeat" /></div>
        </div>
        <div class="clearfix colelem" id="pu1104-4"><!-- group -->
            <div class="clearfix grpelem" id="u1104-4"><!-- content -->
                <p>*Name:</p>
            </div>
            <div class="grpelem" id="u1263"><input type="text" name="firstName" value="${user.firstName}" /></div>
            <div class="clearfix grpelem" id="u1105-4"><!-- content -->
                <p>*Last Name:</p>
            </div>
            <div class="grpelem" id="u1269"><input type="text" name="lastName" value="${user.lastName}" /></div>
        </div>
        <div class="clearfix colelem" id="pu1107-4"><!-- group -->
            <div class="clearfix grpelem" id="u1107-4"><!-- content -->
                <p>City:</p>
            </div>
            <div class="grpelem" id="u1275"><input type="text" name="city" value="${user.city}" /></div>
            <div class="clearfix grpelem" id="u1116-4"><!-- content -->
                <p>Country:</p>
            </div>
            <div class="grpelem" id="u1278"><input type="text" name="country" value="${user.country}" /></div>
        </div>
        <div class="clearfix colelem" id="pu1609-4"><!-- group -->
            <div class="clearfix grpelem" id="u1609-4"><!-- content -->
                <p>*Sex:</p>
            </div>
            <div class="grpelem" id="u1610">
                <select name="sex" id="sex" style="width: 100%;height: 100%;">
                    <option value="M">Male</option>
                    <option value="F">Female</option>
                </select>
            </div>
            <div class="clearfix grpelem" id="u1117-4"><!-- content -->
                <p>Looking for:</p>
            </div>
            <div class="grpelem" id="u1281">
                <select name="looking_for" id="looking_for" style="width: 100%;height: 100%;">
                    <option value="F">Female</option>
                    <option value="M">Male</option>
                </select>
            </div>
        </div>
        <div class="clearfix colelem" id="pu1111-4"><!-- group -->
            <div class="clearfix grpelem" id="u1111-4"><!-- content -->
                <p>Age From:</p>
            </div>
            <div class="grpelem" id="u1284"><input id="age_from" type="text" name="age_from" value="${user.age_from}" /></div>
            <div class="clearfix grpelem" id="u1108-4"><!-- content -->
                <p>Age To:</p>
            </div>
            <div class="grpelem" id="u1287"><input id="age_to" type="text" name="age_to" value="${user.age_to}" /></div>
        </div>
        <div class="clearfix colelem" id="pu1106-4"><!-- group -->
            <div class="clearfix grpelem" id="u1106-4"><!-- content -->
                <p>About me:</p>
            </div>
            <div class="grpelem" id="u1290"><textarea name="about">${user.about}</textarea></div>
        </div>
        <div class="clearfix colelem" id="u1770-4"><!-- content -->
            <p>* - are mandatory fields!</p>
        </div>
    </div>
    <div class="verticalspacer" data-offset-top="860" data-content-above-spacer="860" data-content-below-spacer="84"></div>
    <div class="clearfix colelem" id="u123-4"><!-- content -->
        <p>Copyright 2016 All rights reserved to Java2 development team</p>
    </div>
</div>
</form>
<!-- Other scripts -->
<script type="text/javascript">
    window.Muse.assets.check=function(d){if(!window.Muse.assets.checked){window.Muse.assets.checked=!0;var b={},c=function(a,b){if(window.getComputedStyle){var c=window.getComputedStyle(a,null);return c&&c.getPropertyValue(b)||c&&c[b]||""}if(document.documentElement.currentStyle)return(c=a.currentStyle)&&c[b]||a.style&&a.style[b]||"";return""},a=function(a){if(a.match(/^rgb/))return a=a.replace(/\s+/g,"").match(/([\d\,]+)/gi)[0].split(","),(parseInt(a[0])<<16)+(parseInt(a[1])<<8)+parseInt(a[2]);if(a.match(/^\#/))return parseInt(a.substr(1),
            16);return 0},g=function(g){for(var f=document.getElementsByTagName("link"),i=0;i<f.length;i++)if("text/css"==f[i].type){var h=(f[i].href||"").match(/\/?css\/([\w\-]+\.css)\?crc=(\d+)/);if(!h||!h[1]||!h[2])break;b[h[1]]=h[2]}f=document.createElement("div");f.className="version";f.style.cssText="display:none; width:1px; height:1px;";document.getElementsByTagName("body")[0].appendChild(f);for(i=0;i<Muse.assets.required.length;){var h=Muse.assets.required[i],l=h.match(/([\w\-\.]+)\.(\w+)$/),k=l&&l[1]?
            l[1]:null,l=l&&l[2]?l[2]:null;switch(l.toLowerCase()){case "css":k=k.replace(/\W/gi,"_").replace(/^([^a-z])/gi,"_$1");f.className+=" "+k;k=a(c(f,"color"));l=a(c(f,"backgroundColor"));k!=0||l!=0?(Muse.assets.required.splice(i,1),"undefined"!=typeof b[h]&&(k!=b[h]>>>24||l!=(b[h]&16777215))&&Muse.assets.outOfDate.push(h)):i++;f.className="version";break;case "js":k.match(/^jquery-[\d\.]+/gi)&&d&&d().jquery=="1.8.3"?Muse.assets.required.splice(i,1):i++;break;default:throw Error("Unsupported file type: "+
            l);}}f.parentNode.removeChild(f);if(Muse.assets.outOfDate.length||Muse.assets.required.length)f="Some files on the server may be missing or incorrect. Clear browser cache and try again. If the problem persists please contact website author.",g&&Muse.assets.outOfDate.length&&(f+="\nOut of date: "+Muse.assets.outOfDate.join(",")),g&&Muse.assets.required.length&&(f+="\nMissing: "+Muse.assets.required.join(",")),alert(f)};location&&location.search&&location.search.match&&location.search.match(/muse_debug/gi)?setTimeout(function(){g(!0)},5E3):g()}};
    var muse_init=function(){require.config({baseUrl:"/media/"});require(["jquery","museutils","whatinput","jquery.watch"],function(d){var $ = d;$(document).ready(function(){try{
        window.Muse.assets.check($);/* body */
        Muse.Utils.transformMarkupToFixBrowserProblemsPreInit();/* body */
        Muse.Utils.prepHyperlinks(true);/* body */
        Muse.Utils.resizeHeight('.browser_width');/* resize height */
        Muse.Utils.requestAnimationFrame(function() { $('body').addClass('initialized'); });/* mark body as initialized */
        Muse.Utils.fullPage('#page');/* 100% height page */
        Muse.Utils.showWidgetsWhenReady();/* body */
        Muse.Utils.transformMarkupToFixBrowserProblems();/* body */
    }catch(b){if(b&&"function"==typeof b.notify?b.notify():Muse.Assert.fail("Error calling selector function: "+b),false)throw b;}})})};

</script>
<!-- RequireJS script -->
<script src="media/scripts/require.js?crc=4108833657" type="text/javascript" async data-main="media/scripts/museconfig.js?crc=4242129463" onload="if (requirejs) requirejs.onError = function(requireType, requireModule) { if (requireType && requireType.toString && requireType.toString().indexOf && 0 <= requireType.toString().indexOf('#scripterror')) window.Muse.assets.check(); }" onerror="window.Muse.assets.check();"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $( function() {
        $( "#datepicker" ).datepicker({
            dateFormat: "yy-mm-dd",
            changeMonth: true,
            changeYear: true
        });
    } );
</script>
<script>
    $( document ).ready(function() {
        if ("" != "${user.sex}")
            $("#sex").val("${user.sex}");
        if ("" != "${user.looking_for}")
            $("#looking_for").val("${user.looking_for}");

        if ($("#age_from").val() == 0 )
            $("#age_from").val("")
        if ($("#age_to").val() == 0 )
            $("#age_to").val("")
    });
</script>
</body>
</html>
