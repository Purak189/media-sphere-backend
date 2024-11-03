package com.acme.mediaspherebackend.aim.application.internal.queryservices;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.aim.domain.model.queries.GetAllUsersQuery;
import com.acme.mediaspherebackend.aim.domain.model.queries.GetUserByIdQuery;
import com.acme.mediaspherebackend.aim.domain.services.UserQueryService;
import com.acme.mediaspherebackend.aim.infraestructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }
}
