package com.acme.mediaspherebackend.organization.infraestructure.persistence.jpa.repositories;

import com.acme.mediaspherebackend.organization.domain.model.aggregates.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {

}
