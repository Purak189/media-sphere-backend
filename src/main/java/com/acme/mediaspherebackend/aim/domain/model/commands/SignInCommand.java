package com.acme.mediaspherebackend.aim.domain.model.commands;

public record SignInCommand(
        String email,
        String password
) {
}
