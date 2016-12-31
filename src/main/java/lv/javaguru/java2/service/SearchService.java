package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * Created by Pavel on 19.11.2016..
 */
@Component
public class SearchService extends Utils {

    @Autowired
    private UserDAO userDAO;

    public List<User> searchUsers (String city, String country, String looking_for, String age_fromStr, String age_toStr, Long myId) {

        List<User> usersFound = userDAO.search(city, country, looking_for, stringToInteger(age_fromStr), stringToInteger(age_toStr), myId);

        return usersFound;
    }
}
