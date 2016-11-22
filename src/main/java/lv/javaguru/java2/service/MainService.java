package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

import java.util.List;

/**
 * Created by Pavel on 22.11.2016..
 */
public class MainService extends Utils{

    public List<User> getFriends (Long myId) {

        UserDAO userDAOObj = new UserDAOImpl();
        List<User> usersFound = userDAOObj.getFriends(myId);

        return usersFound;
    }
}
