package com.theptspot.ptspot;

/**
 * Created by Evan on 10/20/2015.
 */
public class UserBuilder {

    private Integer id = null;
    private String firstName = null;
    private String lastName = null;
    private String email = null;
    private Boolean gender = null;
    private String birthDate = null;
    private Integer totalReviews = null;
    private Integer role = null;


    public UserBuilder() {}

    public User buildUser() {
        return new User(id, firstName, lastName, email, birthDate, gender, totalReviews, role);
    }

    public UserBuilder id(Integer _id) {
        this.id = _id;
        return this;
    }

    public UserBuilder firstName(String _firstName) {
        this.firstName = _firstName;
        return this;
    }

    public UserBuilder lastName(String _lastName) {
        this.lastName = _lastName;
        return this;
    }

    public UserBuilder email(String _email) {
        this.email = _email;
        return this;
    }

    public UserBuilder birthDate(String _birthDate) {
        this.birthDate = _birthDate;
        return this;
    }

    public UserBuilder role(Boolean _gender) {
        this.gender = _gender;
        return this;
    }

    public UserBuilder totalReviews(Integer _totalReviews) {
        this.totalReviews = _totalReviews;
        return this;
    }

    public UserBuilder role(Integer _role) {
        this.role = _role;
        return this;
    }
}
