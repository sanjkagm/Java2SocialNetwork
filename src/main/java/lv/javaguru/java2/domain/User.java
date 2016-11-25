package lv.javaguru.java2.domain;


import javax.validation.constraints.NotNull;

public class User {

    private Long userId;
    private String firstName;
    private String lastName;
    @NotNull
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
        if (!this.userId.equals(other.userId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 53 * hash + this.userId.intValue();
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
