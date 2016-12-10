package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.UserMessage;

import java.util.List;

/**
 * Created by Pavel on 29.11.2016..
 */
public interface UserMessageDAO {

    List<UserMessage> getMessagesToUserByUsername(String username);
    Integer readMsg(Long msgId);


    void create(UserMessage user);

    UserMessage getById(Long id);


    void delete(Long id);

    void update(UserMessage user);

    List<UserMessage> getAll();
}
