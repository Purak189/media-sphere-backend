package com.acme.mediaspherebackend.invitation.domain.services;

import com.acme.mediaspherebackend.invitation.domain.model.aggregates.InvitationCode;
import com.acme.mediaspherebackend.invitation.domain.model.queries.GetInvitationCodeByOrganizationIdQuery;

import java.util.Optional;

public interface InvitationCodeQueryService {
    Optional<InvitationCode> handle(GetInvitationCodeByOrganizationIdQuery query);
}
