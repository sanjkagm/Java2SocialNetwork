package lv.javaguru.java2.service;

import lv.javaguru.java2.domain.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static lv.javaguru.java2.domain.UserBuilder.createUser;

/**
 * Created by Pavel on 15.11.2016..
 */
public class Utils {

    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    public User checkIfUserLoggedIn(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (User) session.getAttribute("loginedUser");
    }

    public void storeLoggedUserInSession(HttpServletRequest request, User loginedUser) {
        HttpSession session = request.getSession();
        // On the JSP can access ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
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

        return createUser()
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
}
