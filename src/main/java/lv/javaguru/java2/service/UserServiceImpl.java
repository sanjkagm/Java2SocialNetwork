package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Pavel on 03.01.2017..
 */
@Service
@Transactional
@SuppressWarnings("unchecked")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private Utils utils;


    public User getUserById (String userId) {
        User user = userDAO.getById(utils.stringToLong(userId));
        return user;
    }

    public Boolean checkUserFriend (Long myId, String userId) {
        return userDAO.checkUserFriend(myId,utils.stringToLong(userId));
    }

    public Boolean checkUserPending (Long myId, String userId) {
        return userDAO.checkUserPending(myId,utils.stringToLong(userId));
    }
}
