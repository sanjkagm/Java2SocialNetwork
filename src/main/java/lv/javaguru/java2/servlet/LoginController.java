package lv.javaguru.java2.servlet;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.mvc.MVCController;
import lv.javaguru.java2.mvc.MVCModel;
import lv.javaguru.java2.service.LoginService;
import lv.javaguru.java2.mvc.MVCFilter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Marlen on 11/27/2016.
 */

@WebServlet(urlPatterns = { "/login"})

public class LoginController implements MVCController {

    public MVCModel processGet(HttpServletRequest req) {
        String jspResult = "/loginView.jsp";
        User user = null;
        String[] messages = new String[2];

        LoginService loginService = new LoginService();
        User userInSession = loginService.checkIfUserLoggedIn(req);

       if (userInSession != null) {
           jspResult = "/main";

        }

        return new MVCModel(jspResult,user,messages);

    }



    public MVCModel processPost(HttpServletRequest req) {
        return processGet(req);
    }

}