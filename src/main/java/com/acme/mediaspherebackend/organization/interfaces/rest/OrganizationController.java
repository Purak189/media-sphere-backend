package com.acme.mediaspherebackend.organization.interfaces.rest;

import com.acme.mediaspherebackend.aim.interfaces.acl.IamContextFacade;
import com.acme.mediaspherebackend.organization.domain.services.OrganizationCommandService;
import com.acme.mediaspherebackend.organization.domain.services.OrganizationQueryService;
import com.acme.mediaspherebackend.organization.interfaces.rest.resources.CreateOrganizationResource;
import com.acme.mediaspherebackend.organization.interfaces.rest.resources.OrganizationResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    public OrganizationController(OrganizationCommandService organizationCommandService, OrganizationQueryService organizationQueryService, IamContextFacade iamContextFacade) {
        this.organizationCommandService = organizationCommandService;
        this.organizationQueryService = organizationQueryService;
        this.iamContextFacade = iamContextFacade;
    }

    // @PostMapping
    @Operation(summary = "Create a new organization")
    @PostMapping
    public ResponseEntity<OrganizationResource> createOrganization(@RequestBody CreateOrganizationResource createOrganizationResource) {
        return ResponseEntity.ok(null);
    }
}
