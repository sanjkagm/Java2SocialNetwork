package lv.javaguru.java2.service;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.validators.Validators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import javax.validation.Validation;

/**
 * Created by Pavel on 03.01.2017..
 */

@Service
@Transactional
public class AddUserServiceImpl implements AddUserService{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Validators validators;

    @Autowired
    private Utils utils;


    public String validatePasswords(String password,String password_repeat)
    {
        String errorString = null;

        if (!password.equals(password_repeat))
            errorString = "Passwords not match!";

        return errorString;
    }



    public String register(User user)
    {
        String
                errorString = validators.validate(user, Validation.buildDefaultValidatorFactory().getValidator());

        if (errorString == null) {
            try {
                userDAO.create(user);
            } catch (DBException e) {
                if (e.getMessage().toLowerCase().contains("duplicate entry")) {
                    errorString = "User already exists!";
                } else
                    errorString = e.getMessage();
            }
        }
        return errorString;

    }
}
