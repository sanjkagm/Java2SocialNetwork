package lv.javaguru.java2.servlet;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static lv.javaguru.java2.domain.User.isEmpty;
import static lv.javaguru.java2.domain.UserBuilder.createUser;

/**
 * Created by Pavel on 07.11.2016..
 */
@WebServlet(urlPatterns = { "/doEditUser" })
public class DoEditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAOObj = new UserDAOImpl();

    public DoEditUserServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        // Check User has logged on
        UserDAOImpl userDAOObj = new UserDAOImpl();
        User loginedUser = User.getLoginedUser(session);
        // Not logged in
        if (loginedUser == null) {
            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }


        String userIdStr = (String) request.getParameter("UserID");
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        String password_repeat = (String) request.getParameter("password_repeat");

        //String date_of_birth = (String) request.getParameter("date_of_birth");
        String firstName = (String) request.getParameter("firstName");
        String lastName = (String) request.getParameter("lastName");
        String city = (String) request.getParameter("city");
        String country = (String) request.getParameter("country");
        //String sex = (String) request.getParameter("sex");
        String looking_for = (String) request.getParameter("looking_for");
        String age_fromStr = request.getParameter("age_from");
        String age_toStr = request.getParameter("age_to");
        String about = (String) request.getParameter("about");

        String passwordForm = (String) request.getParameter("passwordForm");


        int age_from;
        int age_to;
        Long userId;

        try {
            age_from = Integer.parseInt(age_fromStr);
        } catch (Exception e) {
            age_from = 0;
        }
        try {
            age_to = Integer.parseInt(age_toStr);
        } catch (Exception e) {
            age_to = 0;
        }
        try {
            userId = Long.parseLong(userIdStr);
        } catch (Exception e) {
            userId = 0L;
        }





        String errorString = null, successString;



            User user = createUser()
                    .withUserId(userId)
                    .withUsername(username)
                    .withPassword(password)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withCity(city)
                    .withCountry(country)
                    .withLooking_for(looking_for)
                    .withAge_from(age_from)
                    .withAge_to(age_to)
                    .withAbout(about).build();


        if (!isEmpty(passwordForm)) {   // Password change form handler

            if (!password.equals(password_repeat))
                errorString = "Passwords not match!";

            if (isEmpty(password))
                errorString = "Password cannot be empty!";

            if (errorString == null) {
                try {
                    userDAOObj.updatePassword(user);
                } catch (DBException e) {
                    e.printStackTrace();
                    errorString = e.getMessage();
                }
            }

            request.setAttribute("user", User.getLoginedUser(session));
            request.setAttribute("user_in_edit", User.getLoginedUser(session));

            successString = "Password successfully updated!";

        } else {    // User info form handler

            if (isEmpty(firstName) ||
                    isEmpty(lastName) ||
                    isEmpty(looking_for)) {
                errorString = "Fill all mandatory fields!";
            }

            if (errorString == null) {
                try {
                    userDAOObj.update(user);
                } catch (DBException e) {
                    e.printStackTrace();
                    errorString = e.getMessage();
                }
            }

            request.setAttribute("user", User.getLoginedUser(session));
            request.setAttribute("user_in_edit", user);

            successString = "User successfully updated!";

        }

        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);



        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/editUserView.jsp");
            dispatcher.forward(request, response);
        }

        // If everything nice.
        // Redirect.
        else {
            if (isEmpty(passwordForm)) {
                request.setAttribute("user", user);
                User.storeLoginedUser(session, user);
            }

            request.setAttribute("successString", successString);

            //response.sendRedirect(request.getContextPath() + "/login");
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/editUserView.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}