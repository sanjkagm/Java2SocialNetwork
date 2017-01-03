package lv.javaguru.java2.servlet;

import lv.javaguru.java2.domain.User;
import lv.javaguru.java2.service.AddUserService;
import lv.javaguru.java2.service.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Pavel on 06.11.2016..
 */

@Controller
public class DoAddUserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AddUserService aS;
    @Autowired
    private Utils utils;


    @RequestMapping(value = "doAddUser", method = {RequestMethod.GET})
    public ModelAndView processGetRequest(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("addUserView", "model", null);
    }

    @RequestMapping(value = "doAddUser", method = {RequestMethod.POST})
    public ModelAndView processPostRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String[] messages = new String[2];

            User user = new User();
            getUserDataFromInput( user, request );

            String resultOfRegistration = aS.validatePasswords( request.getParameter("password"), request.getParameter("password_repeat") );
            if (resultOfRegistration == null) {// If passwords match
                resultOfRegistration = aS.register( user );
            }

            // If error, forward to Edit page with pre-entered data.
            if (resultOfRegistration != null) {
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


    private void getUserDataFromInput (User user, HttpServletRequest request) throws IOException{

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        user.setUsername(username);
        String password = request.getParameter("password");
        user.setPassword(password);
        String date_of_birth = request.getParameter("date_of_birth");
        user.setDate_of_birth(date_of_birth);
        String firstName = request.getParameter("firstName");
        user.setFirstName(firstName);
        String lastName = request.getParameter("lastName");
        user.setLastName(lastName);
        String city = request.getParameter("city");
        user.setCity(city);
        String country = request.getParameter("country");
        user.setCountry(country);
        String sex = request.getParameter("sex");
        user.setSex(sex);
        String looking_for = request.getParameter("looking_for");
        user.setLooking_for(looking_for);
        String age_fromStr = request.getParameter("age_from");
        user.setAge_from(utils.stringToInteger(age_fromStr));
        String age_toStr = request.getParameter("age_to");
        user.setAge_to(utils.stringToInteger(age_toStr));
        String about = request.getParameter("about");
        user.setAbout(about);
    }

}