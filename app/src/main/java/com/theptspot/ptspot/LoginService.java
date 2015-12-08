package com.theptspot.ptspot;

import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * Created by ebbmf on 10/20/2015.
 */
public class LoginService {
    private static final String TAG = LoginService.class.getName();

    private HashMap<String, String> loginCredentials;
    private String clientID, clientSecret;
    private APIService apiService;

    public LoginService(String email, String password, String clientID, String clientSecret) throws IOException {

        this.clientID = clientID;
        this.clientSecret = clientSecret;

        loginCredentials = new HashMap<>();
        loginCredentials.put("grant_type","password");
        loginCredentials.put("username", email);
        loginCredentials.put("password", password);

        apiService = new APIService("account/login", "POST");
    }

    public void login() throws IOException, JSONException, URISyntaxException {
        HashMap<String, String> headerParams  = new HashMap<>();
        headerParams.put("Content-Type", "application/x-www-form-urlencoded");

        apiService.setRequestHeader(headerParams);
        apiService.setAuthorizationHeader(clientID, clientSecret);
        apiService.setRequestBody(loginCredentials);
        String accessToken = apiService.performAPIRequest();

        //retrieve access token and return it
        //return apiService.performAPIRequest();
        String stringURL = "http://www.theptspot.com/";
        URL url = new URL(stringURL);
        URLConnection urlConnection = url.openConnection();
        urlConnection.getContent();



        HttpCookie cookie = new HttpCookie("accessToken", accessToken);

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
        CookieStore cookieStore = cookieManager.getCookieStore();
        cookieStore.add(url.toURI(), cookie);

        Log.i(TAG, cookie.toString());
        Log.i(TAG, cookieStore.getCookies().toString());
    }



}
