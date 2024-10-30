package com.acme.mediaspherebackend.aim.application.internal.outboundedservices.hashing;

public interface HashingService {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword, String encodedPassword);
}
