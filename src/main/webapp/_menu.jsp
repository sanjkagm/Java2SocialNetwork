<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="clearfix colelem" id="pu104"><!-- group -->
    <div class="browser_width grpelem" id="u104-bw">
        <div class="gradient" id="u104"><!-- column -->
            <div class="clearfix" id="u104_align_to_page">
                <div class="clearfix colelem" id="pu1806-4"><!-- group -->
                    <div class="clearfix grpelem" id="u1806-4"><!-- content -->
                        <p><a href="${pageContext.request.contextPath}/editUser">${user.firstName} ${user.lastName}, Age</a></p>
                    </div>
                    <a class="nonblock nontext gradient rounded-corners clearfix grpelem" id="u290" href="${pageContext.request.contextPath}/logout"><!-- group --><div class="clearfix grpelem" id="u293-4"><!-- content --><p>LogOut</p></div></a>
                    <img class="grpelem" id="u506" alt="" width="30" height="30" src="media/images/48-u506.png?crc=4006277573"/><!-- rasterized frame -->
                </div>
                <div class="clearfix colelem" id="u1808-4"><!-- content -->
                    <p>${user.country}, ${user.city}</p>
                </div>
                <div class="clearfix colelem" id="u1807-4"><!-- content -->
                    <p>Online</p>
                </div>
            </div>
        </div>
    </div>
    <div class="browser_width grpelem" id="u107-bw">
        <div class="gradient" id="u107"><!-- group -->
            <div class="clearfix" id="u107_align_to_page">
                <a class="nonblock nontext transition clearfix grpelem" id="u281-4" href="friends-page.html"><!-- content --><p>Friends</p></a>
                <div class="transition clearfix grpelem" id="u284-4"><!-- content -->
                    <p>Massages</p>
                </div>
                <a class="nonblock nontext transition clearfix grpelem" id="u287-4" href="search-page.html"><!-- content --><p>Search</p></a>
            </div>
        </div>
    </div>
    <div class="clearfix grpelem" id="u110" style="left:0; background-color: transparent"><!-- group -->

        <div class="clearfix grpelem" id="u1086-6" style="left:-40%;margin-top:0; ">
            <img src="${pageContext.request.contextPath}/image/${user.username}/avatar.jpg" id="avatarImg" style="max-width: 215px;">
        </div>
        <div class="clearfix grpelem" id="u1146-6"><div class="fluid_height_spacer"></div></div>

    </div>
</div>