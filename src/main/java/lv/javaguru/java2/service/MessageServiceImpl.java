package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.domain.UserMessage;
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
public class MessageServiceImpl implements MessagesService {

    @Autowired
    private UserMessageDAO userMessageDAO;
    @Autowired
    private Utils utils;

    public List<UserMessage> getMessagesToUserByUsername (String username) {
        List<UserMessage> userMessages = userMessageDAO.getMessagesToUserByUsername(username);
        return userMessages;
    }

    public void deleteMsg (String msgId) {
        userMessageDAO.delete(utils.stringToLong(msgId));
    }
}
