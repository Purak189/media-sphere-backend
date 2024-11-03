package com.acme.mediaspherebackend.aim.infraestructure.hashing.bcrypt;

import com.acme.mediaspherebackend.aim.application.internal.outboundedservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
