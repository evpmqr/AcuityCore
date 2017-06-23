package com.acuity.http.service.util;


import com.acuity.db.entities.AcuityAccount;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Zachary Herridge on 6/6/2017.
 */
public class JwtUtil {

    private static Algorithm algorithm;
    private static JWTVerifier verifier;

    static {
        try {
            algorithm = Algorithm.HMAC256("tempSecret");
            verifier = JWT.require(algorithm).withIssuer("auth0").build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static Optional<DecodedJWT> decode(String token){
        if (token == null) return Optional.empty();
        try {
            return Optional.of(verifier.verify(token));
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
        }
        return Optional.empty();
    }

    public static String build(AcuityAccount acuityAccount){
        Map<String, Object> headerClaims = new HashMap<>();

        headerClaims.put("email", acuityAccount.getEmailAddress());
        headerClaims.put("displayName", acuityAccount.getDisplayName());

        return JWT.create()
                .withIssuer("auth0")
                .withHeader(headerClaims)
                .sign(algorithm);
    }

}
