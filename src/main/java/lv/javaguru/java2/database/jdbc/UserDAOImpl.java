package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.GenericHibernateDAOImpl;
import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.JDBCException;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOImpl extends GenericHibernateDAOImpl<User> implements UserDAO {

    private Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);


    @Transactional
    public Long getIdByUsername(String username) throws JDBCException {
        String hql = "select UserID from USERS where username = :username";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
        query.setParameter("username", username);
        if (query.list().size() > 0){

            return new Long((int)query.list().get(0));
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    @Transactional
    public List<User> getFriends(Long myId) throws JDBCException {
        String hql = "select * from USERS where UserID IN (SELECT friend_id FROM users_friends WHERE user_id = :myId AND is_accepted = 1) OR UserID IN (SELECT user_id FROM users_friends WHERE friend_id = :myId AND is_accepted = 1)";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql)
                .addEntity(User.class);
        query.setParameter("myId", myId);
        return query.list();
    }

    @Transactional
    public User getByUsernameAndPassword(String username, String password) throws JDBCException {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq("username", username));
        criteria.add(Restrictions.eq("password", password));
        return criteria.list().size() == 1 ? (User)criteria.list().get(0) : null;
    }

    @Transactional
    public User getByUsername(String username) throws JDBCException {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq("username", username));
        return criteria.list().size() == 1 ? (User)criteria.list().get(0) : null;
    }



    @Transactional
    public boolean checkUserPending(Long myId, Long userId) throws JDBCException {
        String hql = "select id from users_friends where (user_id = :myId AND friend_id = :userId AND is_accepted = 0) OR (friend_id = :myId AND user_id = :userId AND is_accepted = 0) LIMIT 1";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
        query.setParameter("myId", myId);
        query.setParameter("userId", userId);
        return query.list().size() > 0 ? true : false;
    }

    @Transactional
    public int acceptFriendRequest(Long myId, Long userId) throws JDBCException {
        String hql = "update USERS_FRIENDS set is_accepted = 1 where user_id = :userId and friend_id = :myId";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
        query.setParameter("myId", myId);
        query.setParameter("userId", userId);

        return query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<User> search(String city, String country, String looking_for, Integer age_from, Integer age_to, Long myId) throws JDBCException {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(persistentClass);
        criteria.add(Restrictions.like("city", "%"+city+"%"));
        criteria.add(Restrictions.like("country", "%"+country+"%"));
        criteria.add(Restrictions.eq("looking_for", looking_for));

        if (age_from > 0)
            criteria.add(Restrictions.le("age_from", age_from));
        else
            criteria.add(Restrictions.ge("age_from", age_from));

        criteria.add(Restrictions.ge("age_to", age_to));
        criteria.add(Restrictions.ne("userId", myId));

        return criteria.list();
    }

    @Transactional
    public void updatePassword(User user) throws JDBCException {
        String hql = "update USERS set password = :password where UserID = :UserID";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
        query.setParameter("password", user.getPassword());
        query.setParameter("UserID", user.getUserId());
        query.executeUpdate();
    }

    @Transactional
    public void addFriend(Long myId, Long userId) throws JDBCException {
        String hql = "insert into users_friends values (default, :myId, :userId, 1)";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
        query.setParameter("myId", myId);
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

    @Transactional
    public BigInteger addFriendRequest(Long myId, Long userId) throws JDBCException {

        String hql = "insert into users_friends values (default, :myId, :userId, 0)";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
        query.setParameter("myId", myId);
        query.setParameter("userId", userId);
        query.executeUpdate();

        BigInteger result = (BigInteger)sessionFactory.getCurrentSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult();

        return result;
    }

    @Transactional
    public void removeFriend(Long myId, Long userId) throws JDBCException  {
        String hql = "delete from users_friends where (user_id = :myId AND friend_id = :userId) OR (friend_id = :myId AND user_id = :userId)";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
        query.setParameter("myId", myId);
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

    @Transactional
    public boolean checkUserFriend(Long myId, Long userId) throws DBException {
        String hql = "select id from users_friends where (user_id = :myId AND friend_id = :userId AND is_accepted = 1) OR (friend_id = :myId AND user_id = :userId AND is_accepted = 1) LIMIT 1";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
        query.setParameter("myId", myId);
        query.setParameter("userId", userId);

        return query.list().size() > 0 ? true : false;
    }

    /*public Long getIdByUsername(String username) throws DBException {
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

    public User getById(long id) throws DBException {
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

    public void delete(long id) throws DBException {
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
                    .prepareStatement("select id from users_friends where (user_id = ? AND friend_id = ? AND is_accepted = 1) OR (friend_id = ? AND user_id = ? AND is_accepted = 1) LIMIT 1");
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

    public boolean checkUserPending(Long myId, Long userId) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select id from users_friends where (user_id = ? AND friend_id = ? AND is_accepted = 0) OR (friend_id = ? AND user_id = ? AND is_accepted = 0) LIMIT 1");
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

    public Long addFriendRequest(Long myId, Long userId) {

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into users_friends values (default, ?, ?, 0)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, myId);
            preparedStatement.setLong(2, userId);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                return rs.getLong(1);
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.addFriendRequest()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return null;
    }
    public int acceptFriendRequest(Long myId, Long userId) {
        Connection connection = null;
        Integer result = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("update USERS_FRIENDS set is_accepted = 1 where user_id = ? and friend_id = ?");
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, myId);

            System.out.println(preparedStatement);
            result = preparedStatement.executeUpdate();
            return result;
        } catch (Throwable e) {
            System.out.println("Exception while execute UserDAOImpl.acceptFriendRequest()");
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
            PreparedStatement preparedStatement = connection.prepareStatement("select * from USERS where UserID IN (SELECT friend_id FROM users_friends WHERE user_id = ? AND is_accepted = 1) OR UserID IN (SELECT user_id FROM users_friends WHERE friend_id = ? AND is_accepted = 1)");
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
    */

}
