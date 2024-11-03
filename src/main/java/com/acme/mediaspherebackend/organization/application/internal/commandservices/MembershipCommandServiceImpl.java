package com.acme.mediaspherebackend.organization.application.internal.commandservices;

import com.acme.mediaspherebackend.organization.domain.model.aggregates.Membership;
import com.acme.mediaspherebackend.organization.domain.model.commands.CreateMembershipCommand;
import com.acme.mediaspherebackend.organization.domain.services.MembershipCommandService;
import com.acme.mediaspherebackend.organization.infraestructure.persistence.jpa.repositories.MembershipRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembershipCommandServiceImpl implements MembershipCommandService {
    private final MembershipRepository membershipRepository;

    public MembershipCommandServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public Optional<Membership> handle(CreateMembershipCommand command) {
        var membership = new Membership(command);

        this.membershipRepository.save(membership);

        return Optional.of(membership);
    }
}
