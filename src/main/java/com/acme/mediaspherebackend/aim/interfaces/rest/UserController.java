package com.acme.mediaspherebackend.aim.interfaces.rest;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.aim.domain.model.queries.GetAllUsersQuery;
import com.acme.mediaspherebackend.aim.domain.services.UserCommandService;
import com.acme.mediaspherebackend.aim.domain.services.UserQueryService;
import com.acme.mediaspherebackend.aim.interfaces.rest.resources.AuthenticateUserResource;
import com.acme.mediaspherebackend.aim.interfaces.rest.resources.SignInResource;
import com.acme.mediaspherebackend.aim.interfaces.rest.resources.SignUpResource;
import com.acme.mediaspherebackend.aim.interfaces.rest.resources.UserResource;
import com.acme.mediaspherebackend.aim.interfaces.rest.transform.AuthenticateUserResourceFromEntityAssembler;
import com.acme.mediaspherebackend.aim.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import com.acme.mediaspherebackend.aim.interfaces.rest.transform.UserCommandFromSignUpResourceAssembler;
import com.acme.mediaspherebackend.aim.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "Sign up")
    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource signUpResource){
        var signUpCommand = UserCommandFromSignUpResourceAssembler.toCommandFromResource(signUpResource);
        var user = userCommandService.handle(signUpCommand);

        if(user.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(
                user.get()
        );
        return ResponseEntity.ok(userResource);
    }

    @Operation(summary = "Get all users")
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userQueryService.handle(new GetAllUsersQuery()));
    }
}
