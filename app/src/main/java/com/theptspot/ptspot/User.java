package com.theptspot.ptspot;

/**
 * Created by ebbmf on 10/12/2015.
 */
public class User {
    public Integer getId() {
        return id;
    }

    public Integer getTotalReviews() {
        return totalReviews;
    }

    public Integer getRole() {
        return role;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Boolean getGender() {
        return gender;
    }

    private Integer id, totalReviews, role;
    private String firstName, lastName, email, password, birthDate;
    private Boolean gender;

    public User(String firstName, String lastName, String email, String password, String birthDate, Boolean gender, Integer role) {
        this.id = -1;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.password = "";
        this.gender = false;
        this.birthDate = "";
        this.totalReviews = -1;
        this.role = -1;
    }

    public User(Integer id, String firstName, String lastName, String email, String password, Boolean gender, String birthDate, Integer totalReviews, Integer role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.totalReviews = totalReviews;
        this.role = role;
    }

    public User(String firstName, String lastName, String email, String birthDate, Boolean gender, Integer role) {
        this.id = -1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = "";
        this.gender = gender;
        this.birthDate = birthDate;
        this.totalReviews = 0;
        this.role = role;
    }

    public User(String firstName, String lastName, String email, String birthDate) {
        this.id = -1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = "";
        this.gender = false;
        this.birthDate = birthDate;
        this.totalReviews = 0;
        this.role = -1;
    }

    public User(String email, String password) {
        this.id = -1;
        this.firstName = "";
        this.lastName = "";
        this.email = email;
        this.password = password;
        this.gender = false;
        this.birthDate = "";
        this.totalReviews = 0;
        this.role = -1;
    }


}
