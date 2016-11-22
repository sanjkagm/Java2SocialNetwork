package lv.javaguru.java2.service;

/**
 * Created by Pavel on 22.11.2016..
 */
import lv.javaguru.java2.domain.User;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.*;

public class SessionCounter implements HttpSessionListener {



    public SessionCounter() {
    }

    public void sessionCreated(HttpSessionEvent event) {
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        User user = (User) event.getSession().getAttribute("loginedUser");
        if (user != null) {
            List<User> onlineUsers = (List<User>) event.getSession().getServletContext().getAttribute("onlineUsers");
            onlineUsers.remove(user);
            System.out.println("User removed: " + user.getUserId() + " " + user.getUsername());
        }
    }

}