package lv.javaguru.java2.servlet;


import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.domain.UserMessage;
import lv.javaguru.java2.service.MessagesService;
import lv.javaguru.java2.service.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Pavel on 29.11.2016..
 */
@Controller
public class MessagesController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessagesService msgService;
    @Autowired
    private Utils utils;

    @RequestMapping(value = "messages/delete/{msgId}", method = {RequestMethod.GET})
    public ModelAndView processGetOneRequest(@PathVariable("msgId") String msgId, HttpServletRequest request, HttpServletResponse response) {

        User userInSession = utils.checkIfUserLoggedIn(request);
        if (userInSession == null) {
            return new ModelAndView("redirect:/login");
        } else {
            String[] messages = new String[2];
            msgService.deleteMsg(msgId);
            List<UserMessage> data = msgService.getMessagesToUserByUsername(userInSession.getUsername());
            if (data.size() == 0) {
                messages[1] = "You have no messages.";
            }
            messages[0] = "Message deleted.";

            Map<String, Object> myModel = new HashMap<>();
            myModel.put("data", data);
            myModel.put("messages", messages);

            return new ModelAndView("messagesView", myModel);
        }
    }

    @RequestMapping(value = "messages", method = {RequestMethod.GET})
    public ModelAndView processGetAllRootRequest(HttpServletRequest request, HttpServletResponse response) {

        String view;
        List<UserMessage> data;
        String[] messages = new String[2];

        try {
            User userInSession = utils.checkIfUserLoggedIn(request);
            if (userInSession == null) {
                return new ModelAndView("redirect:/login");
            }

            data = msgService.getMessagesToUserByUsername(userInSession.getUsername());
            view = "messagesView";
            if (data.size() == 0) {
                messages[1] = "You have no messages.";
                messages[0] = null;
            }

        } catch (Exception exception) {
            view = "error";
            data = null;
            messages[0] = "Error";
        }

        Map<String, Object> myModel = new HashMap<>();
        myModel.put("data", data);
        myModel.put("messages", messages);

        return new ModelAndView(view, myModel);
    }

    @RequestMapping(value = "messages", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {
        return processGetAllRootRequest(request, response);
    }

}
