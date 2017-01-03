package lv.javaguru.java2.service;

import lv.javaguru.java2.domain.User;
import java.util.List;

/**
 * Created by Pavel on 19.11.2016..
 */

public interface SearchService {

    List<User> searchUsers (String city, String country, String looking_for, String age_fromStr, String age_toStr, Long myId);
}
