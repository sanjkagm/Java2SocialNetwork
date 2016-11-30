package lv.javaguru.java2.servlet;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserMessage;
import lv.javaguru.java2.mvc.MVCController;
import lv.javaguru.java2.mvc.MVCModel;
import lv.javaguru.java2.service.MessagesService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Pavel on 29.11.2016..
 */
public class MessagesController implements MVCController {

    public MVCModel processGet(HttpServletRequest req) {

        String view;
        Object data;
        String messages = null;


        MessagesService msgService = new MessagesService();



        try {
            User userInSession = msgService.checkIfUserLoggedIn(req);
            data = msgService.getMessagesToUserByUsername(userInSession.getUsername());
            view = "/messagesView.jsp";
        } catch (Exception exception) {
            view = "/error.jsp";
            data = null;
            messages = "Error";
        }

        return new MVCModel(view,data,messages);
    }


    public MVCModel processPost(HttpServletRequest req) {
        return processGet(req);
    }
}
