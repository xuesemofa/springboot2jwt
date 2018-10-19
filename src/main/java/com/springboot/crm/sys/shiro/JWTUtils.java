package com.springboot.crm.sys.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    public static final String SECRET = "ldtoken";

    //    生成token
    public static String creaToken(String token) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create().withHeader(map)
//                qian fa zhe
                .withClaim("iss", "ldtoken")
//                mian xiang de yong hu
                .withClaim("sub", token)
//                jie shou gai jwt fang
                .withClaim("aud", "ldtoken")
                //签发时间
                .withIssuedAt(new Date(System.currentTimeMillis()))
//                过期时间
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 12)))
//                mi yao
                .sign(Algorithm.HMAC256(SECRET));
    }

    //    解密token
    public static Map<String, Claim> verifToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            return null;
        }
        return jwt.getClaims();
    }

    //    获取值
    public static Claim getApp(String token, String k) {
        Map<String, Claim> claimMap = verifToken(token);
        return claimMap.get(k);
    }

    public static String getAccId(HttpServletRequest request){
        String lTokenD = request.getHeader("LTokenD");
        Map<String, Claim> map = JWTUtils.verifToken(lTokenD);
        String sub = map.get("sub").asString();
        return sub;
    }
}
