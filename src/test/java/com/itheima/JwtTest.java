package com.itheima;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.logging.log4j.util.Base64Util;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JwtTest {
    @Test
    public void test() {
        HashMap<String, String> claims = new HashMap<>();
        claims.put("id", "1");
        claims.put("username", "tomcat");

        String sign = JWT.create()
                .withClaim("user", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .sign(Algorithm.HMAC256("itheima"));
        System.out.println(sign.toString());
    }

    @Test
    public void test2() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiMSIsInVzZXJuYW1lIjoidG9tY2F0In0sImV4cCI6MTcxNjM0NTAwOX0.EYr57CYragrPHfosAPUPARdpym8x28fLZtUSv2qBBMk";
        JWTVerifier itheima = JWT.require(Algorithm.HMAC256("itheima")).build();
        DecodedJWT verify = itheima.verify(token);
        String header = verify.getHeader();
        Map<String, Claim> claims = verify.getClaims();
        System.out.println(claims.get("user"));


    }
}
