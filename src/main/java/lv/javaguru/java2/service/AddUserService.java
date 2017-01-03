package lv.javaguru.java2.service;
import lv.javaguru.java2.domain.User;



/**
 * Created by Pavel on 15.11.2016..
 */

public interface AddUserService{

    String validatePasswords(String password,String password_repeat);

    String register(User user);


}
