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
        if(typeof Muse == "undefined") window.Muse = {}; window.Muse.assets = {"required":["jquery-1.8.3.min.js", "museutils.js", "museconfig.js", "jquery.watch.js", "require.js", "index.css"], "outOfDate":[]};
    </script>

    <title>index</title>
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="media/css/site_global.css?crc=338158355"/>
    <link rel="stylesheet" type="text/css" href="media/css/index.css?crc=3789467964" id="pagesheet"/>
    <!-- Other scripts -->
    <script type="text/javascript">
        var __adobewebfontsappname__ = "muse";
    </script>
    <!-- JS includes -->
    <script type="text/javascript">
        document.write('\x3Cscript src="' + (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//webfonts.creativecloud.com/dosis:n3:default.js" type="text/javascript">\x3C/script>');
    </script>
</head>
<body>
<form name="loginForm" method="POST" action="doLogin">

    <div class="clearfix borderbox" id="page"><!-- column -->
        <div class="clearfix colelem" id="pu1440-4"><!-- group -->
            <div class="clearfix grpelem" id="u1440-4"><!-- content -->
                <p>Username:</p>
            </div>
            <div class="grpelem" id="u1441"><input type="text" name="username" value= "${user.username}" /></div>
        </div>
        <div class="clearfix colelem" id="pu1446-4"><!-- group -->
            <div class="clearfix grpelem" id="u1446-4"><!-- content -->
                <p>Password:</p>
            </div>
            <div class="grpelem" id="u1447"><input type="text" name="password" value= "${user.password}" /></div>
        </div>
        <div class="clearfix colelem" id="pu1434-4"><!-- group -->
            <a class="nonblock nontext transition clearfix grpelem" id="u1434-4" onclick="loginForm.submit()" style="cursor: pointer"><!-- content --><p>Login</p></a>
            <a class="nonblock nontext transition clearfix grpelem" id="u1437-4" href="registration-page.html"><!-- content --><p>Register</p></a>

        </div>
        <div class="clearfix colelem" id="pu1434-4"><!-- group -->
            <a style="left:30%" class="nonblock nontext transition clearfix grpelem" id="u1434-4" href="${pageContext.request.contextPath}"><!-- content --><p>Cancel</p></a>
        </div>

        <div class="clearfix colelem" id="u1618-4"><!-- content -->
            <p>${errorString}</p>
        </div>
        <div class="verticalspacer" data-offset-top="382" data-content-above-spacer="382" data-content-below-spacer="118"></div>
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
        Muse.Utils.fullPage('#page');/* 100% height page */
        Muse.Utils.showWidgetsWhenReady();/* body */
        Muse.Utils.transformMarkupToFixBrowserProblems();/* body */
    }catch(b){if(b&&"function"==typeof b.notify?b.notify():Muse.Assert.fail("Error calling selector function: "+b),false)throw b;}})})};

</script>
<!-- RequireJS script -->
<script src="media/scripts/require.js?crc=4108833657" type="text/javascript" async data-main="media/scripts/museconfig.js?crc=4242129463" onload="if (requirejs) requirejs.onError = function(requireType, requireModule) { if (requireType && requireType.toString && requireType.toString().indexOf && 0 <= requireType.toString().indexOf('#scripterror')) window.Muse.assets.check(); }" onerror="window.Muse.assets.check();"></script>
</body>
</html>
