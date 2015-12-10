package com.theptspot.ptspot;

import android.util.Base64;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ebbmf on 10/20/2015.
 */
public class APIRequestBuilder {
    private static final String TAG = APIRequestBuilder.class.getName();
    private HttpURLConnection conn;

    public APIRequestBuilder(String requestPath, String requestMethod) throws IOException {
        initializeConnection(requestPath, requestMethod);
    }

    public APIRequestBuilder setRequestHeader(HashMap<String, String> headerParams) {
        Iterator it = headerParams.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry headerRequest = (Map.Entry) it.next();
            conn.setRequestProperty(headerRequest.getKey().toString(), headerRequest.getValue().toString());
        }
        return this;
    }

    public APIRequestBuilder setRequestBody(HashMap<String, String> bodyParams) throws IOException {
        OutputStream outStream = conn.getOutputStream();

        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(outStream, "UTF-8"));
        writer.write(constructRequestBody(bodyParams));

        writer.flush();
        writer.close();
        outStream.flush();
        outStream.close();

        return this;
    }

    public APIRequestBuilder setAuthorizationHeader(String clientID, String clientSecret) {
        conn.addRequestProperty("Authorization", getB64Auth(clientID, clientSecret));
        return this;
    }

    public String performAPIRequest() throws IOException {
        String response = "";

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = br.readLine()) != null) {
                response += line;
            }
        } else {
            response = "";
        }

        return response;
    }

    private void initializeConnection(String requestPath, String requestMethod) throws IOException {
        URL url;
        url = new URL("http://www.theptspot.com/" + requestPath);

        conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(15000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod(requestMethod);
        conn.setUseCaches(true);
        conn.setDoOutput(true);
        conn.setDoInput(true);
    }

    private static String constructRequestBody(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    private static String getB64Auth(String login, String pass) {
        String source = login + ":" + pass;
        return "Basic " + Base64.encodeToString(source.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
    }

}
