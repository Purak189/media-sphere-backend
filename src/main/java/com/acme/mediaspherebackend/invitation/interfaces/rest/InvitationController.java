package com.acme.mediaspherebackend.invitation.interfaces.rest;

import com.acme.mediaspherebackend.invitation.domain.model.queries.GetInvitationCodeByOrganizationIdQuery;
import com.acme.mediaspherebackend.invitation.domain.services.InvitationCodeQueryService;
import com.acme.mediaspherebackend.invitation.interfaces.rest.resources.InvitationCodeResource;
import com.acme.mediaspherebackend.invitation.interfaces.rest.transform.InvitationCodeResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/invitations", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Invitations", description = "Operations related to invitations")
public class InvitationController {
    private final InvitationCodeQueryService invitationCodeQueryService;


    public InvitationController(InvitationCodeQueryService invitationCodeQueryService) {
        this.invitationCodeQueryService = invitationCodeQueryService;
    }

    @Operation(summary = "Get invitation code by organization id")
    @GetMapping("/{organizationId}")
    public ResponseEntity<InvitationCodeResource> getInvitationCodeByOrganizationId(@PathVariable Long organizationId) {
        var invitationCode = this.invitationCodeQueryService.handle(new GetInvitationCodeByOrganizationIdQuery(organizationId));

        if (invitationCode.isPresent()) {
            var invitationCodeResource = InvitationCodeResourceFromEntityAssembler.toResourceFromEntity(invitationCode.get());
            return ResponseEntity.ok(invitationCodeResource);
        }

        return ResponseEntity.notFound().build();
    }
}
