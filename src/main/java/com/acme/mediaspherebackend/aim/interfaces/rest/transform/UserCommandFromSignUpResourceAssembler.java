package com.acme.mediaspherebackend.aim.interfaces.rest.transform;

import com.acme.mediaspherebackend.aim.domain.model.commands.SignUpCommand;
import com.acme.mediaspherebackend.aim.interfaces.rest.resources.SignUpResource;

public class UserCommandFromSignUpResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource signUpResource){
        return new SignUpCommand(signUpResource.email(), signUpResource.fullName(), signUpResource.password());
    }
}
