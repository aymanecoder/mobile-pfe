package com.example.mobile_pfe.model.Globals;

import android.util.Log;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;

import java.util.Collections;
import java.util.List;

public class AppGlobals {
    public static String accessToken;

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        AppGlobals.email = email;
    }

    public static String email;
    public static Integer sportId;
    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken)
    {

        AppGlobals.accessToken = accessToken;
    }


    public static Integer getSportId() {
        return sportId;
    }

    public static void setSportId(Integer sportId)
    {
        Log.d("sportid",""+sportId);
        AppGlobals.sportId = sportId;
    }


    public static List<String> extractUserRoles() {
        try {
            JWT jwt = JWTParser.parse(accessToken);
            JWTClaimsSet claimsSet = jwt.getJWTClaimsSet();

            // Assuming your role claim is named "roles"
            Object rolesClaim = claimsSet.getClaim("roles");
            Log.wtf("roles", rolesClaim + "");

            if (rolesClaim instanceof List) {
                // Cast the rolesClaim to List<String>
                List<String> roles = (List<String>) rolesClaim;
                Log.wtf("roles", roles + "");
                return roles;
            }
            else {
                // Handle if roles claim is neither a list nor a string (adjust as needed)



                return Collections.singletonList("USER");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle parsing or other exceptions
            return Collections.singletonList("USER");
        }
    }

}
