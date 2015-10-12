package com.theptspot.ptspot;

import org.json.JSONArray;

import java.sql.Blob;

/**
 * Created by ebbmf on 10/12/2015.
 */
public class User {
    Integer id, totalReviews, role;
    String firstName, lastName, email, passwordHash, birthDate, photoType;
    Boolean gender, activated;
    Blob photo;

    public User(Integer id, String firstName, String lastName, String email, String passwordHash, Boolean gender, String birthDate, Integer totalReviews, Blob photo, String photoType, Integer role, Boolean activated) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.gender = gender;
        this.birthDate = birthDate;
        this.totalReviews = totalReviews;
        this.photo = photo;
        this.photoType = photoType;
        this.role = role;
        this.activated = activated;
    }

    public User(JSONArray info) {
        this.id = info.getInt("id");
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.gender = gender;
        this.birthDate = birthDate;
        this.totalReviews = totalReviews;
        this.photo = photo;
        this.photoType = photoType;
        this.role = role;
        this.activated = activated;
    }
}
