package com.acme.mediaspherebackend.organization.domain.services;

import com.acme.mediaspherebackend.organization.domain.model.aggregates.Membership;
import com.acme.mediaspherebackend.organization.domain.model.queries.GetMembershipsByUserQuery;

import java.util.List;
import java.util.Optional;

public interface MembershipQueryService {
    Optional<List<Membership>> handle(GetMembershipsByUserQuery query);
}
