package com.acme.mediaspherebackend.organization.interfaces.rest;

import com.acme.mediaspherebackend.aim.interfaces.acl.IamContextFacade;
import com.acme.mediaspherebackend.organization.domain.model.commands.CreateOrganizationCommand;
import com.acme.mediaspherebackend.organization.domain.model.commands.DeleteOrganizationCommand;
import com.acme.mediaspherebackend.organization.domain.model.queries.GetOrganizationById;
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
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/organizations", produces = APPLICATION_JSON_VALUE)
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

    // @DeleteMapping("/{id}")
    @Operation(summary = "Delete an organization")
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
}
