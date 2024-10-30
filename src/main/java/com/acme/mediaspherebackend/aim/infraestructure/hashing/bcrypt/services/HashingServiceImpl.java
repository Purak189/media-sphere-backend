package com.acme.mediaspherebackend.aim.infraestructure.hashing.bcrypt.services;

import com.acme.mediaspherebackend.aim.infraestructure.hashing.bcrypt.BcryptHashingService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HashingServiceImpl implements BcryptHashingService {
    private final BCryptPasswordEncoder passwordEncoder;

    public HashingServiceImpl() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
