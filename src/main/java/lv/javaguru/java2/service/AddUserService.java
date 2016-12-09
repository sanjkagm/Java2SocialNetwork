package lv.javaguru.java2.service;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by Pavel on 15.11.2016..
 */
@Component
public class AddUserService extends Utils {

    @Autowired
    private UserDAO userDAO;

    public String validateInput(String username,
                                String password,
                                String password_repeat,
                                String date_of_birth,
                                String firstName,
                                String lastName,
                                String sex)
    {

        String errorString = null;

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
        return errorString;
    }



    public String register(String username,
                           String password,
                           String password_repeat,
                           String date_of_birth,
                           String firstName,
                           String lastName,
                           String sex,
                           String city,
                           String country,
                           String looking_for,
                           String age_fromStr,
                           String age_toStr,
                           String about)
    {
        String errorString = validateInput(username,password,password_repeat,date_of_birth,firstName,lastName,sex);

        if (errorString == null) {
            try {
               // UserDAO userDAOObj = new UserDAOImpl();
                User user = createUserByBuilder("0",username,password,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);
                userDAO.create(user);
            } catch (DBException e) {
                e.printStackTrace();
                if (e.getMessage().toLowerCase().contains("duplicate entry")) {
                    errorString = "User already exists!";
                } else
                    errorString = e.getMessage();
            }
        }
        return errorString;

    }


}
