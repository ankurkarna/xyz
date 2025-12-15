    package org.xyz.CRUD.utils;

    import io.jsonwebtoken.*;
    import io.jsonwebtoken.security.Keys;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Component;

    import java.security.Key;
    import java.util.Base64;
    import java.util.Date;

    @Component
    public class JwtUtil {

        @Value("${jwt.secret.key}")
        private String SECRET_KEY;

        @Value("${jwt.expiration}")
        private Long EXPIRATION;

        private Key getSigningKey() {
            byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
            return Keys.hmacShaKeyFor(keyBytes);
        }

        public String generateToken(String username) {
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                    .compact();
        }

        public String extractUsername(String token) {
            return Jwts.parserBuilder().setSigningKey(getSigningKey())
                    .build().parseClaimsJws(token)
                    .getBody().getSubject();
        }

        public boolean validateToken(String token, String username) {
            return username.equals(extractUsername(token)) && !isTokenExpired(token);
        }

        private boolean isTokenExpired(String token) {
            Date expiration = Jwts.parserBuilder().setSigningKey(getSigningKey())
                    .build().parseClaimsJws(token).getBody().getExpiration();
            return expiration.before(new Date());
        }
    }
