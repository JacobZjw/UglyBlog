package com.ugly.blog.util;

import com.ugly.blog.constant.Constants;
import com.ugly.blog.dto.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JwZheng
 * @date 2021/4/19 18:12
 */
@PropertySource(value = {"classpath:application.properties"})
public class TokenUtils {
    @Value("${token.secret}")
    private static final String secretKey = "abcdefghijklmnopqrstuvwxyz";


    public static String createToken(LoginUser loginUser) {

        long expiration = loginUser.getIsRememberMe() ? Constants.EXPIRATION_REMEMBER : Constants.EXPIRATION;

        String token = Jwts.builder().setHeaderParam("type", Constants.TOKEN_TYPE)
                .claim(Constants.ROLE_CLAIMS, String.join(",", getRoles(loginUser.getAuthorities())))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setId(String.valueOf(loginUser.getId()))
                .setSubject(loginUser.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
        return Constants.TOKEN_PREFIX + token;
    }

    private static List<String> getRoles(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    public static String getUsername(String token) {
        return parseToken(token).getSubject();
    }

    public static String getId(String token) {
        Claims claims = parseToken(token);
        return claims.getId();
    }

    private static List<SimpleGrantedAuthority> getAuthorities(Claims claims) {
        String role = (String) claims.get(Constants.ROLE_CLAIMS);
        return Arrays.stream(role.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(Constants.TOKEN_HEADER);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims claims = parseToken(token);
        List<SimpleGrantedAuthority> authorities = getAuthorities(claims);
        String userName = claims.getSubject();
        return new UsernamePasswordAuthenticationToken(userName, token, authorities);
    }

    /**
     * 验证Token是否过期
     *
     * @param token
     * @return
     */
    private boolean verifyToken(String token) {
        Date expiredDate = parseToken(token).getExpiration();
        return expiredDate.before(new Date());
    }

}
