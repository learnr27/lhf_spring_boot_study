package com.lhf.springboot.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @ClassName: JWTUtil
 * @Author: liuhefei
 * @Description: JWT工具类
 * @Date: 2019/5/24 15:17
 */
public class JWTUtil {

    // 过期时间 24 小时
    private static final long EXPIRE_TIME = 60 * 24 * 60 * 1000;
    // 密钥
    private static final String SECRET = "SHIRO+JWT";

    /**
     * 生成 token, 5min后过期
     *
     * @param username 用户名
     * @return 加密的token
     */
    public static String createToken(String username){
        try{
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //附带user信息
            return JWT.create().withClaim("username", username)
                    //到期时间
                   .withExpiresAt(date)
                    //创建一个新的JWT，并使用给定的算法进行标记
                    .sign(algorithm);
        }catch (UnsupportedEncodingException e){
            return null;
        }
    }

    /**
     * 校验 token 是否正确
     *
     * @param token    密钥
     * @param username 用户名
     * @return 是否正确
     */
    public static boolean verify(String token, String username){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withArrayClaim("username", username)
                    .build();
            //验证token
            verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
