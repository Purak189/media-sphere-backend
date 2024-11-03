package com.acme.mediaspherebackend.organization.domain.model.commands;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.organization.domain.model.aggregates.Membership;

public record CreateOrganizationCommand(
        String name,
        String description,
        Membership membership
) {
}
