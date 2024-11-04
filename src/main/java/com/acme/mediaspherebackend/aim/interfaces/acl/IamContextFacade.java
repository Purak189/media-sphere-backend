package com.acme.mediaspherebackend.aim.interfaces.acl;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.aim.domain.model.queries.GetUserByEmailQuery;
import com.acme.mediaspherebackend.aim.domain.model.queries.GetUserByIdQuery;
import com.acme.mediaspherebackend.aim.domain.services.UserCommandService;
import com.acme.mediaspherebackend.aim.domain.services.UserQueryService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IamContextFacade {
    public final UserCommandService userCommandService;
    public final UserQueryService userQueryService;

    public IamContextFacade(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    public User getUserById(long userId){
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = this.userQueryService.handle(getUserByIdQuery);
        return user.orElse(null);
    }

    public Optional<User> getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            return this.userQueryService.handle(new GetUserByEmailQuery(email));
        } else {
            throw new IllegalStateException("User not authenticated.");
        }
    }
}
