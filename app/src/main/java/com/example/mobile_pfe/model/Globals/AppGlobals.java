package com.example.mobile_pfe.model.Globals;

public class AppGlobals {
    public static String accessToken;

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        AppGlobals.accessToken = accessToken;
    }
}
