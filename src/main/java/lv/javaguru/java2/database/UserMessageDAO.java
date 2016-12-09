package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.UserMessage;

import java.util.List;
import org.springframework.stereotype.Component;
/**
 * Created by Pavel on 29.11.2016..
 */
@Component
public interface UserMessageDAO {

    List<UserMessage> getMessagesToUserByUsername(String username);



    void create(UserMessage user);

    UserMessage getById(Long id);


    void delete(Long id);

    void update(UserMessage user);

    List<UserMessage> getAll();
}
