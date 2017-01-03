package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.domain.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lv.javaguru.java2.domain.UserBuilder;
import lv.javaguru.java2.domain.UserMessage;
import lv.javaguru.java2.domain.UserMessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Pavel on 15.11.2016..
 */

public interface Utils {

    User checkIfUserLoggedIn(HttpServletRequest request);
    void storeLoggedUserInSession(HttpServletRequest request, User loginedUser);
    void storeUserCookie(HttpServletResponse response, User user);
    Integer stringToInteger (String Str);
    Long stringToLong (String Str);
    UserMessage createUserMessageByBuilder (String idStr,
                                            String sender,
                                            String recipient,
                                            String text,
                                            Date created,
                                            Boolean isRead);
    User createUserByBuilder (String userIdStr,
                              String username,
                              String password,
                              String date_of_birth,
                              String firstName,
                              String lastName,
                              String sex,
                              String city,
                              String country,
                              String looking_for,
                              String age_fromStr,
                              String age_toStr,
                              String about);

    boolean isEmpty(final String string);

    void deleteUserCookie(HttpServletResponse response);
    String getUserNameInCookie(HttpServletRequest request);
    void storeLoginedUser(HttpSession session, User loginedUser);
    boolean sendMessage(Long myId, Long userId, String type);

}
