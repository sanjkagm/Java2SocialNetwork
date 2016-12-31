package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Pavel on 22.11.2016..
 */
@Component
public class MainService extends Utils{

    @Autowired
    private UserDAO userDAO;

    public List<User> getFriends (Long myId) {

        List<User> usersFound = userDAO.getFriends(myId);

        return usersFound;
    }
}
