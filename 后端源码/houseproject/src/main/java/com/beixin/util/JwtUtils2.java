package com.beixin.util;


import com.beixin.model.AdminInfo;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import lombok.Data;

import java.util.Date;


@Data
public class JwtUtils2 {
    //签名私钥
    private static String key="kappy";
    //签名失效时间 60 分钟
    private static Long failureTime=60 * 60 * 1000L;
    /**
     * 加密生产token
     * @param user
     * @return
     */
    public static String geneJsonWebToken(AdminInfo user){
        if(user==null||user.getId()==null||user.getUsername()==null){
            return null;
        }

        String token=Jwts.builder()
                .claim("id",user.getId())
                .claim("username",user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+failureTime))
                .signWith(SignatureAlgorithm.HS256,key).compact();

        return token ;
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static  Claims checkJWT(String token ) {

        try {
            final Claims claims = Jwts.parser().setSigningKey(key).
                    parseClaimsJws(token).getBody();
            return claims;

        } catch (Exception e) {
        }
        return null;

    }

    /**
     * 获取用户ID
     * @param
     * @return
     */
    public static Integer getUserId(String token){
        Claims claims=JwtUtils2.checkJWT(token);
        return (Integer)claims.get("id");
    }

    /**
     * 获取用户名
     * @param
     * @return
     */
    public static String getUserName(String token){
        Claims claims=JwtUtils2.checkJWT(token);
        return (String)claims.get("username");
    }


    /*
     * Token 是否过期验证
     */
    public static boolean isTokenExpired (Date expirationTime) {
        return expirationTime.before(new Date());
    }
}
