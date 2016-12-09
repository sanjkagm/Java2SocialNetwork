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
        List<UserMessage> data;
        String[] messages = new String[2];





        MessagesService msgService = new MessagesService();

        try {
            String pathInfo = req.getServletPath();
            String action = null;
            String msgId = null;
            if (pathInfo == null) {
                //
            } else {
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length > 2) {
                    action = pathParts[2]; //
                }
                if (pathParts.length > 3) {
                    msgId = pathParts[3]; //
                }
            }
            if (action == null) {


                User userInSession = msgService.checkIfUserLoggedIn(req);
                data = msgService.getMessagesToUserByUsername(userInSession.getUsername());
                view = "/messagesView.jsp";
                if (data.size() == 0) {
                    messages[1] = "You have no messages.";
                    messages[0] = null;
                }
            }
            else if (action.equals("delete")) {
                if (msgId != null) {
                    User userInSession = msgService.checkIfUserLoggedIn(req);
                    msgService.deleteMsg(msgId);
                    data = msgService.getMessagesToUserByUsername(userInSession.getUsername());
                    view = "/messagesView.jsp";
                    if (data.size() == 0) {
                        messages[1] = "You have no messages.";
                    }
                    messages[0] = "Message deleted.";
                } else {
                    view = "/error.jsp";
                    data = null;
                    messages[0] = "Message ID: " + msgId;
                }

            } else {
                view = "/error.jsp";
                data = null;
                messages[0] = "Action: " + action;
            }


        } catch (Exception exception) {
            view = "/error.jsp";
            data = null;
            messages[0] = "Error";
        }

        return new MVCModel(view,data,messages);
    }


    public MVCModel processPost(HttpServletRequest req) {
        return processGet(req);
    }

}