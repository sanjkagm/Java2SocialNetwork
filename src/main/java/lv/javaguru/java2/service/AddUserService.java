package lv.javaguru.java2.service;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

import static lv.javaguru.java2.domain.User.isEmpty;
import static lv.javaguru.java2.domain.UserBuilder.createUser;

/**
 * Created by Pavel on 15.11.2016..
 */
public class AddUserService {

    public Integer stringToInteger (String ageStr) {
        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (Exception e) {
            age = 0;
        }
        return age;
    }

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

    public User createUserByBuilder (String username,
                                     String password,
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
        int age_from = stringToInteger(age_fromStr);
        int age_to = stringToInteger(age_toStr);

        return createUser()
                .withUsername(username)
                .withPassword(password)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withDate_of_birth(date_of_birth)
                .withCity(city)
                .withCountry(country)
                .withSex(sex)
                .withLooking_for(looking_for)
                .withAge_from(age_from)
                .withAge_to(age_to)
                .withAbout(about).build();

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
                UserDAO userDAOObj = new UserDAOImpl();
                User user = createUserByBuilder(username,password,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);
                userDAOObj.create(user);
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
