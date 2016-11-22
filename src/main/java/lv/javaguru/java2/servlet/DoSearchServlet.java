package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.SearchService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 19.11.2016..
 */
@WebServlet(urlPatterns = { "/doSearch" })
public class DoSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;



    public DoSearchServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SearchService searchService = new SearchService();
        User userInSession = searchService.checkIfUserLoggedIn(request);

        if (userInSession == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Store info in request attribute
        request.setAttribute("user", userInSession);

        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String looking_for = request.getParameter("looking_for");
        String age_fromStr = request.getParameter("age_from");
        String age_toStr = request.getParameter("age_to");

        // Preset data for search form
        User searchData = searchService.createUserByBuilder("0","username","password","date_of_birth","firstName","lastName","sex",city,country,looking_for,age_fromStr,age_toStr,"about");
        request.setAttribute("searchData", searchData);


        // Found users
        List<User> usersFound = searchService.searchUsers(city, country, looking_for, age_fromStr,age_toStr, userInSession.getUserId());
        if (usersFound.size() == 0)
            request.setAttribute("errorString", "<div data-alert class=\"alert-box\">\n" +
                    "                    No matches.\n" +
                    "                </div>");

        List<User> onlineUsers = (List<User>) request.getServletContext().getAttribute("onlineUsers");
        request.setAttribute("online", onlineUsers);


        request.setAttribute("usersFound", usersFound);
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/searchView.jsp");
        dispatcher.forward(request, response);


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
