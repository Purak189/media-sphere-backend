package com.acme.mediaspherebackend.organization.interfaces.rest;

import com.acme.mediaspherebackend.aim.interfaces.acl.IamContextFacade;
import com.acme.mediaspherebackend.invitation.interfaces.acl.InvitationContextFacade;
import com.acme.mediaspherebackend.organization.domain.model.aggregates.Membership;
import com.acme.mediaspherebackend.organization.domain.model.commands.CreateOrganizationCommand;
import com.acme.mediaspherebackend.organization.domain.model.commands.DeleteOrganizationCommand;
import com.acme.mediaspherebackend.organization.domain.model.commands.UpdateOrganizationCommand;
import com.acme.mediaspherebackend.organization.domain.model.commands.ValidateUserRoleCommand;
import com.acme.mediaspherebackend.organization.domain.model.queries.GetOrganizationById;
import com.acme.mediaspherebackend.organization.domain.model.valueobjects.Role;
import com.acme.mediaspherebackend.organization.domain.services.OrganizationCommandService;
import com.acme.mediaspherebackend.organization.domain.services.OrganizationQueryService;
import com.acme.mediaspherebackend.organization.interfaces.acl.MembershipContextFacade;
import com.acme.mediaspherebackend.organization.interfaces.rest.resources.CreateOrganizationResource;
import com.acme.mediaspherebackend.organization.interfaces.rest.resources.OrganizationResource;
import com.acme.mediaspherebackend.organization.interfaces.rest.resources.OrganizationWithInvitationCodeResource;
import com.acme.mediaspherebackend.organization.interfaces.rest.resources.UpdateOrganizationResource;
import com.acme.mediaspherebackend.organization.interfaces.rest.transform.OrganizationResourceFromEntityAssembler;
import com.acme.mediaspherebackend.organization.interfaces.rest.transform.OrganizationWithInvitationCodeResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/organizations", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Organizations", description = "Operations related to organizations")
public class OrganizationController {
    private final OrganizationCommandService organizationCommandService;
    private final OrganizationQueryService organizationQueryService;
    private final IamContextFacade iamContextFacade;
    private final MembershipContextFacade membershipContextFacade;
    private final InvitationContextFacade invitationContextFacade;

    public OrganizationController(OrganizationCommandService organizationCommandService, OrganizationQueryService organizationQueryService, IamContextFacade iamContextFacade, MembershipContextFacade membershipContextFacade, InvitationContextFacade invitationContextFacade) {
        this.organizationCommandService = organizationCommandService;
        this.organizationQueryService = organizationQueryService;
        this.iamContextFacade = iamContextFacade;
        this.membershipContextFacade = membershipContextFacade;
        this.invitationContextFacade = invitationContextFacade;
    }

    // @PostMapping
    @Operation(summary = "Create a new organization")
    @PostMapping
    public ResponseEntity<OrganizationResource> createOrganization(@RequestBody CreateOrganizationResource createOrganizationResource) {
        var createOrganizationCommand = new CreateOrganizationCommand(createOrganizationResource.name(), createOrganizationResource.description(), null);
        var organization = this.organizationCommandService.handle(createOrganizationCommand);

        var user = this.iamContextFacade.getCurrentUser().orElseThrow(() -> new IllegalStateException("User not authenticated."));
        var membership = this.membershipContextFacade.createMembership(user, Role.OWNER, organization.get());

        this.organizationCommandService.save(organization.get());

        var organizationResource = OrganizationResourceFromEntityAssembler.toResourceFromEntity(organization.get());

        return new ResponseEntity<>(organizationResource, HttpStatus.CREATED);
    }

    // @DeleteMapping("/{id}")
    @Operation(summary = "Delete an organization if role is OWNER")
    @DeleteMapping("/{organizationId}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long organizationId){
        var organization = this.organizationQueryService.handle(new GetOrganizationById(organizationId));

        if (organization.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var user = this.iamContextFacade.getCurrentUser().orElseThrow(() -> new IllegalStateException("User not authenticated."));
        var org = organization.get();

        this.organizationCommandService.handle(new DeleteOrganizationCommand(org, user));
        return ResponseEntity.ok().build();
    }

    // @GetMapping
    @Operation(summary = "Get all organizations by user if got a membership")
    @GetMapping
    public ResponseEntity<List<OrganizationResource>> getOrganizations() {
        var user = this.iamContextFacade.getCurrentUser().orElseThrow(() -> new IllegalStateException("User not authenticated."));
        var memberships = this.membershipContextFacade.getMembershipsByUser(user);

        var organizations = memberships.stream()
                .map(Membership::getOrganization)
                .toList();

        var organizationResources = organizations.stream()
                .map(OrganizationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(organizationResources);
    }

    // @UpdateMapping
    @Operation(summary = "Update organization if role is OWNER or ADMIN")
    @PutMapping("/{organizationId}")
    public ResponseEntity<Void> updateOrganization(@PathVariable Long organizationId, @RequestBody UpdateOrganizationResource updateOrganizationResource){
        var organization = this.organizationQueryService.handle(new GetOrganizationById(organizationId));

        if(organization.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var user = this.iamContextFacade.getCurrentUser().orElseThrow(() -> new IllegalStateException("User not authenticated."));
        var updateOrganizationCommand = new UpdateOrganizationCommand(organization.get(), user, updateOrganizationResource.name(), updateOrganizationResource.description());

        this.organizationCommandService.handle(updateOrganizationCommand);

        return ResponseEntity.ok().build();
    }

    // @PostMapping("/{id}")
    @Operation(summary = "Get invitation code by organization id if role is OWNER or ADMIN")
    @PostMapping("/{organizationId}/invitation-code")
    public ResponseEntity<OrganizationWithInvitationCodeResource> createInvitationCode(@PathVariable Long organizationId){
        var organization = this.organizationQueryService.handle(new GetOrganizationById(organizationId));

        if(organization.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var user = this.iamContextFacade.getCurrentUser().orElseThrow(() -> new IllegalStateException("User not authenticated."));
        var org = organization.get();
        var validateUserRoleCommand = new ValidateUserRoleCommand(org, user);

        if (!this.organizationCommandService.handle(validateUserRoleCommand)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        var invitationCode = this.invitationContextFacade.createInvitationCodeByOrg(org);

        var organizationWithInvitatioCodeResource = OrganizationWithInvitationCodeResourceFromEntityAssembler.toResourceFromEntity(org,invitationCode);

        return new ResponseEntity<>(organizationWithInvitatioCodeResource, HttpStatus.CREATED);
    }
}
