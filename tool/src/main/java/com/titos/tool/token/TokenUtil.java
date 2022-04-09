package com.titos.tool.token;

import com.titos.tool.exception.JwtExpireException;
import com.titos.tool.exception.JwtNotExistException;
import com.titos.tool.exception.JwtVerifyException;
import io.jsonwebtoken.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token工具类
 * @author Titos
 */
public class TokenUtil {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String ID = "id";
    public static final String ROLE = "role";
    public static final String USERNAME = "username";

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
     * 从toke中获取用户的自定义变量信息
     * @param request http请求
     * @param secretKey 密钥
     * @return 用户在token中的自定义信息
     */
    public static CustomStatement getMsgFromToken(HttpServletRequest request, String secretKey) throws JwtExpireException, JwtVerifyException , JwtNotExistException{
        String token = getToken(request);
        CustomStatement customStatement = new CustomStatement();
        customStatement.setId((Integer) getTokenValueByKey(token, secretKey, ID));
        customStatement.setRole((Integer) getTokenValueByKey(token, secretKey, ROLE));
        customStatement.setUsername((String) getTokenValueByKey(token, secretKey, USERNAME));
        return customStatement;
    }

    /**
     * 从toke中获取用户的自定义变量信息
     * @param token token值
     * @param secretKey 密钥
     * @return 用户在token中的自定义信息
     */
    public static CustomStatement getMsgFromToken(String token, String secretKey) throws JwtExpireException, JwtVerifyException , JwtNotExistException{
        CustomStatement customStatement = new CustomStatement();
        customStatement.setId((Integer) getTokenValueByKey(token, secretKey, ID));
        customStatement.setRole((Integer) getTokenValueByKey(token, secretKey, ROLE));
        customStatement.setUsername((String) getTokenValueByKey(token, secretKey, USERNAME));
        return customStatement;
    }

    /**
     * 根据Token中的键获取对应的值
     * @param jwt token
     * @param secretKey 密钥
     * @param key 键
     * @return 值
     */
    public static Object getTokenValueByKey(String jwt, String secretKey, String key) {
        Claims claims = parse(jwt, secretKey);
        if (claims == null || claims.get(key) == null) {
            return null;
        }
        return claims.get(key);
    }

    /**
     * 将token字符串解析为Jws对象，然后获取claims
     * @param jwt 前端头部的携带的token值
     * @param secretKey 密钥
     * @return
     */
    public static Claims parse(String jwt, String secretKey) throws JwtExpireException, JwtVerifyException , JwtNotExistException  {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
        } catch (ExpiredJwtException expiredJwtException) {
            throw new JwtExpireException("token过期");
        } catch (JwtException jwtException) {
            throw new JwtVerifyException("JWT验证错误");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new JwtNotExistException("Token为空");
        }
        return claims;
    }

    /**
     * 从http请求的Cookie中获取token
     * @param request http请求
     * @return token值
     */
    public static String getToken(HttpServletRequest request) {
        String token =request.getHeader(TOKEN_HEADER);
        if (token == null) {
            throw new JwtNotExistException("Token不存在");
        }
        return token;
    }

    public static boolean isTokenExisted(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        if(token == null) {
            return false;
        }
        return true;
    }
}
