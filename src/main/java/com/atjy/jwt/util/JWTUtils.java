package com.atjy.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.codec.binary.StringUtils;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {

    private static final String SING = "abc&*(defghi!~@jklmno#$%^pqrst:,.uvwxyz#$%^)_+-=`[]{};'<>/?|";

    public static String getToken(Map<String, String> map) {

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        String token = builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SING));

        return token;
    }


    public static DecodedJWT verify(String token) {

        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);

        /*
             JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("@#@!FSKJL@!")).build();
            DecodedJWT verify = jwtVerifier.verify();
        * */

    }
}
