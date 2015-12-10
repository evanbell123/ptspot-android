package com.theptspot.ptspot;

import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URI;

/**
 * Created by Evan on 12/9/2015.
 */
public class PTCookiePolicy implements CookiePolicy {
    private static final String domain = "http://www.theptspot.com";
    private static final String path = "/";

    @Override
    public boolean shouldAccept(URI uri, HttpCookie cookie) {
        if (uri.getHost() == domain && uri.getPath() == path) {
            return true;
        }
        return false;
    }


}
