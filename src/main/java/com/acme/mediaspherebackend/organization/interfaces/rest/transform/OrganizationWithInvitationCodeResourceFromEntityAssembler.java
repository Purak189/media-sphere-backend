package com.acme.mediaspherebackend.organization.interfaces.rest.transform;

import com.acme.mediaspherebackend.invitation.domain.model.aggregates.InvitationCode;
import com.acme.mediaspherebackend.organization.domain.model.aggregates.Organization;
import com.acme.mediaspherebackend.organization.interfaces.rest.resources.OrganizationWithInvitationCodeResource;

public class OrganizationWithInvitationCodeResourceFromEntityAssembler {
    public static OrganizationWithInvitationCodeResource toResourceFromEntity(Organization organization, InvitationCode invitationCode){
        return new OrganizationWithInvitationCodeResource(organization.getId(), invitationCode.getCode(), invitationCode.getExpired_at());
    }
}
