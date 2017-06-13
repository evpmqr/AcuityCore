package com.acuity.http.service.util;

import com.acuity.http.api.acuity_account.AcuityAccount;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zachary Herridge on 6/6/2017.
 */
public class JwtUtil {

    public static String build(AcuityAccount acuityAccount) throws UnsupportedEncodingException {
        Map<String, Object> headerClaims = new HashMap<>();

        headerClaims.put("email", acuityAccount.getEmail());
        headerClaims.put("displayName", acuityAccount.getDisplayName());


        Algorithm algorithm = Algorithm.HMAC256("tempSecret");

        return JWT.create()
                .withIssuer("auth0")
                .withHeader(headerClaims)
                .sign(algorithm);
    }

}
