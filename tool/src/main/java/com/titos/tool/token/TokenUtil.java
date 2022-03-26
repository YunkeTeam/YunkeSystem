package com.titos.tool.token;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token工具类
 * @author Titos
 */
public class TokenUtil {
    /**
     * 生成token
     * @param tokenContent 生成token需要用到的信息
     * @return token
     */
    public static String buildToken(TokenContent tokenContent) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", tokenContent.getCustomStatement().getId());
        claims.put("role", tokenContent.getCustomStatement().getRole());
        claims.put("username", tokenContent.getCustomStatement().getUsername());
        // 获取当前的时间
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.MILLISECOND, tokenContent.getMilliSecond());
        // token过期时间
        Date expireDate = calendar.getTime();
        String jwtToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .setIssuer(tokenContent.getJwtIssuer())
                .setAudience(tokenContent.getJwtAud())
                .signWith(SignatureAlgorithm.HS256, tokenContent.getSecretKey())
                .compact();
        return jwtToken;
    }
}
