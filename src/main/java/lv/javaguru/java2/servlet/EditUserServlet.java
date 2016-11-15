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

/**
 * Created by Pavel on 07.11.2016..
 */
@WebServlet(urlPatterns = { "/editUser" })
public class EditUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditUserServlet() {
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

        // Store info in request attribute
        request.setAttribute("user", userInSession);
        request.setAttribute("user_in_edit", userInSession);



        // Logined, forward to /editUserView.jsp
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/editUserView.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}