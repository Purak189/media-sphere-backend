package com.acme.mediaspherebackend.aim.interfaces.rest.resources;

public record SignUpResource(
String email, String fullName, String password
) {
}
