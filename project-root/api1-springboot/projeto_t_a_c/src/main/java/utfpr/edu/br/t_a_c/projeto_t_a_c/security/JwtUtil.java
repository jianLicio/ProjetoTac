package utfpr.edu.br.t_a_c.projeto_t_a_c.security;

import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private final String SECRET_KEY = JwtKey.getKey();

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = extractExpiration(token);
        System.out.println("Token expiration time: " + expiration);
        System.out.println("Current time: " + new Date());
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String email) {
        return createToken(email);
    }

    private String createToken(String subject) {

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System
                        .currentTimeMillis()))
                .setExpiration(new Date(System
                        .currentTimeMillis() + 1000 * 60 * 60 * 24)) // DURA 24 HORAS
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

    }

    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractEmail(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
