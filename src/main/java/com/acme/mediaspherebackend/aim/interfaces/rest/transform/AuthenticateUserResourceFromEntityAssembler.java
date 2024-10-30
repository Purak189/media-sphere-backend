package com.acme.mediaspherebackend.aim.interfaces.rest.transform;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.aim.interfaces.rest.resources.AuthenticateUserResource;

public class AuthenticateUserResourceFromEntityAssembler {
    public static AuthenticateUserResource toResourceFromEntity(User user){
        return new AuthenticateUserResource(user.getUser_id(), user.getFull_name(), user.getEmail());
    }
}
