package com.theptspot.ptspot;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by ebbmf on 10/20/2015.
 */
class LoginService {
    private static final String TAG = LoginService.class.getName();

    private final HashMap<String, String> loginCredentials;
    private final String clientID;
    private final String clientSecret;
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

    public JSONObject login() throws IOException, JSONException {
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

        return new JSONObject(stringToken);

    }
}
