package com.chiyun.voting.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {


    // 过期时间15分钟
    private static final long EXPIRE_TIME = 15 * 60 * 1000;
    //    私钥
    private static final String TOKRN_SECRET = "265B23D023B735BBE05000017F0029F7";

    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKRN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


    /**
     * 生成签名,5min后过期
     *
     * @param username 用户名
     * @return 加密的token
     */
    public static String sign(String username, int userid, int role) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(TOKRN_SECRET);
            Map<String, Object> hearder = new HashMap<>(2);
            hearder.put("typ", "JWT");
            hearder.put("alg", "HS256");
            return JWT.create()
                    .withHeader(hearder)
                    .withClaim("uname", username)
                    .withClaim("uid", userid)
                    .withClaim("role", role)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("uname").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * 获取token中的用户id
     */
    public static Integer getUId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("uid").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取token中的用户角色
     */
    public static Integer getRole(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("role").asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
