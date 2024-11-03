package com.acme.mediaspherebackend.aim.infraestructure.tokens.jwt;

import com.acme.mediaspherebackend.aim.application.internal.outboundedservices.tokens.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface BearerTokenService extends TokenService {
    String getBearerTokenFrom(HttpServletRequest token);
    String generateToken(Authentication authentication);
}
