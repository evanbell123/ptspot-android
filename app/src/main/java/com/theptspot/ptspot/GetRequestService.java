package com.theptspot.ptspot;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Evan on 12/2/2015.
 */
public class GetRequestService {
    private String url;

    public GetRequestService() {
        url = "http://www.theptspot.com/api/";
    }

    public JSONArray getTrainerResults() throws JSONException {
        return APIService.performGetRequest(url + "search");
    }
}
