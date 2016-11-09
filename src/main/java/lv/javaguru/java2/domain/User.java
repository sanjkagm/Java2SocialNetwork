package lv.javaguru.java2.domain;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class User {

    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String date_of_birth;
    private String city;
    private String country;
    private String sex;
    private String looking_for;
    private Integer age_from;
    private Integer age_to;
    private String about;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) { this.city = city; }

    public String getDate_of_birth() {
        return date_of_birth;
    }
    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) { this.sex = sex; }

    public String getLooking_for() {
        return looking_for;
    }
    public void setLooking_for(String looking_for) {
        this.looking_for = looking_for;
    }

    public Integer getAge_from() { return age_from; }
    public void setAge_from(Integer age_from) { this.age_from = age_from; }

    public Integer getAge_to() {
        return age_to;
    }
    public void setAge_to(Integer age_to) { this.age_to = age_to; }

    public String getAbout() {
        return about;
    }
    public void setAbout(String about) { this.about = about; }



    // Store user info in Session.
    public static void storeLoginedUser(HttpSession session, User loginedUser) {

        // On the JSP can access ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }


    // Get the user information stored in the session.
    public static User getLoginedUser(HttpSession session) {
        User loginedUser = (User) session.getAttribute("loginedUser");
        return loginedUser;
    }


    // Store info in Cookie
    public static void storeUserCookie(HttpServletResponse response, User user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUsername());

        // 1 day (Convert to seconds)
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }

    public static String getUserNameInCookie(HttpServletRequest request) {
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


    // Delete cookie.
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        System.out.println("Delete user cookie");
        // 0 seconds (Expires immediately)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }

    public static boolean isEmpty(final String string) {
        return string == null || string.trim().isEmpty();
    }
}
