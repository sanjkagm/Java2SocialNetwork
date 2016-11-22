package lv.javaguru.java2.servlet;


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.LoginService;
import lv.javaguru.java2.service.Utils;

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

        LoginService loginService = new LoginService();
        String resultOfAuth = loginService.authenticate(username, password);



        // If error, forward to /loginView.jsp
        if (resultOfAuth != null) {
            User user = loginService.presetUsernameAndPasswordForLoginForm(username, password);

            request.setAttribute("errorString", resultOfAuth);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/loginView.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
            User user = loginService.getUserByUsernameAndPassword(username, password);

            loginService.storeLoggedUserInSession(request, user);
            loginService.storeUserCookie(response,user);
            response.sendRedirect(request.getContextPath() + "/main");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}