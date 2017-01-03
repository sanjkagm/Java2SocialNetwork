package lv.javaguru.java2.servlet;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.EditUserService;
import lv.javaguru.java2.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Pavel on 07.11.2016..
 */
@WebServlet(urlPatterns = { "/doProfile" })
public class DoProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;



    public DoProfileServlet() {
        super();
    }

    @Autowired
    private EditUserService editUserService;
    @Autowired
    private Utils utils;

    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User userInSession = utils.checkIfUserLoggedIn(request);

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

        String currentPassword = userInSession.getPassword();




        String errorString, successString;
        User user = utils.createUserByBuilder(userIdStr,username,currentPassword,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);


        if (!utils.isEmpty(passwordForm)) {   // Password change form handler

            errorString = editUserService.updatePassword(password, password_repeat,userIdStr);


            request.setAttribute("user", userInSession);
            request.setAttribute("user_in_edit", userInSession);

            successString = "Password successfully updated!";

        } else {    // User info form handler

            errorString = editUserService.updateUserInfo(userIdStr,username,firstName,lastName,city,country,looking_for,age_fromStr,age_toStr,about,sex,date_of_birth, currentPassword);

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
            if (utils.isEmpty(passwordForm)) {
                request.setAttribute("user", user);
                utils.storeLoggedUserInSession(request,user);
            } else { // if passwordForm is NOT EMPTY
                User userTmp = userInSession;
                userTmp.setPassword(password);
                utils.storeLoggedUserInSession(request,userTmp);
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