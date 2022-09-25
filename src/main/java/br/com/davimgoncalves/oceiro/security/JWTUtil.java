package br.com.davimgoncalves.oceiro.security;

import br.com.davimgoncalves.oceiro.config.details.UsuarioDetail;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(UsuarioDetail usuarioDetail) {
        log.info("generating token for {};", usuarioDetail.getUsername());
        return Jwts.builder()
                .setSubject(usuarioDetail.getUsername())
                .claim("role", usuarioDetail.getAuthorities())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public boolean isValid(String jwt) {
        log.info("validating token");
        try {
            getJws(jwt);
            log.info("valid token");
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public String getUsername(String jwt) {
        return getJws(jwt).getBody().getSubject();
    }

    private Jws<Claims> getJws(String jwt) {
        return Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(jwt);
    }
}
