package lv.javaguru.java2.servlet;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.MainService;
import lv.javaguru.java2.service.UserService;
import lv.javaguru.java2.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/user/*" })
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserServlet() {
        super();
    }

    @Autowired
    private UserService userService;
    @Autowired
    private Utils utils;
    @Autowired
    private MainService mainService;

    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check User has logged on
        User userInSession = utils.checkIfUserLoggedIn(request);
        // Not logged in
        if (userInSession == null) {
            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.setAttribute("user", userInSession);


        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
        String[] pathParts = pathInfo.split("/");
        String userID = null;
        if (pathParts.length > 0) {
            userID = pathParts[1]; //
        }
        // Check if userID is actually supplied to the request URI.
        if (userID == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // get list of : friends of a friend
        // and friends of  user in session   to compare them

      //  MainService friendsOfUserInSessionObj = new MainService ();
        List <User> friendsOfUserInSession = mainService.getFriends(userInSession.getUserId());

      //  MainService friendsOfAFriendObj = new MainService ();
        List<User>  friendsOfFriend = mainService.getFriends(Long.parseLong(userID));



        request.setAttribute("friendsOfUserInSession",friendsOfUserInSession);
        request.setAttribute("friendsOfFriend",friendsOfFriend);

        request.setAttribute("userService", userService);


        User user = userService.getUserById(userID);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        Boolean isFriend = userService.checkUserFriend(userInSession.getUserId(), userID);
        Boolean isPending = userService.checkUserPending(userInSession.getUserId(), userID);


        // Store info in request attribute
        request.setAttribute("userFound", user);
        request.setAttribute("isFriend",isFriend);
        request.getSession().setAttribute("isFriend", isFriend);
        request.getSession().setAttribute("isPending", isPending);

        // Logined, forward to /userView.jsp
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/userView.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}