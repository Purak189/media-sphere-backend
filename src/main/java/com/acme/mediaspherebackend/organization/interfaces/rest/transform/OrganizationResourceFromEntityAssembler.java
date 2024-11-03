package com.acme.mediaspherebackend.organization.interfaces.rest.transform;

import com.acme.mediaspherebackend.organization.domain.model.aggregates.Organization;
import com.acme.mediaspherebackend.organization.interfaces.rest.resources.OrganizationResource;

public class OrganizationResourceFromEntityAssembler {
    public static OrganizationResource toResourceFromEntity(Organization organization){
        return new OrganizationResource(organization.getOrganization_id(), organization.getName(), organization.getDescription());
    }
}
