package com.stefan.testeauto.service;

import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class AuthService {

    private static final String SECRET_KEY = "4BqHPHxribtwRxqjWcJU8wYQ7za86CSH4jmngPPjifFrBc4NXiXXSJgLaiCrL8EtBExFjevg5BRfhzCdaSTXeKD8bFdz6zaJzWz4uNvcLTZT6mE9hrmd4CUQxkvA5jjeCtqunHxJTzQxEr7tJ8x2dt";

    public String createJWT(String email) {
        // Build an HMAC signer using a SHA-256 hash
        Signer signer = HMACSigner.newSHA256Signer(SECRET_KEY);

        // Build a new JWT with an issuer(iss), issued at(iat), subject(sub) and expiration(exp)
        JWT jwt = new JWT()
                .setIssuer("teste-auto")
                .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
                .setSubject(email)
                .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(60));
//                .addClaim("email", email);

        // Sign and encode the JWT to a JSON string representation
        return JWT.getEncoder().encode(jwt, signer);
    }

    public JWT verify(String encodedJWT) {
        // Build an HMC verifier using the same secret that was used to sign the JWT
        Verifier verifier = HMACVerifier.newVerifier(SECRET_KEY);

        // Verify and decode the encoded string JWT to a rich object
        return JWT.getDecoder().decode(encodedJWT, verifier);
    }
}
