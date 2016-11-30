package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends DAOImpl implements UserDAO {


    public Long getIdByUsername(String username) throws DBException {
        Connection connection = null;


        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT UserID FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            Long id = null;
            if (resultSet.next()) {
                id = resultSet.getLong("UserID");
            }
            return id;
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.checkUserFriend()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public void create(User user) throws DBException {
        if (user == null) {
            return;
        }

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into USERS values (?, ?, default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());

            preparedStatement.setString(5, user.getDate_of_birth());
            preparedStatement.setString(6, user.getCity());
            preparedStatement.setString(7, user.getCountry());
            preparedStatement.setString(8, user.getSex());
            preparedStatement.setString(9, user.getLooking_for());
            preparedStatement.setInt(10, user.getAge_from());
            preparedStatement.setInt(11, user.getAge_to());
            preparedStatement.setString(12, user.getAbout());

            //System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                user.setUserId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

    }

    public User getById(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from USERS where UserID = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getLong("UserID"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setCountry(resultSet.getString("country"));
                user.setCity(resultSet.getString("city"));
                user.setDate_of_birth(resultSet.getString("date_of_birth"));
                user.setLooking_for(resultSet.getString("looking_for"));
                user.setAge_from(resultSet.getInt("age_from"));
                user.setAge_to(resultSet.getInt("age_to"));
                user.setSex(resultSet.getString("sex"));
                user.setAbout(resultSet.getString("about"));
            }
            return user;
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public User getByUsernameAndPassword(String username, String password) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from USERS where username = ? AND password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getLong("UserID"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));

                user.setCountry(resultSet.getString("country"));
                user.setCity(resultSet.getString("city"));

                user.setDate_of_birth(resultSet.getString("date_of_birth"));
                user.setLooking_for(resultSet.getString("looking_for"));

                user.setAge_from(resultSet.getInt("age_from"));
                user.setAge_to(resultSet.getInt("age_to"));

                user.setSex(resultSet.getString("sex"));
                user.setAbout(resultSet.getString("about"));
            }
            return user;
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.getByUsernameAndPassword()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public User getByUsername(String username) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from USERS where username = ?");
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {

                user = new User();
                user.setUserId(resultSet.getLong("UserID"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setCountry(resultSet.getString("country"));
                user.setCity(resultSet.getString("city"));

                user.setDate_of_birth(resultSet.getString("date_of_birth"));
                user.setLooking_for(resultSet.getString("looking_for"));

                user.setAge_from(resultSet.getInt("age_from"));
                user.setAge_to(resultSet.getInt("age_to"));

                user.setSex(resultSet.getString("sex"));
                user.setAbout(resultSet.getString("about"));


            }
            return user;
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.getByUsername");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }



    public List<User> getAll() throws DBException {
        List<User> users = new ArrayList<User>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from USERS");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong("UserID"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setCountry(resultSet.getString("country"));
                user.setCity(resultSet.getString("city"));

                user.setDate_of_birth(resultSet.getString("date_of_birth"));
                user.setLooking_for(resultSet.getString("looking_for"));

                user.setAge_from(resultSet.getInt("age_from"));
                user.setAge_to(resultSet.getInt("age_to"));

                user.setSex(resultSet.getString("sex"));
                user.setAbout(resultSet.getString("about"));
                users.add(user);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list UserDAOImpl.getList()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return users;
    }

    public void delete(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from USERS where UserID = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public void update(User user) throws DBException {
        if (user == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update USERS set FirstName = ?, LastName = ? , city = ?, country = ?, looking_for = ?, " +
                            "age_from = ?, age_to = ?, about = ? " +
                            "where UserID = ?");

            preparedStatement.executeQuery("SET NAMES 'UTF8'");
            preparedStatement.executeQuery("SET CHARACTER SET 'UTF8'");

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getCity());
            preparedStatement.setString(4, user.getCountry());
            preparedStatement.setString(5, user.getLooking_for());
            preparedStatement.setInt(6, user.getAge_from());
            preparedStatement.setInt(7, user.getAge_to());
            preparedStatement.setString(8, user.getAbout());

            preparedStatement.setLong(9, user.getUserId());
            //System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public void updatePassword(User user) throws DBException {
        if (user == null) {
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update USERS set password = ? " +
                            "where UserID = ?");
            preparedStatement.setString(1, user.getPassword());

            preparedStatement.setLong(2, user.getUserId());

            //System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.updatePassword()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<User> search(String city, String country, String looking_for, Integer age_from, Integer age_to, Long myId) throws DBException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement;
            if (age_from > 0)
                preparedStatement = connection.prepareStatement("select * from USERS WHERE city LIKE ? AND country LIKE ? AND looking_for = ? AND age_from <= ? AND age_to >= ? AND UserID <> ?");
            else
                preparedStatement = connection.prepareStatement("select * from USERS WHERE city LIKE ? AND country LIKE ? AND looking_for = ? AND age_from >= ? AND age_to >= ? AND UserID <> ?");

            preparedStatement.setString(1, "%" + city + "%");
            preparedStatement.setString(2, "%" + country + "%");
            preparedStatement.setString(3, looking_for);
            preparedStatement.setInt(4, age_from);
            preparedStatement.setInt(5, age_to);

            preparedStatement.setLong(6, myId);

            //System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();

                user.setUserId(resultSet.getLong("UserID"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setCountry(resultSet.getString("country"));
                user.setCity(resultSet.getString("city"));
                user.setDate_of_birth(resultSet.getString("date_of_birth"));
                user.setLooking_for(resultSet.getString("looking_for"));
                user.setAge_from(resultSet.getInt("age_from"));
                user.setAge_to(resultSet.getInt("age_to"));
                user.setSex(resultSet.getString("sex"));
                user.setAbout(resultSet.getString("about"));

                users.add(user);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list UserDAOImpl.search()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return users;
    }

    public boolean checkUserFriend(Long myId, Long userId) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select id from users_friends where (user_id = ? AND friend_id = ?) OR (friend_id = ? AND user_id = ?) LIMIT 1");
            preparedStatement.setLong(1, myId);
            preparedStatement.setLong(2, userId);
            preparedStatement.setLong(3, myId);
            preparedStatement.setLong(4, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Boolean isFriend = false;
            if (resultSet.next()) {
                isFriend = true;
            }
            return isFriend;
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.checkUserFriend()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public void addFriend(Long myId, Long userId) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into users_friends values (default, ?, ?, 1)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, myId);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.addFriend()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public void removeFriend(Long myId, Long userId) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from users_friends where (user_id = ? AND friend_id = ?) OR (friend_id = ? AND user_id = ?)");
            preparedStatement.setLong(1, myId);
            preparedStatement.setLong(2, userId);
            preparedStatement.setLong(3, myId);
            preparedStatement.setLong(4, userId);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.removeFriend()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }


    public List<User> getFriends(Long myId) throws DBException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from USERS where UserID IN (SELECT friend_id FROM users_friends WHERE user_id = ?) OR UserID IN (SELECT user_id FROM users_friends WHERE friend_id = ?)");
            preparedStatement.setLong(1, myId);
            preparedStatement.setLong(2, myId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong("UserID"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setLastName(resultSet.getString("LastName"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setCountry(resultSet.getString("country"));
                user.setCity(resultSet.getString("city"));
                user.setDate_of_birth(resultSet.getString("date_of_birth"));
                user.setLooking_for(resultSet.getString("looking_for"));
                user.setAge_from(resultSet.getInt("age_from"));
                user.setAge_to(resultSet.getInt("age_to"));
                user.setSex(resultSet.getString("sex"));
                user.setAbout(resultSet.getString("about"));
                users.add(user);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list UserDAOImpl.getList()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return users;
    }

}
