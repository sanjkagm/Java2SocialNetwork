package lv.javaguru.java2.service;


import lv.javaguru.java2.domain.User;

import java.util.List;

/**
 * Created by Pavel on 22.11.2016..
 */

public interface MainService{
    List<User> getFriends (Long myId);

}
