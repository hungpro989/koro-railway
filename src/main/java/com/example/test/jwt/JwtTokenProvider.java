package com.example.test.jwt;

import com.example.test.config.CustomUserDetails;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenProvider {
    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    @Value("${com.security.secret}")
    private String JWT_SECRET;

    //Thời gian có hiệu lực của chuỗi jwt (vi du 7 ngay)
    @Value("${com.security.expiration}")
    private  long JWT_EXPIRATION;

    // Tạo ra jwt từ thông tin user
    public String generateToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        Map<String, Object> claims = new HashMap<>();
//        claims.put("fullName",userDetails.getFullName());
//        claims.put("id",userDetails.getId());
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    //Dich nguoc - Lấy thông tin user từ jwt
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }
    public String getUsernameFromJwt    (String token){
        Claims claims= Jwts.parser() //phân tích
                .setSigningKey(JWT_SECRET)// lấy JWT_SECRET_KEY để mở khoá
                .parseClaimsJws(token)
                .getBody(); //lấy ra thông tin của User

        //trả về thông tin user
        return claims.getSubject();
    }
    //xac thuc token
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET)
                    .parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
