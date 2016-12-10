package lv.javaguru.java2.servlet;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.AddUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Pavel on 06.11.2016..
 */

@Controller
public class DoAddUserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AddUserService addUserService;

    @RequestMapping(value = "doAddUser", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("addUserView", "model", null);
    }

    @RequestMapping(value = "doAddUser", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) {

        try {


            User user = null;
            String[] messages = new String[2];

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String password_repeat = request.getParameter("password_repeat");
            String date_of_birth = request.getParameter("date_of_birth");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String city = request.getParameter("city");
            String country = request.getParameter("country");
            String sex = request.getParameter("sex");
            String looking_for = request.getParameter("looking_for");
            String age_fromStr = request.getParameter("age_from");
            String age_toStr = request.getParameter("age_to");
            String about = request.getParameter("about");


            String resultOfRegistration = addUserService.register(username, password, password_repeat, date_of_birth, firstName, lastName, sex, city, country, looking_for, age_fromStr, age_toStr, about);

            // If error, forward to Edit page with pre-entered data.
            if (resultOfRegistration != null) {
                user = addUserService.createUserByBuilder("0", username, password, date_of_birth, firstName, lastName, sex, city, country, looking_for, age_fromStr, age_toStr, about);
                messages[0] = resultOfRegistration;
                messages[1] = null;
            }
            // If everything nice.
            // Display success message.
            else {
                messages[0] = null;
                messages[1] = "User successfully created! Now you can Login";
            }

            Map<String, Object> myModel = new HashMap<>();
            myModel.put("data", user);
            myModel.put("messages", messages);

            return new ModelAndView("addUserView", myModel);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

}