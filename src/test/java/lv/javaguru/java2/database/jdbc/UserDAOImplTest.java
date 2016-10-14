package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.domain.User;
import org.junit.Test;

import static lv.javaguru.java2.domain.UserBuilder.createUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDAOImplTest extends DBUnitTestCase {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected String getDatabaseFile() {
        return "dbscripts/UserDAOImplTest.xml";
    }

    @Test
    public void testCreate() throws Exception {
        User user = createUser()
                .withFirstName("F")
                .withLastName("L").build();

        userDAO.create(user);

        User userFromDB = userDAO.getById(user.getUserId());
        assertNotNull(userFromDB);
        assertEquals(user.getUserId(), userFromDB.getUserId());
        assertEquals(user.getFirstName(), userFromDB.getFirstName());
        assertEquals(user.getLastName(), userFromDB.getLastName());
    }

}
