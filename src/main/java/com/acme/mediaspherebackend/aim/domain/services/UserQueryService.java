package com.acme.mediaspherebackend.aim.domain.services;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.aim.domain.model.queries.GetAllUsersQuery;

import java.util.List;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
}
