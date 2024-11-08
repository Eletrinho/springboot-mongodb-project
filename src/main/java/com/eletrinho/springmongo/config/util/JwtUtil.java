package com.eletrinho.springmongo.config.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;

public class JwtUtil {

    private static final String SECRET_KEY = "chave-foda";
    private static final String ISSUER = "Lixowitter";
    private static final long EXPIRATION_TIME = 3600000;

    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    public static String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer(ISSUER)
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusMillis(EXPIRATION_TIME))
                .sign(algorithm);

    }

    public static DecodedJWT validateToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
        return verifier.verify(token);
    }

    public static String extractUsername(String token) {
        return validateToken(token).getSubject();
    }
}
