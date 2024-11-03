package com.acme.mediaspherebackend.organization.application.internal.queryservices;

import com.acme.mediaspherebackend.organization.domain.model.aggregates.Organization;
import com.acme.mediaspherebackend.organization.domain.model.queries.GetOrganizationById;
import com.acme.mediaspherebackend.organization.domain.model.queries.GetOrganizationsByUserId;
import com.acme.mediaspherebackend.organization.domain.services.OrganizationQueryService;
import com.acme.mediaspherebackend.organization.infraestructure.persistence.jpa.repositories.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationQueryServiceImpl implements OrganizationQueryService {
    public final OrganizationRepository organizationRepository;

    public OrganizationQueryServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public List<Organization> handle(GetOrganizationsByUserId query) {
       return this.organizationRepository.findAllByUserId(query.userId());
    }

    @Override
    public Optional<Organization> handle(GetOrganizationById query) {
        return this.organizationRepository.findById(query.organizationId());
    }
}
