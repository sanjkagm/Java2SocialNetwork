package lv.javaguru.java2.service;


import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.domain.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Pavel on 30.11.2016..
 */

public interface MessagesService {
    List<UserMessage> getMessagesToUserByUsername (String username);
    void deleteMsg (String msgId);
}
