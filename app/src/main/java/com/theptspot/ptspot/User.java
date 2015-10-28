package com.theptspot.ptspot;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ebbmf on 10/12/2015.
 */
public class User {

    private Integer id = null;
    private String firstName = null;
    private String lastName = null;
    private String email = null;
    private String birthDate = null;
    private Integer totalReviews = null;
    private Boolean gender = null;
    private Integer role = null;

    public User(Integer id, String firstName, String lastName, String email, String birthDate, Boolean gender, Integer totalReviews, Integer role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
        this.gender = gender;
        this.birthDate = birthDate;
        this.totalReviews = -1;
        this.role = role;
    }

    public Integer getID() {
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

    public String getEmail() { return email; }

    public String getBirthDate() {
        return birthDate;
    }

    public Boolean getGender() {
        return gender;
    }

}