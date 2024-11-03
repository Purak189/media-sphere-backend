package com.acme.mediaspherebackend.organization.interfaces.rest.resources;

public record CreateOrganizationResource(
        Long userId,
        String name,
        String description
) {
}
