package lv.javaguru.java2.servlet;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.FriendRequestService;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = { "/friend/*"})
public class FriendRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FriendRequestServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check User has logged on
        FriendRequestService friendService = new FriendRequestService();
        User userInSession = friendService.checkIfUserLoggedIn(request);
        // Not logged in
        if (userInSession == null) {
            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.setAttribute("user", userInSession);

        String userId = request.getParameter("user_id");
        String friendId = request.getParameter("friend_id");

        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
        String[] pathParts = pathInfo.split("/");
        String action = null;
        if (pathParts.length > 0) {
            action = pathParts[1]; //
        }
        // Check if 'action' is actually supplied to the request URI.
        if (action == null || (!action.equals("add") && !action.equals("remove")&& !action.equals("cancel") && !action.equals("accept")) ) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }



        boolean isFriend = false;
        if (action.equals("add")) {
            detectIntruder(friendService.stringToLong(userId),userInSession.getUserId(), response);

            isFriend = friendService.addFriend(userId,friendId);
            request.getSession().setAttribute("isFriend", isFriend);

        }
        else if (action.equals("remove") || action.equals("cancel")) {
            detectIntruder(friendService.stringToLong(userId),userInSession.getUserId(), response);

            isFriend = friendService.removeFriend(userId,friendId);
            request.getSession().setAttribute("isFriend", isFriend);

        }
        else if (action.equals("accept")) {
            if (pathParts.length <= 2) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
                return;
            }
            System.out.println(pathParts.length + " " + pathParts[2] + " " + pathParts[3]);
            request.getSession().setAttribute("successString", friendService.acceptFriendRequest(userInSession.getUserId(),pathParts[2]));
            response.sendRedirect("/main");
            return;

        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.append(""+isFriend+"");
        out.close();


        return;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public boolean detectIntruder (Long userId, Long myId, HttpServletResponse response) throws ServletException, IOException {
        if (!userId.equals(myId)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.

        }
        return false;
    }


}