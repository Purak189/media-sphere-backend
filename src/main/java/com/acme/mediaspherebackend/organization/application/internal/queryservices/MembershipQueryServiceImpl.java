package com.acme.mediaspherebackend.organization.application.internal.queryservices;

import com.acme.mediaspherebackend.organization.domain.model.aggregates.Membership;
import com.acme.mediaspherebackend.organization.domain.model.queries.GetMembershipsByUserQuery;
import com.acme.mediaspherebackend.organization.domain.services.MembershipQueryService;
import com.acme.mediaspherebackend.organization.infraestructure.persistence.jpa.repositories.MembershipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipQueryServiceImpl implements MembershipQueryService {
    private final MembershipRepository membershipRepository;

    public MembershipQueryServiceImpl(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public Optional<List<Membership>> handle(GetMembershipsByUserQuery query) {
        return Optional.ofNullable(this.membershipRepository.findAllByUser(query.user()));
    }
}
