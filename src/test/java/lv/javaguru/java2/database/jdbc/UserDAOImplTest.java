package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserDAOImplTest extends DBUnitTestCase {

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
    public void setUp() throws Exception {}

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
    public void getById() throws Exception {
        User user = utils.createUserByBuilder("0",username,password,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);
        userDAO.create(user);
        assertEquals(user, userDAO.getById(user.getUserId()));
    }
    @Test
    public void testGetUserByIdNull() throws Exception {
        assertNull(userDAO.getById(1L));
    }

    @Test
    public void getByUsernameAndPassword() throws Exception {
        User user = utils.createUserByBuilder("0",username,password,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);
        userDAO.create(user);
        assertEquals(user, userDAO.getByUsernameAndPassword( user.getUsername(), user.getPassword() ));
    }

    @Test
    public void getByUsername() throws Exception {
        User user = utils.createUserByBuilder("0",username,password,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);
        userDAO.create(user);
        assertEquals(user, userDAO.getByUsername( user.getUsername() ));
    }

    @Test
    public void getAll() throws Exception {
        User user = utils.createUserByBuilder("0",username,password,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);
        userDAO.create(user);
        User userFromDb = null;
        List<User> userListFromDb = userDAO.getAll();
        for (User item : userListFromDb){
            if (item.getUserId().equals(user.getUserId())){
                userFromDb = item;
            }
        }
        assertTrue(userListFromDb.size() >= 1);
        assertEquals(user, userFromDb);
    }

    @Test
    public void delete() throws Exception {
        Utils utils = new Utils();
        int userListSize = userDAO.getAll().size();
        User user = utils.createUserByBuilder("0",username,password,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);
        userDAO.create(user);
        userDAO.delete(userDAO.getIdByUsername(username));
        assertNull(userDAO.getIdByUsername(username));
        assertEquals(userListSize, userDAO.getAll().size());

    }

    @Test
    public void update() throws Exception {
        User user = utils.createUserByBuilder("0",username,password,date_of_birth,firstName,lastName,sex,"new" + city,country,looking_for,age_fromStr,age_toStr,about);
        userDAO.create(user);
        user = updateUser(user);
        userDAO.update(user);
        assertEquals(user, userDAO.getById(user.getUserId()));
    }

    private User updateUser (User user){
        user.setFirstName("1" + firstName);
        user.setLastName("1" + lastName);
        user.setCity(city);
        user.setPassword("1" + password);
        return user;
    }

    @Test
    public void updatePassword() throws Exception {
        User user = utils.createUserByBuilder("0",username,"new" + password,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);
        userDAO.create(user);
        user.setPassword(password);
        userDAO.updatePassword(user);
        assertEquals(user, userDAO.getById(user.getUserId()));
    }

    @Test
    public void search() throws Exception {
        User user = utils.createUserByBuilder("0",username,password,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);
        User user2 = utils.createUserByBuilder("0","my" + username,password,date_of_birth,firstName,lastName,sex,city,country,looking_for,age_fromStr,age_toStr,about);
        userDAO.create(user);
        userDAO.create(user2);
        User userFromDb = null;
        List<User> userListFromDb = userDAO.search(city,country,looking_for,utils.stringToInteger(age_fromStr),utils.stringToInteger(age_toStr), user2.getUserId());
        for (User item : userListFromDb){
            if (item.getUserId().equals(user.getUserId())){
                userFromDb = item;
            }
        }
        assertTrue(userListFromDb.size() == 1);
        assertEquals(user, userFromDb);
    }

    @Test
    public void checkUserFriend() throws Exception {
        userDAO.addFriend(utils.stringToLong("2"),utils.stringToLong("3"));
        boolean userIsFriend = userDAO.checkUserFriend(utils.stringToLong("2"),utils.stringToLong("3"));
        assertTrue(userIsFriend);
    }

    @Test
    public void addFriend() throws Exception {
        userDAO.addFriend(utils.stringToLong("2"),utils.stringToLong("3"));
        boolean userIsFriend = userDAO.checkUserFriend(utils.stringToLong("2"),utils.stringToLong("3"));

        assertTrue(userIsFriend);
    }

    @Test
    public void removeFriend() throws Exception {
        userDAO.addFriend(utils.stringToLong("2"),utils.stringToLong("3"));
        userDAO.removeFriend(utils.stringToLong("2"),utils.stringToLong("3"));
        boolean userIsFriend = userDAO.checkUserFriend(utils.stringToLong("2"),utils.stringToLong("3"));

        assertFalse(userIsFriend);
    }

    @Test
    public void getFriends() throws Exception {
        User user = utils.createUserByBuilder("0", username, password, date_of_birth, firstName, lastName, sex, city, country, looking_for, age_fromStr, age_toStr, about);
        User user2 = utils.createUserByBuilder("0", "friend1" + username, password, date_of_birth, firstName, lastName, sex, city, country, looking_for, age_fromStr, age_toStr, about);
        User user3 = utils.createUserByBuilder("0", "friend2" + username, password, date_of_birth, firstName, lastName, sex, city, country, looking_for, age_fromStr, age_toStr, about);
        userDAO.create(user);
        userDAO.create(user2);
        userDAO.create(user3);
        userDAO.addFriend(user.getUserId(), user2.getUserId());
        userDAO.addFriend(user.getUserId(), user3.getUserId());
        User friend1FromDb = null;
        User friend2FromDb = null;
        List<User> friendListFromDb = userDAO.getFriends(user.getUserId());
        for (User item : friendListFromDb) {
            if (item.getUserId().equals(user2.getUserId())) {
                friend1FromDb = item;
            }
            if (item.getUserId().equals(user3.getUserId())) {
                friend2FromDb = item;
            }
        }
        assertTrue(friendListFromDb.size() == 2);
        assertEquals(user2, friend1FromDb);
        assertEquals(user3, friend2FromDb);
        }
}
