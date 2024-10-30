package com.acme.mediaspherebackend.aim.interfaces.rest.transform;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.aim.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user){
        return new UserResource(user.getUser_id(), user.getFull_name(), user.getEmail());
    }
}
