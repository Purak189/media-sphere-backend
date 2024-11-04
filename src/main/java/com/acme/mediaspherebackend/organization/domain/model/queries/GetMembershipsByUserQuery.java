package com.acme.mediaspherebackend.organization.domain.model.queries;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;

public record GetMembershipsByUserQuery(
        User user
) {
}
