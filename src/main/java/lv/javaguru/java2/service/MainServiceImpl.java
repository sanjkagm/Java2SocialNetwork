package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Pavel on 03.01.2017..
 */
@Service
@Transactional
@SuppressWarnings("unchecked")
public class MainServiceImpl implements MainService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private Utils utils;

    public List<User> getFriends (Long myId) {

        List<User> usersFound = userDAO.getFriends(myId);

        return usersFound;
    }
}
