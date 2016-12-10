package lv.javaguru.java2.service;


import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.domain.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Pavel on 30.11.2016..
 */
@Service
public class MessagesService extends Utils{

    @Autowired
    private UserMessageDAO userMessageDAO;

    public List<UserMessage> getMessagesToUserByUsername (String username) {
        List<UserMessage> userMessages = userMessageDAO.getMessagesToUserByUsername(username);
        return userMessages;
    }

    public void deleteMsg (String msgId) {
        userMessageDAO.delete(stringToLong(msgId));
    }
}
