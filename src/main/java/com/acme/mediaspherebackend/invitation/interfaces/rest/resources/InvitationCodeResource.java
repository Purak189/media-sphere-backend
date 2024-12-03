package com.acme.mediaspherebackend.invitation.interfaces.rest.resources;

import java.time.LocalDateTime;

public record InvitationCodeResource(
        String code,
        LocalDateTime createdAt,
        LocalDateTime expiredAt
) {
}
