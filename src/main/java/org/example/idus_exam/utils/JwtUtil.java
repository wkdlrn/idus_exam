package org.example.idus_exam.utils;

import org.example.idus_exam.member.model.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "abcdefghijklmnopqrstuvwxyz0123456789abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int EXP = 30 * 60 * 1000;


    public static Member getMember(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return Member.builder()
                    .idx(claims.get("userIdx", Long.class))
                    .name(claims.get("name", String.class))
                    .phoneNum(claims.get("phoneNum", String.class))
                    .gender(claims.get("gender", String.class))
                    .email(claims.get("userEmail", String.class))
                    .nickName(claims.get("userNickName", String.class))
                    .role(claims.get("userRole", String.class))
                    .build();

        } catch (ExpiredJwtException e) {
            System.out.println("토큰이 만료되었습니다!");
            return null;
        }
    }

    public static String generateToken(Long userIdx, String userEmail, String userNickName, String userRole, String phoneNum, String gender, String name) {
        Claims claims = Jwts.claims();
        claims.put("userRole", userRole);
        claims.put("userEmail", userEmail);
        claims.put("userNickName", userNickName);
        claims.put("userIdx", userIdx);
        claims.put("phoneNum", phoneNum);
        claims.put("gender", gender);
        claims.put("name", name);

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXP))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        return token;
    }

    public static boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            System.out.println("토큰이 만료되었습니다!");
            return false;
        }
        return true;
    }
}
