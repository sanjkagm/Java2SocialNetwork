package lv.javaguru.java2.servlet;


import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.mvc.MVCController;
import lv.javaguru.java2.mvc.MVCModel;
import lv.javaguru.java2.service.AddUserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Pavel on 06.11.2016..
 */
//@WebServlet(urlPatterns = { "/doAddUser" })
public class DoAddUserController /*extends HttpServlet*/ implements MVCController {
    /*private static final long serialVersionUID = 1L;

    public DoAddUserController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username =           request.getParameter("username");
        String password =           request.getParameter("password");
        String password_repeat =    request.getParameter("password_repeat");
        String date_of_birth =      request.getParameter("date_of_birth");
        String firstName =          request.getParameter("firstName");
        String lastName =           request.getParameter("lastName");
        String city =               request.getParameter("city");
        String country =            request.getParameter("country");
        String sex =                request.getParameter("sex");
        String looking_for =        request.getParameter("looking_for");
        String age_fromStr =        request.getParameter("age_from");
        String age_toStr =          request.getParameter("age_to");
        String about =              request.getParameter("about");


        AddUserService addUserService = new AddUserService();
        String resultOfRegistration = addUserService.register(username,password,password_repeat,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);

        // If error, forward to Edit page with pre-entered data.
        if (resultOfRegistration != null) {
            User user = addUserService.createUserByBuilder("0",username,password,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);

            request.setAttribute("errorString", resultOfRegistration);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/addUserView.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // If everything nice.
        // Display success message.
        else {
            request.setAttribute("successString", "User successfully created! Now you can Login");
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/addUserView.jsp");
            dispatcher.forward(request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }*/

    @Override
    public MVCModel processGet(HttpServletRequest request) {
        return new MVCModel("/error.jsp", null, "Incorrect request");
    }

    @Override
    public MVCModel processPost(HttpServletRequest request) {
        String jspResult = "/addUserView.jsp";
        User user = null;
        String[] messages = new String[2];

            String username =           request.getParameter("username");
            String password =           request.getParameter("password");
            String password_repeat =    request.getParameter("password_repeat");
            String date_of_birth =      request.getParameter("date_of_birth");
            String firstName =          request.getParameter("firstName");
            String lastName =           request.getParameter("lastName");
            String city =               request.getParameter("city");
            String country =            request.getParameter("country");
            String sex =                request.getParameter("sex");
            String looking_for =        request.getParameter("looking_for");
            String age_fromStr =        request.getParameter("age_from");
            String age_toStr =          request.getParameter("age_to");
            String about =              request.getParameter("about");


            AddUserService addUserService = new AddUserService();
            String resultOfRegistration = addUserService.register(username,password,password_repeat,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);

            // If error, forward to Edit page with pre-entered data.
            if (resultOfRegistration != null) {
                user = addUserService.createUserByBuilder("0",username,password,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);
                messages[0] = resultOfRegistration;
                messages[1] = null;
            }
            // If everything nice.
            // Display success message.
            else {
                messages[0] = null;
                messages[1] = "User successfully created! Now you can Login";
            }


        return new MVCModel(jspResult,user,messages);
    }

}