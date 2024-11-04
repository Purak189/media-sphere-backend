package com.acme.mediaspherebackend.organization.interfaces.acl;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.aim.interfaces.acl.IamContextFacade;
import com.acme.mediaspherebackend.organization.domain.model.aggregates.Membership;
import com.acme.mediaspherebackend.organization.domain.model.aggregates.Organization;
import com.acme.mediaspherebackend.organization.domain.model.commands.CreateMembershipCommand;
import com.acme.mediaspherebackend.organization.domain.model.queries.GetMembershipsByUserQuery;
import com.acme.mediaspherebackend.organization.domain.model.valueobjects.Role;
import com.acme.mediaspherebackend.organization.domain.services.MembershipCommandService;
import com.acme.mediaspherebackend.organization.domain.services.MembershipQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipContextFacade {
    public final MembershipCommandService membershipCommandService;
    public final MembershipQueryService membershipQueryService;
    public final IamContextFacade iamContextFacade;

    public MembershipContextFacade(MembershipCommandService membershipCommandService, MembershipQueryService membershipQueryService, IamContextFacade iamContextFacade) {
        this.membershipCommandService = membershipCommandService;
        this.membershipQueryService = membershipQueryService;
        this.iamContextFacade = iamContextFacade;
    }

    public Membership createMembership(User user, Role role, Organization organization){
        var command = new CreateMembershipCommand(user, role, organization);
        var membership = new Membership(command);

        this.membershipCommandService.handle(command);

        return membership;
    }

    public List<Membership> getMembershipsByUser(User user){
        var query = new GetMembershipsByUserQuery(user);
        return this.membershipQueryService.handle(query).orElse(null);
    }
}
