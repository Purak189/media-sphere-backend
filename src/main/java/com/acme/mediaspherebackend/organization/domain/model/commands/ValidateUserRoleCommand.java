package com.acme.mediaspherebackend.organization.domain.model.commands;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.organization.domain.model.aggregates.Organization;

public record ValidateUserRoleCommand(
        Organization organization,
        User user
) {
}
