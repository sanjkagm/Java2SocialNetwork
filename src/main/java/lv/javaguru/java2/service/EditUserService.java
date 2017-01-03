package lv.javaguru.java2.service;


/**
 * Created by Pavel on 15.11.2016..
 */

public interface EditUserService{

    String updatePassword(String password,String password_repeat,String userIdStr);

    String updateUserInfo(String userIdStr,
                          String username,
                          String firstName,
                          String lastName,
                          String city,
                          String country,
                          String looking_for,
                          String age_fromStr,
                          String age_toStr,
                          String about,
                          String sex,
                          String date_of_birth,
                          String currentPassword);

}
