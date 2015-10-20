package com.theptspot.ptspot;

import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by ebbmf on 10/20/2015.
 */
public class LoginService {
    private String url;
    private HashMap<String, String> loginCredentials;
    private static final String TAG = LoginService.class.getName();

    // cityName is city,state. For example: Leawood,KS or Kansas%20City,MO
    public LoginService(String email, String password) {
        url = "http://www.theptspot.com/api/login";
        loginCredentials = new HashMap<>();
        loginCredentials.put("loginEmail", email);
        loginCredentials.put("loginPassword", password);
    }

    // Throws Exception if there is a problem getting temperature
    public String getId() throws Exception {
        try {
            JSONObject userData = APIService.getJSONObject(url, loginCredentials);
            Log.i(TAG, userData.toString());
            //JSONObject mainSection = userData.getJSONObject("main");
            String id = userData.getString("userId");
            return id;

        } catch (JSONException e) {
            e.printStackTrace();
            throw new Exception("Problem in LoginService.getId");
        }
    }

}
