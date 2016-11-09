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
import java.io.IOException;
import java.sql.SQLException;

import static lv.javaguru.java2.domain.User.isEmpty;
import static lv.javaguru.java2.domain.UserBuilder.createUser;

/**
 * Created by Pavel on 06.11.2016..
 */
@WebServlet(urlPatterns = { "/doAddUser" })
public class DoAddUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAOObj = new UserDAOImpl();

    public DoAddUserServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        String password_repeat = (String) request.getParameter("password_repeat");

        String date_of_birth = (String) request.getParameter("date_of_birth");
        String firstName = (String) request.getParameter("firstName");
        String lastName = (String) request.getParameter("lastName");
        String city = (String) request.getParameter("city");
        String country = (String) request.getParameter("country");
        String sex = (String) request.getParameter("sex");
        String looking_for = (String) request.getParameter("looking_for");
        String age_fromStr = request.getParameter("age_from");
        String age_toStr = request.getParameter("age_to");
        String about = (String) request.getParameter("about");


        int age_from;
        int age_to;

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


        User user = createUser()
                .withUsername(username)
                .withPassword(password)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withDate_of_birth(date_of_birth)
                .withCity(city)
                .withCountry(country)
                .withSex(sex)
                .withLooking_for(looking_for)
                .withAge_from(age_from)
                .withAge_to(age_to)
                .withAbout(about).build();



        String errorString = null, successString;

        if (!password.equals(password_repeat))
            errorString = "Passwords not match!";

        if (isEmpty(username) ||
            isEmpty(password) ||
            isEmpty(password_repeat) ||
            isEmpty(date_of_birth) ||
            isEmpty(firstName) ||
            isEmpty(lastName) ||
            isEmpty(sex))
        {
            errorString = "Fill all mandatory fields!";
        }


        if (errorString == null) {
            try {
                userDAOObj.create(user);
            } catch (DBException e) {
                e.printStackTrace();
                if ( e.getMessage().toLowerCase().contains("duplicate entry") ) {
                    errorString = "Record already exists!";
                }
                else
                    errorString = e.getMessage();
            }
        }

        // Store information to request attribute, before forward to views.


        // If error, forward to Edit page.
        if (errorString != null) {
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/addUserView.jsp");
            dispatcher.forward(request, response);
        }

        // If everything nice.
        // Redirect to login page or display success message.
        else {
            successString = "User successfully created!<br> Now you can <a href='" + request.getContextPath() + "/login'>Login</a>";
            request.setAttribute("successString", successString);


            //response.sendRedirect(request.getContextPath() + "/login");
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/addUserView.jsp");
            dispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}