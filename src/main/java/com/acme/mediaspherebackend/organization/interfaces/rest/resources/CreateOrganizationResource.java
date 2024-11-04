package com.acme.mediaspherebackend.organization.interfaces.rest.resources;

public record CreateOrganizationResource(
        String name,
        String description
) {
}
