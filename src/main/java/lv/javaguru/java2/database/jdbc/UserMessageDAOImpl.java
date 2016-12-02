package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserMessage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 29.11.2016..
 */
public class UserMessageDAOImpl extends DAOImpl implements UserMessageDAO {

    public List<UserMessage> getMessagesToUserByUsername(String username) throws DBException {
        List<UserMessage> userMessages = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("SELECT * FROM messages WHERE recipient = ? ORDER BY created DESC");

            preparedStatement.setString(1, username);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                UserMessage userMessage = new UserMessage();

                userMessage.setId(resultSet.getLong("id"));
                userMessage.setSender(resultSet.getString("sender"));
                userMessage.setRecipient(resultSet.getString("recipient"));
                userMessage.setText(resultSet.getString("text"));
                userMessage.setCreated(resultSet.getTimestamp("created"));
                userMessage.setIsRead(resultSet.getBoolean("is_read"));


                userMessages.add(userMessage);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list UserDAOImpl.search()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return userMessages;
    }



    public void create(UserMessage userMessage) throws DBException {
        if (userMessage == null) {
            return;
        }
        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String created = sdf.format(userMessage.getCreated());

        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into MESSAGES values (default, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, userMessage.getSender());
            preparedStatement.setString(2, userMessage.getRecipient());

            preparedStatement.setString(3, userMessage.getText());

            preparedStatement.setBoolean(4, userMessage.getIsRead());
            preparedStatement.setString(5, created);


            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                userMessage.setId(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute UserMessageDAOImpl.create()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

    }

    public UserMessage getById(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from MESSAGES where id = ?");
            preparedStatement.setLong(1, id);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            UserMessage msg = null;
            if (resultSet.next()) {
                msg = new UserMessage();
                msg.setId(resultSet.getLong("id"));
                msg.setSender(resultSet.getString("sender"));
                msg.setRecipient(resultSet.getString("recipient"));
                msg.setText(resultSet.getString("text"));
                msg.setCreated(resultSet.getTimestamp("created"));
                msg.setIsRead(resultSet.getBoolean("is_read"));

            }
            return msg;
        } catch (Throwable e) {
            System.out.println("Exception while execute UserMessageDAOImpl.getById()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public List<UserMessage> getAll() throws DBException {
        List<UserMessage> msgs = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from MESSAGES");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserMessage msg = new UserMessage();
                msg.setId(resultSet.getLong("id"));
                msg.setSender(resultSet.getString("sender"));
                msg.setRecipient(resultSet.getString("recipient"));
                msg.setText(resultSet.getString("text"));
                msg.setCreated(resultSet.getTimestamp("created"));
                msg.setIsRead(resultSet.getBoolean("is_read"));
                msgs.add(msg);
            }
        } catch (Throwable e) {
            System.out.println("Exception while getting customer list UserMessageDAOImpl.getList()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return msgs;
    }


    public void delete(Long id) throws DBException {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from MESSAGES where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute UserMessageDAOImpl.delete()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    public void update(UserMessage msg) throws DBException {
        if (msg == null) {
            return;
        }
        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String created = sdf.format(msg.getCreated());

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update MESSAGES set sender = ?, recipient = ? , text = ?, created = ?, is_read = ? " +
                            "where id = ?");

            preparedStatement.executeQuery("SET NAMES 'UTF8'");
            preparedStatement.executeQuery("SET CHARACTER SET 'UTF8'");

            preparedStatement.setString(1, msg.getSender());
            preparedStatement.setString(2, msg.getRecipient());
            preparedStatement.setString(3, msg.getText());
            preparedStatement.setString(4, created);
            preparedStatement.setBoolean(5, msg.getIsRead());
            preparedStatement.setLong(6, msg.getId());
            //System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute UserMessageDAOImpl.update()");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }


}
