package lv.javaguru.java2.service;


import lv.javaguru.java2.domain.User;

/**
 * Created by Pavel on 14.11.2016..
 */

public interface LoginService {
    boolean validateUseLoginAndPassword(String username, String password);
    User getUserByUsernameAndPassword(String username, String password);
    User presetUsernameAndPasswordForLoginForm(String username, String password);
    String authenticate(String username, String password);



}
