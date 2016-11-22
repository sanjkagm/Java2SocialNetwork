package lv.javaguru.java2.servlet;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.EditUserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static lv.javaguru.java2.service.Utils.isEmpty;


/**
 * Created by Pavel on 07.11.2016..
 */
@WebServlet(urlPatterns = { "/doProfile" })
public class DoProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;



    public DoProfileServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {




        EditUserService editUserService = new EditUserService();
        User userInSession = editUserService.checkIfUserLoggedIn(request);

        if (userInSession == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String userIdStr = request.getParameter("UserID");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password_repeat = request.getParameter("password_repeat");

        String date_of_birth = userInSession.getDate_of_birth();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String city = request.getParameter("city");
        String country = request.getParameter("country");

        String sex = userInSession.getSex();

        String looking_for = request.getParameter("looking_for");
        String age_fromStr = request.getParameter("age_from");
        String age_toStr = request.getParameter("age_to");
        String about = request.getParameter("about");

        String passwordForm = request.getParameter("passwordForm");




        String errorString, successString;
        User user = editUserService.createUserByBuilder(userIdStr,username,password,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);


        if (!isEmpty(passwordForm)) {   // Password change form handler

            errorString = editUserService.updatePassword(password, password_repeat,userIdStr);

            request.setAttribute("user", userInSession);
            request.setAttribute("user_in_edit", userInSession);

            successString = "Password successfully updated!";

        } else {    // User info form handler

            errorString = editUserService.updateUserInfo(userIdStr,username,firstName,lastName,city,country,looking_for,age_fromStr,age_toStr,about,sex,date_of_birth);

            request.setAttribute("user", userInSession);
            request.setAttribute("user_in_edit", user);

            successString = "User successfully updated!";
        }

        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);

        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/profileView.jsp");
            dispatcher.forward(request, response);
        }

        // If everything nice.
        // Redirect.
        else {
            if (isEmpty(passwordForm)) {
                request.setAttribute("user", user);
                editUserService.storeLoggedUserInSession(request,user);
            }
            request.setAttribute("successString", successString);
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/profileView.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}