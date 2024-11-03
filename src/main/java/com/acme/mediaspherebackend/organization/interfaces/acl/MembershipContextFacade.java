package com.acme.mediaspherebackend.organization.interfaces.acl;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.aim.interfaces.acl.IamContextFacade;
import com.acme.mediaspherebackend.organization.domain.model.aggregates.Membership;
import com.acme.mediaspherebackend.organization.domain.model.commands.CreateMembershipCommand;
import com.acme.mediaspherebackend.organization.domain.model.valueobjects.Role;
import com.acme.mediaspherebackend.organization.domain.services.MembershipCommandService;
import org.springframework.stereotype.Service;

@Service
public class MembershipContextFacade {
    public final MembershipCommandService membershipCommandService;
    public final IamContextFacade iamContextFacade;

    public MembershipContextFacade(MembershipCommandService membershipCommandService, IamContextFacade iamContextFacade) {
        this.membershipCommandService = membershipCommandService;
        this.iamContextFacade = iamContextFacade;
    }

    public Membership createMembership(CreateMembershipCommand command){
        var user = command.user();
        var role = command.role();

        var membership = new Membership(user, role);

        this.membershipCommandService.handle(command);

        return membership;

    }
}
