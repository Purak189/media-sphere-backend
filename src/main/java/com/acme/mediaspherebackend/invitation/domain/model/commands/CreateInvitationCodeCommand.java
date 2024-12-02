package com.acme.mediaspherebackend.invitation.domain.model.commands;

import com.acme.mediaspherebackend.organization.domain.model.aggregates.Organization;

public record CreateInvitationCodeCommand(
        Organization organization
) {
}
