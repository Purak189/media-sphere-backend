package com.acme.mediaspherebackend.aim.application.internal.commandservices;

import com.acme.mediaspherebackend.aim.application.internal.outboundedservices.hashing.HashingService;
import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.aim.domain.model.commands.SignInCommand;
import com.acme.mediaspherebackend.aim.domain.model.commands.SignUpCommand;
import com.acme.mediaspherebackend.aim.domain.services.UserCommandService;
import com.acme.mediaspherebackend.aim.infraestructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;

    private final HashingService hashingService;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
    }


    @Override
    public Optional<User> handle(SignUpCommand command) {
        String email = command.email();
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        });
        User user = new User( new SignUpCommand(
                email,
                command.full_name(),
                hashingService.encode(command.hashed_password())
        ));
        userRepository.save(user);

        return Optional.of(user);
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        if (!hashingService.matches(command.password(),user.get().getHashed_password())) {
            throw new IllegalArgumentException("Invalid password");
        }
        return Optional.of(ImmutablePair.of(user.get(), "token"));
    }
}
