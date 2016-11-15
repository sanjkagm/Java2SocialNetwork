package lv.javaguru.java2.service;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;


import static lv.javaguru.java2.domain.UserBuilder.createUser;

/**
 * Created by Pavel on 15.11.2016..
 */
public class EditUserService extends Utils {

    public String updatePassword(String password,String password_repeat,String userIdStr) {

        String errorString = null;
        if (!password.equals(password_repeat))
            errorString = "Passwords not match!";
        if (isEmpty(password))
            errorString = "Password cannot be empty!";

        if (errorString == null) {
            try {
                UserDAO userDAOObj = new UserDAOImpl();
                User user = createUser().withUserId( stringToLong(userIdStr) ).withPassword( password ).build();
                userDAOObj.updatePassword(user);
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
                                           String date_of_birth)
    {

        String errorString = null;

        if (isEmpty(firstName) ||
                isEmpty(lastName) ||
                isEmpty(looking_for)) {
            errorString = "Fill all mandatory fields!";
        }

        if (errorString == null) {
            try {
                UserDAO userDAOObj = new UserDAOImpl();
                User user = createUser().withUserId(stringToLong(userIdStr)).withUsername(username).withFirstName(firstName).withLastName(lastName).withCity(city).withCountry(country).withLooking_for(looking_for).withAge_from(stringToInteger(age_fromStr)).withAge_to(stringToInteger(age_toStr)).withAbout(about).withSex(sex).withDate_of_birth(date_of_birth).build();
                userDAOObj.update(user);
                //System.out.println(username);
            } catch (DBException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }

        return errorString;
    }
}
