package lv.javaguru.java2.database;

import lv.javaguru.java2.domain.User;

import java.util.List;

public interface UserDAO {

    void create(User user);

    User getById(Long id);
    User getByUsernameAndPassword(String username, String password);
    User getByUsername(String username);

    void delete(Long id);

    void update(User user);
    void updatePassword(User user);

    List<User> getAll();

}
