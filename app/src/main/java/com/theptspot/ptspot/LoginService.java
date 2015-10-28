package com.theptspot.ptspot;

import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpCookie;
import java.util.HashMap;

/**
 * Created by ebbmf on 10/20/2015.
 */
public class LoginService {
    private String url;
    private HashMap<String, String> loginCredentials;
    private static final String TAG = LoginService.class.getName();

    public LoginService(String email, String password) {
        url = "http://www.theptspot.com/api/login";
        loginCredentials = new HashMap<>();
        loginCredentials.put("loginEmail", email);
        loginCredentials.put("loginPassword", password);
    }

    // Throws Exception if there is a problem getting id
    public void login() throws Exception {
        try {
            JSONObject userData = APIService.getJSONObject(url, loginCredentials);
            HttpCookie httpCookie = new HttpCookie("ptspot", userData.toString());
            Log.i(TAG, httpCookie.toString());
            //Log.i(TAG, userData.toString());
            //Log.i(TAG, httpCookie.getDomain());

        } catch (JSONException e) {
            e.printStackTrace();
            throw new Exception("Problem in LoginService.login");
        }
    }

}
