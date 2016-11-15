package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;



/**
 * Created by Pavel on 14.11.2016..
 */
public class LoginService extends Utils {

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
