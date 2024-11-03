package com.acme.mediaspherebackend.organization.infraestructure.persistence.jpa.repositories;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.organization.domain.model.aggregates.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Query("SELECT o FROM Organization o JOIN o.memberships m WHERE m.user.user_id = :userId")
    List<Organization> findAllByUserId(@Param("userId") String userId);
}
