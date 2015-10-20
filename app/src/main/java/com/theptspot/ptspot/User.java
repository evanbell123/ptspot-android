package com.theptspot.ptspot;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ebbmf on 10/12/2015.
 */
public class User {

    private Integer id, totalReviews, role;
    private String firstName, lastName, email, birthDate;
    private Boolean gender;


    public User(JSONObject userData) throws JSONException{
        this.id = userData.getInt("id");
        this.firstName = userData.getString("firstName");
        this.lastName = userData.getString("lastName");
        this.email = userData.getString("email");
        this.gender = userData.getBoolean("email");
        this.birthDate = userData.getString("birthDate");
        this.totalReviews = userData.getInt("totalReviews");
        this.role = userData.getInt("role");
    }

    public User(Integer id, String firstName, String lastName, String email, Boolean gender, String birthDate, Integer totalReviews, Integer role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
        this.totalReviews = totalReviews;
        this.role = role;
    }

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

    public String getEmail() { return email; }

    public String getBirthDate() {
        return birthDate;
    }

    public Boolean getGender() {
        return gender;
    }

}
