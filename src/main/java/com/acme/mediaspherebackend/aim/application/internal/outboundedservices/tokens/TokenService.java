package com.acme.mediaspherebackend.aim.application.internal.outboundedservices.tokens;

public interface TokenService {
    String generateToken(String email);
    String getEmailFromToken(String token);
    boolean validateToken(String token);
}
