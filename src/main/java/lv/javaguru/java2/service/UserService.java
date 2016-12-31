package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Pavel on 21.11.2016..
 */
@Component
public class UserService extends Utils{

    @Autowired
    private UserDAO userDAO;

    public User getUserById (String userId) {
        User user = userDAO.getById(stringToLong(userId));
        return user;

    }

    public Boolean checkUserFriend (Long myId, String userId) {
        return userDAO.checkUserFriend(myId,stringToLong(userId));
    }

    public Boolean checkUserPending (Long myId, String userId) {
        return userDAO.checkUserPending(myId,stringToLong(userId));
    }
}
