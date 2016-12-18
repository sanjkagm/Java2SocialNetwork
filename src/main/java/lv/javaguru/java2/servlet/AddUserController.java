package lv.javaguru.java2.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Pavel on 06.11.2016..
 */
@Controller
public class AddUserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "addUser", method = { RequestMethod.POST })
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        return processGetRequest(request, response);
    }

    @RequestMapping(value = "addUser", method = { RequestMethod.GET })
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {

        /*logger.debug("Here is some DEBUG");
        logger.info("Here is some INFO");
        logger.warn("Here is some WARN");
        logger.error("Here is some ERROR");
        logger.trace("Here is some TRACE");*/

        return new ModelAndView("addUserView", "model", null);
    }

}
