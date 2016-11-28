package lv.javaguru.java2.servlet;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.mvc.MVCController;
import lv.javaguru.java2.mvc.MVCModel;
import lv.javaguru.java2.service.LoginService;
import lv.javaguru.java2.mvc.MVCFilter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Marlen on 11/27/2016.
 */
public class DoLoginController implements MVCController {


    @Override
    public MVCModel processPost(HttpServletRequest request) {
        String jspResult = "/addUserView.jsp";
        User user = null;
        String[] messages = new String[2];

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoginService loginService = new LoginService();
        String resultOfAuth = loginService.authenticate(username, password);


        if (resultOfAuth != null) {


                messages[0] = resultOfAuth;
                messages[1] = null;


        }
        else {
            user = loginService.getUserByUsernameAndPassword(username, password);

          //  loginService.storeLoggedUserInSession(request, user);
           // loginService.storeUserCookie(response,user);



            jspResult = "/main";
        }

        return new MVCModel(jspResult,user,messages);
    }


    @Override
    public MVCModel processGet(HttpServletRequest request) {
        return new MVCModel("/error.jsp", null, "Incorrect request");
    }
}
