package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.domain.User;

/**
 * Created by Pavel on 21.11.2016..
 */
public class UserService extends Utils{

    public User getUserById (String userId) {

        UserDAO userDAOObj = new UserDAOImpl();
        User user = userDAOObj.getById(stringToLong(userId));
        return user;

    }

    public Boolean checkUserFriend (Long myId, String userId) {
        UserDAO userDAOObj = new UserDAOImpl();
        return userDAOObj.checkUserFriend(myId,stringToLong(userId));
    }

    public Boolean checkUserPending (Long myId, String userId) {
        UserDAO userDAOObj = new UserDAOImpl();
        return userDAOObj.checkUserPending(myId,stringToLong(userId));
    }
}
