package com.titos.tool.token;

import io.jsonwebtoken.*;
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
     * 私有方法防止构造
     */
    private TokenUtil(){}

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

    /**
     * TODO：key写死了
     * @param token 传入token
     * @return
     * @throws ExpiredJwtException 抛出失效异常
     */
    public static Integer verifyTokenAndGetUserId(String token) throws ExpiredJwtException {
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey("YUNKE")
                .parseClaimsJws(token);
        return (Integer) jws.getBody().get("id");
    }

    /**
     *
     * @param token 传入token
     * @return 返回用户角色编号
     * @throws ExpiredJwtException 抛出失效异常
     */
    public static Integer verifyTokenAndGetUserRole(String token) throws ExpiredJwtException {
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey("YUNKE")
                .parseClaimsJws(token);
        return (Integer) jws.getBody().get("role");
    }

    /**
     *
     * @param token 传入token
     * @return 返回用户名称
     * @throws ExpiredJwtException
     */
    public static String verifyTokenAndGetUserName(String token) throws ExpiredJwtException {
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey("YUNKE")
                .parseClaimsJws(token);
        return (String) jws.getBody().get("username");
    }

}
