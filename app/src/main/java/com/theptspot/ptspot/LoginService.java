package com.theptspot.ptspot;

import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.HashMap;

/**
 * Created by ebbmf on 10/20/2015.
 */
public class LoginService {
    private static final String TAG = LoginService.class.getName();

    private HashMap<String, String> loginCredentials;
    String clientID, clientSecret;
    APIService apiService;

    public LoginService(String email, String password, String clientID, String clientSecret) throws IOException {

        this.clientID = clientID;
        this.clientSecret = clientSecret;

        loginCredentials = new HashMap<>();
        loginCredentials.put("grant_type","password");
        loginCredentials.put("username", email);
        loginCredentials.put("password", password);

        apiService = new APIService("account/login", "POST");
    }

    public void login() throws IOException, JSONException {
        HashMap<String, String> headerParams  = new HashMap<>();
        headerParams.put("Content-Type", "application/x-www-form-urlencoded");

        apiService.setRequestHeader(headerParams);
        apiService.setAuthorizationHeader(clientID, clientSecret);
        apiService.setRequestBody(loginCredentials);

        //retrieve access token and return it
        //return apiService.performAPIRequest();

        HttpCookie cookie = new HttpCookie("accessToken", apiService.performAPIRequest());

        Log.i(TAG, cookie.toString());
    }



}
