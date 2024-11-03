package com.acme.mediaspherebackend.organization.application.internal.commandservices;

import com.acme.mediaspherebackend.organization.domain.model.aggregates.Organization;
import com.acme.mediaspherebackend.organization.domain.model.commands.CreateOrganizationCommand;
import com.acme.mediaspherebackend.organization.domain.model.commands.DeleteOrganizationCommand;
import com.acme.mediaspherebackend.organization.domain.model.commands.UpdateOrganizationCommand;
import com.acme.mediaspherebackend.organization.domain.services.OrganizationCommandService;
import com.acme.mediaspherebackend.organization.infraestructure.persistence.jpa.repositories.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationCommandServiceImpl implements OrganizationCommandService {
    private final OrganizationRepository organizationRepository;

    public OrganizationCommandServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Optional<Organization> handle(CreateOrganizationCommand command) {
        var organization = new Organization(command);

        this.organizationRepository.save(organization);

        return Optional.of(organization);
    }

    @Override
    public Optional<Organization> handle(UpdateOrganizationCommand command) {
        var organization = command.organization();
        organization.setName(command.name());
        organization.setDescription(command.description());

        this.organizationRepository.save(organization);
        return Optional.of(organization);
    }

    @Override
    public Optional<Organization> handle(DeleteOrganizationCommand command) {
        var organization = command.organization();

        this.organizationRepository.delete(organization);

        return Optional.of(organization);
    }

    @Override
    public void save(Organization organization) {
        this.organizationRepository.save(organization);
    }
}
