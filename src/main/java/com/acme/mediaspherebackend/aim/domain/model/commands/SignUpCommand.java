package com.acme.mediaspherebackend.aim.domain.model.commands;

public record SignUpCommand(
        String email,
        String full_name,
        String hashed_password
){}
