package lv.javaguru.java2.domain;

public class UserBuilder {

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


    private UserBuilder() {}

    public static UserBuilder createUser() {
        return new UserBuilder();
    }

    public User build() {
        User user = new User();
        user.setUserId(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setDate_of_birth(date_of_birth);
        user.setCity(city);
        user.setCountry(country);
        user.setSex(sex);
        user.setLooking_for(looking_for);
        user.setAge_from(age_from);
        user.setAge_to(age_to);
        user.setAbout(about);

        return user;
    }

    public UserBuilder withUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }
    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }
    public UserBuilder withDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
        return this;
    }
    public UserBuilder withCity(String city) {
        this.city = city;
        return this;
    }
    public UserBuilder withCountry(String country) {
        this.country = country;
        return this;
    }
    public UserBuilder withSex(String sex) {
        this.sex = sex;
        return this;
    }
    public UserBuilder withLooking_for(String looking_for) {
        this.looking_for = looking_for;
        return this;
    }
    public UserBuilder withAge_from(Integer age_from) {
        this.age_from = age_from;
        return this;
    }
    public UserBuilder withAge_to(Integer age_to) {
        this.age_to = age_to;
        return this;
    }
    public UserBuilder withAbout(String about) {
        this.about = about;
        return this;
    }
}
