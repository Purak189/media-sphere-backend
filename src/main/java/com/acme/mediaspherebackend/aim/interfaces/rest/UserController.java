package com.acme.mediaspherebackend.aim.interfaces.rest;

import com.acme.mediaspherebackend.aim.domain.services.UserCommandService;
import com.acme.mediaspherebackend.aim.domain.services.UserQueryService;
import com.acme.mediaspherebackend.aim.interfaces.rest.resources.AuthenticateUserResource;
import com.acme.mediaspherebackend.aim.interfaces.rest.resources.SignInResource;
import com.acme.mediaspherebackend.aim.interfaces.rest.transform.AuthenticateUserResourceFromEntityAssembler;
import com.acme.mediaspherebackend.aim.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/auth")
@Tag(name = "Auth", description = "Operations related to users auth")
public class UserController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    @Operation(summary = "Sing in")
    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticateUserResource> signIn(@RequestBody SignInResource signInResource){
        var signInCommand = SignInCommandFromResourceAssembler.toCommandFromResource(signInResource);
        var authenticateUser = userCommandService.handle(signInCommand);

        if(authenticateUser.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var authenticateUserResource = AuthenticateUserResourceFromEntityAssembler.toResourceFromEntity(
                authenticateUser.get().getLeft());
        return ResponseEntity.ok(authenticateUserResource);
    }
}
