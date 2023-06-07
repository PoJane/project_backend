package com.ling.project_backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ling.project_backend.domain.User;
import java.util.Date;

public class TokenUtil {

    //token过期时间
    public static final long EXPIRE_TIME=15*60*1000;
    //密钥盐值
    private static final String TOKEN_SECRET="password";

    /**
     * Token签名生成
     * @param user
     * @return
     */
    public static String sign(User user){
        String token=null;
        try{
            Date expiresAt=new Date(System.currentTimeMillis()+EXPIRE_TIME);
            token= JWT.create()
                    .withIssuer("auth0")
                    .withClaim("userId",user.getUserId())
                    .withExpiresAt(expiresAt)
                    //HMAC256加密
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    /**
     * Token签名认证
     * @param token
     * @return
     */
    public static boolean verify(String token){
        try{
            JWTVerifier verifier=JWT.require(Algorithm.HMAC256(TOKEN_SECRET))
                    .withIssuer("auth0").build();
            DecodedJWT jwt=verifier.verify(token);
            System.out.println("Verify Pass:");
            System.out.println("issuer: "+jwt.getIssuer());
            System.out.println("userId: "+jwt.getClaim("userId").asString());
            System.out.println("Expire At: "+jwt.getExpiresAt());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
