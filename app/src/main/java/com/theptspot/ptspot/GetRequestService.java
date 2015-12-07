package com.theptspot.ptspot;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Evan on 12/2/2015.
 */
public class GetRequestService {
    private APIService apiService;

    public GetRequestService(String requestPath) throws IOException {
        apiService = new APIService("api/" + requestPath, "GET");
    }

    public JSONArray performAPIRequest() throws JSONException, IOException {
        return new JSONArray(apiService.performAPIRequest());
    }
}
