package lv.javaguru.java2.servlet;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.MainService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = { "/main" })
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MainServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check User has logged on
        MainService mainService = new MainService();
        User userInSession = mainService.checkIfUserLoggedIn(request);
        // Not logged in
        if (userInSession == null) {
            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        // Store info in request attribute
        request.setAttribute("user", userInSession);

        // Found users
        List<User> usersFound = mainService.getFriends(userInSession.getUserId());
        if (usersFound.size() == 0)
            request.setAttribute("errorString", "noFriends");


        request.setAttribute("usersFound", usersFound);


        // Temp var storing online users
        List<User> onlineUsersTemp = new ArrayList<>((List<User>) request.getServletContext().getAttribute("onlineUsers"));
        onlineUsersTemp.retainAll(usersFound);
        request.setAttribute("online", onlineUsersTemp);

        // Logined, forward to /mainView.jsp
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/mainView.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}