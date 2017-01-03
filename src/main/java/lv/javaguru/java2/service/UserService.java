package lv.javaguru.java2.service;


import lv.javaguru.java2.domain.User;

/**
 * Created by Pavel on 21.11.2016..
 */

public interface UserService {
    User getUserById (String userId);
    Boolean checkUserFriend (Long myId, String userId);
    Boolean checkUserPending (Long myId, String userId);

}
