package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Pavel on 14.11.2016..
 */
public class LoginService {

    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    public User checkIfUserLoggedIn(HttpServletRequest request){
        HttpSession session = request.getSession();
        User loginedUser = (User) session.getAttribute("loginedUser");
        return loginedUser;
    }

    public boolean validateUseLoginAndPassword(String username, String password) {
        if (username == null || password == null
                || username.length() == 0 || password.length() == 0) {
            return false;
        }
        return true;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        UserDAO userDAOObj = new UserDAOImpl();
        return userDAOObj.getByUsernameAndPassword(username, password);
    }

    public User presetUsernameAndPasswordForLoginForm(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    public void storeLoggedUserInSession(HttpServletRequest request, User loginedUser) {
        HttpSession session = request.getSession();
        // On the JSP can access ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }

    public void storeUserCookie(HttpServletResponse response, User user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUsername());

        // 1 day (Convert to seconds)
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }

    public String authenticate(String username, String password) {
        User user;
        //boolean hasError = false;
        String errorString = null;

        if (!validateUseLoginAndPassword(username, password)) {
            //hasError = true;
            errorString = "Required username and password!";
        } else {
            try {
                user = getUserByUsernameAndPassword(username, password);
                if (user == null) {
                    //hasError = true;
                    errorString = "User Name or password invalid";
                }
            } catch (Exception e) {
                e.printStackTrace();
                //hasError = true;
                errorString = e.getMessage();
            }
        }
        return errorString;

    }

}
