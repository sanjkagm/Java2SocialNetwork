package lv.javaguru.java2.servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

@WebServlet(urlPatterns = { "/doLogin" })
public class DoLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DoLoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //String rememberMeStr = request.getParameter("rememberMe");
        //boolean remember= "Y".equals(rememberMeStr);


        User user = null;
        boolean hasError = false;
        String errorString = null;

        if (username == null || password == null
                || username.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {

            try {
                UserDAOImpl userDAOObj = new UserDAOImpl();
                user = userDAOObj.getById(1L);

                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            } catch (Exception e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }

        // If error, forward to /WEB-INF/views/login.jsp
        if (hasError) {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);


            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);


            // Forward to /WEB-INF/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/loginView.jsp");

            dispatcher.forward(request, response);
        }

        // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
            /*HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);

            // If user checked "Remember me".
            if(remember)  {
                MyUtils.storeUserCookie(response,user);
            }

            // Else delete cookie.
            else  {
                MyUtils.deleteUserCookie(response);
            }*/

            // Redirect to userInfo page.
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}