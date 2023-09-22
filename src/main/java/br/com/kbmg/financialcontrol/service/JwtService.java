package br.com.kbmg.financialcontrol.service;

import br.com.kbmg.financialcontrol.dto.LoginDto;
import br.com.kbmg.financialcontrol.exceptions.AuthorizationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    @Value("${secret-key-jwt}")
    private String secretKeyJwt;

    @Value("${description-application}")
    private String descriptionApplication;

    public String getJwtValidated(String authorization) {

        if (Strings.isBlank(authorization) || !authorization.startsWith("Bearer ")) {
            throw new AuthorizationException();
        }

        String jwtToken = authorization.substring(7);

        if (!isValidJwtToken(jwtToken)) {
            throw new AuthorizationException();
        }

        return jwtToken;
    }

    public String getEmailFromJwt(String jwtToken) {
        return getClaims(jwtToken)
                .getBody()
                .get("email", String.class);
    }

    public boolean isValidJwtToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }

    public String generateToken(LoginDto loginDTO) {

        LocalDateTime now = LocalDateTime.now();
        Date startDate = getDate(now);
        Date expireDate = getDate(now.plusMinutes(5));

        return generateToken(loginDTO, startDate, expireDate);
    }

    private String generateToken(LoginDto loginDTO, Date startDate, Date expireDate) {
        return Jwts.builder()
                .setIssuer(descriptionApplication)
                .setSubject(UUID.randomUUID().toString()) // The subject can be of user id
                .setIssuedAt(startDate)
                .claim("email", loginDTO.getEmail())
                .claim("permissions", new ArrayList<>()) //insert here permissions logic
                .setExpiration(expireDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKeyJwt);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private Date getDate(LocalDateTime startDate) {
        return Date.from(startDate.atZone(ZoneId.systemDefault()).toInstant());
    }
}
