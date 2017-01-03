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
public class SearchServiceImpl implements SearchService{

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private Utils utils;

    public List<User> searchUsers (String city, String country, String looking_for, String age_fromStr, String age_toStr, Long myId) {

        List<User> usersFound = userDAO.search(city, country, looking_for, utils.stringToInteger(age_fromStr), utils.stringToInteger(age_toStr), myId);

        return usersFound;
    }
}
