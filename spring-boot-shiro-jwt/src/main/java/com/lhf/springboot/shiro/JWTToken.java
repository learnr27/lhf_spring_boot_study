package com.lhf.springboot.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @ClassName: JWTToken
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/5/24 16:09
 */
public class JWTToken implements AuthenticationToken {
    private String token;

    public JWTToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
