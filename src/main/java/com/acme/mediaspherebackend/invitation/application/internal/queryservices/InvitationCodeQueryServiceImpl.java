package com.acme.mediaspherebackend.invitation.application.internal.queryservices;

import com.acme.mediaspherebackend.invitation.domain.model.aggregates.InvitationCode;
import com.acme.mediaspherebackend.invitation.domain.model.queries.GetInvitationCodeByOrganizationIdQuery;
import com.acme.mediaspherebackend.invitation.domain.services.InvitationCodeQueryService;
import com.acme.mediaspherebackend.invitation.infraestructure.persistence.jpa.repositories.InvitationCodeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvitationCodeQueryServiceImpl implements InvitationCodeQueryService {
    private final InvitationCodeRepository invitationCodeRepository;

    public InvitationCodeQueryServiceImpl(InvitationCodeRepository invitationCodeRepository) {
        this.invitationCodeRepository = invitationCodeRepository;
    }

    @Override
    public Optional<InvitationCode> handle(GetInvitationCodeByOrganizationIdQuery query) {
        var organizationId = query.organization_id();

        if (this.invitationCodeRepository.existsByOrganization_Id(organizationId)) {
            return Optional.of(this.invitationCodeRepository.findByOrganization_Id(organizationId));
        }

        return Optional.empty();
    }
}
