package com.acme.mediaspherebackend.organization.domain.model.commands;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.organization.domain.model.aggregates.Organization;
import com.acme.mediaspherebackend.organization.domain.model.valueobjects.Role;

public record CreateMembershipCommand(
        User user,
        Role role,
        Organization organization
) {
}
