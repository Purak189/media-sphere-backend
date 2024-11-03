package com.acme.mediaspherebackend.organization.interfaces.rest.resources;

import com.acme.mediaspherebackend.organization.domain.model.aggregates.Membership;

public record OrganizationResource(
        Long id,
        String name,
        String description
) {
}
