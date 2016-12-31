package lv.javaguru.java2.service;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import static lv.javaguru.java2.domain.UserBuilder.createUser;

/**
 * Created by Pavel on 15.11.2016..
 */
@Component
public class EditUserService extends Utils {

    @Autowired
    private UserDAO userDAO;

    public String updatePassword(String password,String password_repeat,String userIdStr) {

        String errorString = null;
        if (!password.equals(password_repeat))
            errorString = "Passwords not match!";
        if (isEmpty(password))
            errorString = "Password cannot be empty!";
        else if (password.length() < 5)
            errorString = "Password minimum length is 5.";


        if (errorString == null) {
            try {
                User user = createUser().withUserId( stringToLong(userIdStr) ).withPassword( password ).build();
                userDAO.updatePassword(user);
            } catch (DBException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
        return errorString;
    }

    public String updateUserInfo(String userIdStr,
                                           String username,
                                           String firstName,
                                           String lastName,
                                           String city,
                                           String country,
                                           String looking_for,
                                           String age_fromStr,
                                           String age_toStr,
                                           String about,
                                           String sex,
                                           String date_of_birth,
                                 String currentPassword)
    {

        String errorString = null;

        if (isEmpty(firstName) ||
                isEmpty(lastName) ||
                isEmpty(looking_for)) {
            errorString = "Fill all mandatory fields!";
        }

        if (errorString == null) {
            try {
                //User user = createUser().withUserId(stringToLong(userIdStr)).withUsername(username).withFirstName(firstName).withLastName(lastName).withCity(city).withCountry(country).withLooking_for(looking_for).withAge_from(stringToInteger(age_fromStr)).withAge_to(stringToInteger(age_toStr)).withAbout(about).withSex(sex).withDate_of_birth(date_of_birth).build();
                User user = createUserByBuilder(userIdStr, username, currentPassword, date_of_birth, firstName, lastName, sex, city, country, looking_for, age_fromStr, age_toStr, about);
                userDAO.update(user);
                //System.out.println(username);
            } catch (DBException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }

        return errorString;
    }
}
