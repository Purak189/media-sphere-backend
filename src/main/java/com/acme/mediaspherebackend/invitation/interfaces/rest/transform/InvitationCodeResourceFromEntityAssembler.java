package com.acme.mediaspherebackend.invitation.interfaces.rest.transform;

import com.acme.mediaspherebackend.invitation.domain.model.aggregates.InvitationCode;
import com.acme.mediaspherebackend.invitation.interfaces.rest.resources.InvitationCodeResource;

public class InvitationCodeResourceFromEntityAssembler {
    public static InvitationCodeResource toResourceFromEntity(InvitationCode invitationCode) {
        return new InvitationCodeResource(invitationCode.getCode(), invitationCode.getCreated_at(), invitationCode.getExpired_at());
    }
}
