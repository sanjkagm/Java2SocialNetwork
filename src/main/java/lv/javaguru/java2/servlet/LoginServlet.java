package lv.javaguru.java2.servlet;


import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(urlPatterns = { "/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LoginService loginService = new LoginService();
        User userInSession = loginService.checkIfUserLoggedIn(request);

        if (userInSession != null) {
            response.sendRedirect("/main");
            return;
        }

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/loginView.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
