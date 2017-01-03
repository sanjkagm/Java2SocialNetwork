package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.domain.UserMessage;
import lv.javaguru.java2.service.Utils;
import lv.javaguru.java2.service.UtilsImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;

/**
 * Created by Pavel on 30.11.2016..
 */
public class UserMessageDAOImplTest  extends DBUnitTestCase {

    private UserMessageDAO userMessageDAO = new UserMessageDAOImpl();
    private Utils utils = new UtilsImpl();

    private String sender = "user1";
    private String recipient = "user2";
    private Date created = new Date();
    private String text = "Test message!";
    private Boolean isRead = false;



    @Override
    protected String getDatabaseFile() {
        return "dbscripts/UserMessageDAOImplTest.xml";
    }
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void create() throws Exception {
        UserMessage msg = utils.createUserMessageByBuilder("0",sender,recipient,text,created,isRead);

        userMessageDAO.create(msg);

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



        UserMessage msgFromDB = userMessageDAO.getById(msg.getId());
        assertNotNull(msgFromDB);
        assertEquals(msg.getId(), msgFromDB.getId());
        assertEquals(msg.getSender(), msgFromDB.getSender());
        assertEquals(msg.getRecipient(), msgFromDB.getRecipient());
        assertEquals(msg.getText(), msgFromDB.getText());
        assertEquals(sdf.format(msg.getCreated()), sdf.format(msgFromDB.getCreated()));
        assertEquals(msg.getIsRead(), msgFromDB.getIsRead());
    }

    @Test
    public void getAll() throws Exception {
        UserMessage msg = utils.createUserMessageByBuilder("0",sender,recipient,text,created,isRead);

        userMessageDAO.create(msg);
        UserMessage msgFromDb = null;
        List<UserMessage> msgListFromDb = userMessageDAO.getAll();
        for (UserMessage item : msgListFromDb){
            if (item.getId().equals(msg.getId())){
                msgFromDb = item;
            }
        }
        assertTrue(msgListFromDb.size() >= 1);
        assertEquals(msg, msgFromDb);
    }

    @Test
    public void delete() throws Exception {

        int userListSize = userMessageDAO.getAll().size();
        UserMessage msg = utils.createUserMessageByBuilder("0",sender,recipient,text,created,isRead);
        userMessageDAO.create(msg);
        userMessageDAO.delete(msg.getId());
        assertNull(userMessageDAO.getById(msg.getId()));
        assertEquals(userListSize, userMessageDAO.getAll().size());

    }

    @Test
    public void update() throws Exception {
        UserMessage msg = utils.createUserMessageByBuilder("0",sender,recipient,text,created,isRead);
        userMessageDAO.create(msg);
        msg = updateMsg(msg);
        userMessageDAO.update(msg);
        assertEquals(msg, userMessageDAO.getById(msg.getId()));
    }

    private UserMessage updateMsg (UserMessage msg){
        msg.setText("New" + text);
        return msg;
    }

    @Test
    public void getMessagesToUserByUsername() throws Exception {
        UserMessage userMessage = utils.createUserMessageByBuilder("0",sender,recipient,text,created,isRead);
        userMessageDAO.create(userMessage);
        UserMessage userMessageFromDb = null;
        List<UserMessage> userMessagesListFromDb = userMessageDAO.getMessagesToUserByUsername(recipient);
        for (UserMessage item : userMessagesListFromDb){
            if (item.getId().equals(userMessage.getId())){
                userMessageFromDb = item;
            }
        }
        assertTrue(userMessagesListFromDb.size() >= 1);
        assertEquals(userMessage, userMessageFromDb);


    }

}