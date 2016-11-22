package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;

/**
 * Created by Pavel on 21.11.2016..
 */
public class FriendRequestService extends Utils{

    public boolean addFriend (String myId,String userId) {
        UserDAO userDAOObj = new UserDAOImpl();
        userDAOObj.addFriend(stringToLong(myId),stringToLong(userId));
        return true;

    }

    public boolean removeFriend (String myId,String userId) {
        UserDAO userDAOObj = new UserDAOImpl();
        userDAOObj.removeFriend(stringToLong(myId),stringToLong(userId));
        return false;

    }
}
