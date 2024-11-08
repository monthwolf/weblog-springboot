package cn.dogalist.weblog.jwt.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

/**
 * JWT令牌工具类
 */
@Component
@Slf4j
public class JwtTokenHelper implements InitializingBean {
    /**
     * 签发人
     */
    @Value("${jwt.issuer}")
    private String issuer;
    /**
     * 密钥
     */
    private Key key;
    /**
     * JWT解析
     */
    private JwtParser jwtParser;

    /**
     * 解码配置文件中配置的Base64编码的密钥
     */
    @Value("${jwt.secret}")
    public void setSecret(String base64Key) {
        key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64Key));
    }

    /**
     * 初始化JwtParser
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        jwtParser = Jwts.parserBuilder().requireIssuer(issuer)
                .setSigningKey(key)
                .setAllowedClockSkewSeconds(10)
                .build();
    }

    /**
     * 生成token
     * 
     * @param username
     * @return token
     */
    public String generateToken(String username) {
        LocalDateTime now = LocalDateTime.now();
        // 一小时后失效
        LocalDateTime expiration = now.plusHours(1);
        return Jwts.builder().setSubject(username)
                .setIssuer(issuer)
                .setIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expiration.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(key)
                .compact();
    }

    /**
     * 解析token
     * 
     * @param token
     * @return
     */
    public Jws<Claims> parseToken(String token) {
        try {
            return jwtParser.parseClaimsJws(token);
        } catch (SignatureException | MalformedJwtException e) {
            throw new BadCredentialsException("token不可用");
        } catch (ExpiredJwtException e) {
            throw new CredentialsExpiredException("token已过期");
        }
    }

    /**
     * 校检token是否可用
     * 
     * @param token
     * @return
     */
    public void validateToken(String token) {
        // parseClaimsJws方法会校检token是否可用，如果不可用会抛出这几种异常：
        // SignatureException、MalformedJwtException、UnsupportedJwtException、IllegalArgumentException、ExpiredJwtException
        jwtParser.parseClaimsJws(token);
    }

    /**
     * 解析token中的用户名
     * 
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        try {
            // 解析token字段
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            return claims.getSubject(); // 获取用户名，即subject字段
        } catch (Exception e) {
            log.error("解析token时发生异常: {}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * 生成一个Base64编码的密钥
     * 
     * @Return String
     */
    private static String generatebase64Key() {
        // 生成安全密匙
        Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        // 将密匙转为base64
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    public static void main(String[] args) {
        System.out.println("key:" + generatebase64Key());
    }
}
