package lv.javaguru.java2.service;

import lv.javaguru.java2.database.UserDAO;
import lv.javaguru.java2.database.UserMessageDAO;
import lv.javaguru.java2.database.jdbc.UserDAOImpl;
import lv.javaguru.java2.database.jdbc.UserMessageDAOImpl;
import lv.javaguru.java2.domain.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lv.javaguru.java2.domain.UserBuilder;
import lv.javaguru.java2.domain.UserMessage;
import lv.javaguru.java2.domain.UserMessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Pavel on 15.11.2016..
 */
@Service
public class Utils {

    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserMessageDAO userMessageDAO;

    public User checkIfUserLoggedIn(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (User) session.getAttribute("loginedUser");
    }

    public void storeLoggedUserInSession(HttpServletRequest request, User loginedUser) {
        HttpSession session = request.getSession();
        // On the JSP can access ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
        if(null == request.getServletContext().getAttribute("onlineUsers"))
            request.getServletContext().setAttribute("onlineUsers", new ArrayList<User>());

        List<User> onlineUsers = (List<User>) request.getServletContext().getAttribute("onlineUsers");
        if (loginedUser != null) {
            onlineUsers.add(loginedUser);
            System.out.println("User added: " + loginedUser.getUserId() + " " + loginedUser.getUsername());
        }

    }

    public void storeUserCookie(HttpServletResponse response, User user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUsername());

        // 1 day (Convert to seconds)
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }

    public Integer stringToInteger (String Str) {
        int Int;
        try {
            Int = Integer.parseInt(Str);
        } catch (Exception e) {
            Int = 0;
        }
        return Int;
    }
    public Long stringToLong (String Str) {
        long Lng;
        try {
            Lng = Long.parseLong(Str);
        } catch (Exception e) {
            Lng = 0;
        }
        return Lng;
    }

    public UserMessage createUserMessageByBuilder (String idStr,
                                                   String sender,
                                                   String recipient,
                                                   String text,
                                                   Date created,
                                                   Boolean isRead)

    {

        long id = stringToLong(idStr);

        return UserMessageBuilder.createUserMessage()
                .withMsgId(id)
                .withSender(sender)
                .withRecipient(recipient)
                .withText(text)
                .withCreated(created)
                .withIsRead(isRead).build();
    }

    public User createUserByBuilder (String userIdStr,
                                     String username,
                                     String password,
                                     String date_of_birth,
                                     String firstName,
                                     String lastName,
                                     String sex,
                                     String city,
                                     String country,
                                     String looking_for,
                                     String age_fromStr,
                                     String age_toStr,
                                     String about)

    {
        int age_from = stringToInteger(age_fromStr);
        int age_to = stringToInteger(age_toStr);
        long userId = stringToLong(userIdStr);


        return UserBuilder.createUser()
                .withUserId(userId)
                .withUsername(username)
                .withPassword(password)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withDate_of_birth(date_of_birth)
                .withCity(city)
                .withCountry(country)
                .withSex(sex)
                .withLooking_for(looking_for)
                .withAge_from(age_from)
                .withAge_to(age_to)
                .withAbout(about).build();
    }

    public static boolean isEmpty(final String string) {
        return string == null || string.trim().isEmpty();
    }

    // Delete cookie.
    public void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        System.out.println("Delete user cookie");
        // 0 seconds (Expires immediately)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }

    public String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    // Store user info in Session.
    public void storeLoginedUser(HttpSession session, User loginedUser) {
        // On the JSP can access ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }

    public boolean sendMessage(Long myId, Long userId, String type) {

        User sender = userDAO.getById(myId);
        User recipient = userDAO.getById(userId);
        UserMessage friendRequest = null;
        if (type.equals("remove")) {
            friendRequest =
                    createUserMessageByBuilder("0", sender.getUsername(), recipient.getUsername(),
                            "Unfriend notification from " + sender.getUsername(), new Date(), false);
        } else if (type.equals("cancel")) {
            friendRequest =
                    createUserMessageByBuilder("0", sender.getUsername(), recipient.getUsername(),
                            "Invitation cancelled by " + sender.getUsername() + ".", new Date(), false);
        }
        userMessageDAO.create(friendRequest);
        return true;

    }


}
