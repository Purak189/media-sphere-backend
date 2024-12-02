package com.acme.mediaspherebackend.organization.domain.services;


import com.acme.mediaspherebackend.organization.domain.model.aggregates.Organization;
import com.acme.mediaspherebackend.organization.domain.model.commands.CreateOrganizationCommand;
import com.acme.mediaspherebackend.organization.domain.model.commands.DeleteOrganizationCommand;
import com.acme.mediaspherebackend.organization.domain.model.commands.UpdateOrganizationCommand;
import com.acme.mediaspherebackend.organization.domain.model.commands.ValidateUserRoleCommand;

import java.util.Optional;

public interface OrganizationCommandService {
    Optional<Organization> handle(CreateOrganizationCommand command);
    Optional<Organization> handle(UpdateOrganizationCommand command);
    Optional<Organization> handle(DeleteOrganizationCommand command);

    boolean handle(ValidateUserRoleCommand command);
    void save(Organization organization);
}
