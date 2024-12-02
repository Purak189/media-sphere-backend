package com.acme.mediaspherebackend.organization.interfaces.rest.resources;

import java.time.LocalDateTime;

public record OrganizationWithInvitationCodeResource(
        Long organization_id,
        String invitationCode,
        LocalDateTime expired_at
) {
}
