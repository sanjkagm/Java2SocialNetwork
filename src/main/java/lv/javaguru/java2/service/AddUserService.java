package lv.javaguru.java2.service;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.validators.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


/**
 * Created by Pavel on 15.11.2016..
 */
@Service
public class AddUserService extends Utils {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Validators validators;

    public String validatePasswords(String password,String password_repeat)
    {
        String errorString = null;

        if (!password.equals(password_repeat))
            errorString = "Passwords not match!";

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


        User user = createUserByBuilder("0",username,password,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        String errorString = validators.validate(user, validator);

        if (errorString == null)
            errorString = validatePasswords(password,password_repeat);

        if (errorString == null) {
            try {
                userDAO.create(user);
            } catch (DBException e) {
                //e.printStackTrace();
                if (e.getMessage().toLowerCase().contains("duplicate entry")) {
                    errorString = "User already exists!";
                } else
                    errorString = e.getMessage();
            }
        }
        return errorString;

    }


}
