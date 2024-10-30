package com.acme.mediaspherebackend.aim.interfaces.rest.transform;

import com.acme.mediaspherebackend.aim.domain.model.commands.SignInCommand;
import com.acme.mediaspherebackend.aim.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.email(), signInResource.password());
    }
}
