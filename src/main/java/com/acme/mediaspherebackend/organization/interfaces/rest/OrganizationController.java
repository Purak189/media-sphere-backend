package com.acme.mediaspherebackend.organization.interfaces.rest;

import com.acme.mediaspherebackend.aim.interfaces.acl.IamContextFacade;
import com.acme.mediaspherebackend.organization.domain.model.commands.CreateOrganizationCommand;
import com.acme.mediaspherebackend.organization.domain.model.valueobjects.Role;
import com.acme.mediaspherebackend.organization.domain.services.OrganizationCommandService;
import com.acme.mediaspherebackend.organization.domain.services.OrganizationQueryService;
import com.acme.mediaspherebackend.organization.interfaces.acl.MembershipContextFacade;
import com.acme.mediaspherebackend.organization.interfaces.rest.resources.CreateOrganizationResource;
import com.acme.mediaspherebackend.organization.interfaces.rest.resources.OrganizationResource;
import com.acme.mediaspherebackend.organization.interfaces.rest.transform.OrganizationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/organizations")
@Tag(name = "Organizations", description = "Operations related to organizations")
public class OrganizationController {
    private final OrganizationCommandService organizationCommandService;
    private final OrganizationQueryService organizationQueryService;
    private final IamContextFacade iamContextFacade;
    private final MembershipContextFacade membershipContextFacade;

    public OrganizationController(OrganizationCommandService organizationCommandService, OrganizationQueryService organizationQueryService, IamContextFacade iamContextFacade, MembershipContextFacade membershipContextFacade) {
        this.organizationCommandService = organizationCommandService;
        this.organizationQueryService = organizationQueryService;
        this.iamContextFacade = iamContextFacade;
        this.membershipContextFacade = membershipContextFacade;
    }

    // @PostMapping
    @Operation(summary = "Create a new organization")
    @PostMapping
    public ResponseEntity<OrganizationResource> createOrganization(@RequestBody CreateOrganizationResource createOrganizationResource) {
        var createOrganizationCommand = new CreateOrganizationCommand(createOrganizationResource.name(), createOrganizationResource.description(), null);
        var organization = this.organizationCommandService.handle(createOrganizationCommand);

        var user = this.iamContextFacade.getUserById(createOrganizationResource.userId());
        var membership = this.membershipContextFacade.createMembership(user, Role.CREATOR, organization.get());

        this.organizationCommandService.save(organization.get());

        var organizationResource = OrganizationResourceFromEntityAssembler.toResourceFromEntity(organization.get());

        return new ResponseEntity<>(organizationResource, HttpStatus.CREATED);
    }
}
