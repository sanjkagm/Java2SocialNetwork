package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;


import java.util.List;

/**
 * Created by Pavel on 19.11.2016..
 */
public class SearchService extends Utils {

    public List<User> searchUsers (String city, String country, String looking_for, String age_fromStr, String age_toStr, Long myId) {

        UserDAO userDAOObj = new UserDAOImpl();
        List<User> usersFound = userDAOObj.search(city, country, looking_for, stringToInteger(age_fromStr), stringToInteger(age_toStr), myId);

        return usersFound;
    }
}
