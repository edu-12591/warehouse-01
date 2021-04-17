package com.atjy.jwt.test;

import com.atjy.jwt.util.JWTUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SpringBootJWTApplicationTests {

    @Test
    public void JWTLoadsTest() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 40);
        String token = JWT.create()
                .withClaim("username", "jinyu")
                .withClaim("userId", 123)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256("@#@!FSKJL@!"));//签名
        System.out.println(token);
    }

    @Test
    public void Test() {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("@#@!FSKJL@!")).build();
            DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTc4NTExNzgsInVzZXJJZCI6MTIzLCJ1c2VybmFtZSI6Imppbnl1In0.8L8iBN1egpBDIVDPRywUZgTlbhi0Tij6nomtln6_C28");
        String username = verify.getClaim("username").asString();
         Integer userId = verify.getClaim("userId").asInt();
        System.out.println(username + userId);

    }
    @Test
    public  void Test01(){
        Map<String,String> map  = new HashMap<String, String>();
        map.put("username","jinyu");
        map.put("userid","1234125");

        String token = JWTUtils.getToken(map);
        System.out.println(token);
    }
}



