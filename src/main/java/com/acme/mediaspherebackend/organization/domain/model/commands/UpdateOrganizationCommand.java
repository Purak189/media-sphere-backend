package com.acme.mediaspherebackend.organization.domain.model.commands;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.organization.domain.model.aggregates.Organization;

public record UpdateOrganizationCommand(
        Organization organization,
        User user,
        String name,
        String description
) {
}
