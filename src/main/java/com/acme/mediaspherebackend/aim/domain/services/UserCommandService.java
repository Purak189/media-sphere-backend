package com.acme.mediaspherebackend.aim.domain.services;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.aim.domain.model.commands.SignUpCommand;
import com.acme.mediaspherebackend.aim.domain.model.commands.SignInCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpCommand command);
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
}
