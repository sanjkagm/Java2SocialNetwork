package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.database.jdbc.UserMessageDAOImpl;
import lv.javaguru.java2.domain.UserMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 30.11.2016..
 */
public class MessagesService extends Utils{

    public List<UserMessage> getMessagesToUserByUsername (String username) {

        UserMessageDAO userMessageDAOObj = new UserMessageDAOImpl();
        List<UserMessage> userMessages = userMessageDAOObj.getMessagesToUserByUsername(username);

        return userMessages;
    }
}
