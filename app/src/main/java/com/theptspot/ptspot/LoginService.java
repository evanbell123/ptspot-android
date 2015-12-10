package com.theptspot.ptspot;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * Created by ebbmf on 10/20/2015.
 */
public class LoginService {
    private static final String TAG = LoginService.class.getName();

    private HashMap<String, String> loginCredentials;
    private String clientID, clientSecret;
    private APIRequestBuilder apiRequestBuilder;

    public LoginService(String email, String password, String clientID, String clientSecret) throws IOException {

        this.clientID = clientID;
        this.clientSecret = clientSecret;

        loginCredentials = new HashMap<>();
        loginCredentials.put("grant_type", "password");
        loginCredentials.put("username", email);
        loginCredentials.put("password", password);

        apiRequestBuilder = new APIRequestBuilder("account/login", "POST");
    }

    public JSONObject login() throws IOException, JSONException, URISyntaxException {
        HashMap<String, String> headerParams = new HashMap<>();
        headerParams.put("Content-Type", "application/x-www-form-urlencoded");

        //apiRequestBuilder.setRequestHeader(headerParams);
        //apiRequestBuilder.setAuthorizationHeader(clientID, clientSecret);
        //apiRequestBuilder.setRequestBody(loginCredentials);
        String stringToken = apiRequestBuilder
                .setRequestHeader(headerParams)
                .setAuthorizationHeader(clientID, clientSecret)
                .setRequestBody(loginCredentials)
                .performAPIRequest();
        JSONObject accessToken = new JSONObject(stringToken);

        return accessToken;

    }
}
