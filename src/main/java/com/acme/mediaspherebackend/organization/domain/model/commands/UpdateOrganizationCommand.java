package com.acme.mediaspherebackend.organization.domain.model.commands;

import com.acme.mediaspherebackend.organization.domain.model.aggregates.Organization;

public record UpdateOrganizationCommand(
        Organization organization,
        String name,
        String description
) {
}
