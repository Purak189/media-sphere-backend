package com.acme.mediaspherebackend.organization.domain.services;

import com.acme.mediaspherebackend.organization.domain.model.aggregates.Organization;
import com.acme.mediaspherebackend.organization.domain.model.queries.GetOrganizationById;
import com.acme.mediaspherebackend.organization.domain.model.queries.GetOrganizationsByUserId;

import java.util.List;
import java.util.Optional;

public interface OrganizationQueryService {
    List<Organization> handle(GetOrganizationsByUserId query);
    Optional<Organization> handle(GetOrganizationById query);
}
