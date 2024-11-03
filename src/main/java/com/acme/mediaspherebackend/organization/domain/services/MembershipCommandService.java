package com.acme.mediaspherebackend.organization.domain.services;

import com.acme.mediaspherebackend.organization.domain.model.aggregates.Membership;
import com.acme.mediaspherebackend.organization.domain.model.commands.CreateMembershipCommand;

import java.util.Optional;

public interface MembershipCommandService {
    Optional<Membership> handle(CreateMembershipCommand command);
}
