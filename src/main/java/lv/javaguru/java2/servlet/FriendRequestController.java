package lv.javaguru.java2.servlet;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.FriendRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Controller
public class FriendRequestController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private boolean isFriend = false;

    @Autowired
    private FriendRequestService friendService;


    @RequestMapping(value = "friend/readmsg/{id}", method = {RequestMethod.POST})
    @ResponseBody
    public String processPostReadRequest(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
        User userInSession = friendService.checkIfUserLoggedIn(request);
        if (userInSession == null) {
            return "notLoggedIn";
        }
        return ""+ friendService.readMsg(id);

    }

    @RequestMapping(value = "friend/accept/{fromId}/{toId}/*", method = {RequestMethod.GET})
    @ResponseBody
    public String processGetAddRequest(@PathVariable("fromId") String fromId,@PathVariable("toId") String toId, HttpServletRequest request, HttpServletResponse response) throws Exception{
        User userInSession = friendService.checkIfUserLoggedIn(request);
        if (userInSession == null) {
            return "notLoggedIn";
        }
        request.getSession().setAttribute("successString", friendService.acceptFriendRequest(userInSession.getUserId(),fromId));
        response.sendRedirect("/main");
        return "";
    }

    @RequestMapping(value = {"friend/remove","friend/cancel"}, method = {RequestMethod.POST})
    @ResponseBody
    public String processPostRemoveRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
        User userInSession = friendService.checkIfUserLoggedIn(request);
        if (userInSession == null) {
            return "notLoggedIn";
        }
        String userId = request.getParameter("user_id");
        String friendId = request.getParameter("friend_id");

        detectIntruder(friendService.stringToLong(userId),userInSession.getUserId(), response);

        isFriend = friendService.removeFriend(userId,friendId);
        request.getSession().setAttribute("isFriend", isFriend);
        return "" + isFriend;

    }

    @RequestMapping(value = "friend/add", method = {RequestMethod.POST})
    @ResponseBody
    public String processPostAddRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User userInSession = friendService.checkIfUserLoggedIn(request);
        if (userInSession == null) {
            return "notLoggedIn";
        }
        request.setAttribute("user", userInSession);

        String userId = request.getParameter("user_id");
        String friendId = request.getParameter("friend_id");

        detectIntruder(friendService.stringToLong(userId), userInSession.getUserId(), response);

        request.getSession().setAttribute("isFriend", true);
        isFriend = true;

        if (friendService.addFriend(userId, friendId) == false) {
            return "already";
        }
        return "" + isFriend;
    }



    public boolean detectIntruder (Long userId, Long myId, HttpServletResponse response) throws ServletException, IOException {
        if (!userId.equals(myId)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
        }
        return false;
    }


}