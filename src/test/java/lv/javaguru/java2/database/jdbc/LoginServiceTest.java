package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserMessage;
import lv.javaguru.java2.service.Utils;
import lv.javaguru.java2.service.LoginService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;

/**
 * Created by Marlen on 12/4/2016.
 */
public class LoginServiceTest extends DBUnitTestCase {

    private UserDAO userDAO = new UserDAOImpl();
    private Utils utils = new Utils();

    private String firstName = "firstName";
    private String lastName = "lastName";
    private String username = "usernameTest";
    private String password = "passwordTest";
    private String date_of_birth = "1998-10-04";
    private String looking_for = "F";
    private String city = "cityTest";
    private String country = "countryTest";
    private String age_fromStr = "20";
    private String age_toStr = "30";
    private String sex = "M";
    private String about = "aboutTest";


    @Override
    protected String getDatabaseFile() {
        return "dbscripts/UserDAOImplTest.xml";
    }


    @Before
    public void setUp() throws Exception {
        User user = utils.createUserByBuilder("0",username,password,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);

        userDAO.create(user);
    }

    @After
    public void tearDown() throws Exception {}

    @Test
    public void create() throws Exception {
        User user = utils.createUserByBuilder("0",username,password,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);

        userDAO.create(user);

        User userFromDB = userDAO.getById(user.getUserId());
        assertNotNull(userFromDB);
        assertEquals(user.getUserId(), userFromDB.getUserId());
        assertEquals(user.getFirstName(), userFromDB.getFirstName());
        assertEquals(user.getLastName(), userFromDB.getLastName());
        assertEquals(user.getUsername(), userFromDB.getUsername());
        assertEquals(user.getAge_from(), userFromDB.getAge_from());
        assertEquals(user.getAge_to(), userFromDB.getAge_to());
        assertEquals(user.getPassword(), userFromDB.getPassword());
    }

    @Test
    public void  validateUseLoginAndPassword() throws Exception {
        User user = utils.createUserByBuilder("0",username,password,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);
        userDAO.create(user);
        assertEquals(user, userDAO.getById(user.getUserId()));
    }
    @Test
    public void testGetUserByIdNull() throws Exception {
        assertNull(userDAO.getById(1L));
    }


}
