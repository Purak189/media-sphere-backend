package com.acme.mediaspherebackend.invitation.interfaces.acl;

import com.acme.mediaspherebackend.invitation.domain.model.aggregates.InvitationCode;
import com.acme.mediaspherebackend.invitation.domain.model.commands.CreateInvitationCodeCommand;
import com.acme.mediaspherebackend.invitation.domain.services.InvitationCodeCommandService;
import com.acme.mediaspherebackend.organization.domain.model.aggregates.Organization;
import org.springframework.stereotype.Service;

@Service
public class InvitationContextFacade {
    public final InvitationCodeCommandService invitationCommandService;

    public InvitationContextFacade(InvitationCodeCommandService invitationCommandService) {
        this.invitationCommandService = invitationCommandService;
    }

    public InvitationCode createInvitationCodeByOrg(Organization organization){
        var command = new CreateInvitationCodeCommand(organization);
        var optionalInvitationCode = this.invitationCommandService.handle(command);

        return optionalInvitationCode.orElseThrow(() -> new RuntimeException("Failed to create invitation Code"));
    }
}
