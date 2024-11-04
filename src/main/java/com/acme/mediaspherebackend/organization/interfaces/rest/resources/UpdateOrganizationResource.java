package com.acme.mediaspherebackend.organization.interfaces.rest.resources;

public record UpdateOrganizationResource(
        String name,
        String description
) {
}
