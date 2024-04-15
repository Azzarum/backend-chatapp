package net.devazzarum.chatapp.Services.Login;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {

    private static int expireHourRefreshToken = 72;

    private static final String Secret_key ="c2e3ad17f3a8effdd47b3b6b8a3090c4e5cfdf92e5cb1942ad2771365ad2fd5f";


    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Objects> extraClaims, UserDetails userDetails) {
        Claims claims = Jwts.claims();
        claims.setSubject(userDetails.getUsername());
        claims.put("role", userDetails.getAuthorities());
        claims.putAll(extraClaims);


        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 48))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(Secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public static String createRefreshToken(String email) {
        try {
            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(email)
                    .expirationTime(Date.from(Instant.now().plusSeconds(expireHourRefreshToken * 3600)))
                    .build();

            Payload payload = new Payload(claims.toJSONObject());

            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256),
                    payload);

            jwsObject.sign(new MACSigner(Secret_key));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException("Erreur lors de la création d'un JWT", e);
        }
    }
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


}
