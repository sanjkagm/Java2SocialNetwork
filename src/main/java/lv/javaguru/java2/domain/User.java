package lv.javaguru.java2.domain;


import lv.javaguru.java2.validators.Years18;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

    private Long userId;

    @NotNull(message="Имя должно быть задано")
    private String firstName;
    @NotNull(message="Фамилия должна быть задана")
    @Size(min = 3, message="Длина фамилии должна быть больше трех")
    private String lastName;
    @NotNull(message="Username должнен быть задан")
    @Size(min = 3, message="Длина username должна быть больше трех")
    private String username;

    @NotNull(message="Пароль должен быть задан.")
    @Size(min = 5, message="Длина пароля должна быть больше пяти")
    private String password;

    @NotNull(message="Дата рождения должна быть задана")
    @Pattern(regexp = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])",
            message = "неверная дата")
    @Years18(message="Возраст должен превышать 18 лет")
    private String date_of_birth;


    private String city;
    private String country;

    @NotNull(message="Без пола нельзя")
    private String sex;

    @NotNull(message="Кого ищете сударь ?")
    private String looking_for;


    private Integer age_from;
    private Integer age_to;


    private String about;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!User.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final User other = (User) obj;
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        if (this.userId != null) {
            if (!this.userId.equals(other.userId)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 53 * hash + (this.userId != null ? this.userId.intValue() : 0);
        return hash;
    }



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
}
