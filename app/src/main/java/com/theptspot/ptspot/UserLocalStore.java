package com.theptspot.ptspot;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Evan on 10/18/2015.
 */
public class UserLocalStore {
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putInt("id", user.getId());
        spEditor.putString("firstName", user.getFirstName());
        spEditor.putString("lastName", user.getLastName());
        spEditor.putString("email", user.getEmail());
        spEditor.putBoolean("gender", user.getGender());
        spEditor.putString("birthDate", user.getBirthDate());
        spEditor.putInt("totalReviews", user.getTotalReviews());
        spEditor.putInt("role", user.getRole());
        spEditor.commit();
    }

    public User getLoggedInUser() {
        Integer id = userLocalDatabase.getInt("id", Integer.parseInt(null));
        String firstName = userLocalDatabase.getString("firstName", null);
        String lastName = userLocalDatabase.getString("lastName", null);
        String email = userLocalDatabase.getString("email", null);
        Boolean gender = userLocalDatabase.getBoolean("gender", Boolean.parseBoolean(null));
        String birthDate = userLocalDatabase.getString("birthDate", null);
        Integer totalReviews = userLocalDatabase.getInt("totalReviews", Integer.parseInt(null));
        Integer role = userLocalDatabase.getInt("role", Integer.parseInt(null));

        User storedUser = new User(id, firstName, lastName, email, birthDate, gender, totalReviews, role);
        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == true) {
            return true;
        } else {
            return false;
        }
    }

    public void clearUserData() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
