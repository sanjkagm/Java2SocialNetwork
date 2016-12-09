package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserDAO {

    Long getIdByUsername(String username);

    void create(User user);

    User getById(Long id);
    User getByUsernameAndPassword(String username, String password);
    User getByUsername(String username);

    void delete(Long id);

    void update(User user);
    void updatePassword(User user);

    List<User> getAll();
    List<User> search(String city, String country, String looking_for, Integer age_from, Integer age_to, Long myId);

    boolean checkUserFriend(Long myId, Long userId);
    boolean checkUserPending(Long myId, Long userId);

    Long addFriendRequest(Long myId,Long userId);
    int acceptFriendRequest(Long myId,Long userId);

    void addFriend(Long myId,Long userId);
    void removeFriend(Long myId,Long userId);

    List<User> getFriends(Long userId);


}
