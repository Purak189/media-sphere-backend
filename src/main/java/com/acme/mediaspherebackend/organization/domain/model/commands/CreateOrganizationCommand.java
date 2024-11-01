package com.acme.mediaspherebackend.organization.domain.model.commands;

public record CreateOrganizationCommand(
        String name,
        String description
) {
}
