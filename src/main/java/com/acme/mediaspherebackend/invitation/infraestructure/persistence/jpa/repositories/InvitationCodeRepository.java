package com.acme.mediaspherebackend.invitation.infraestructure.persistence.jpa.repositories;

import com.acme.mediaspherebackend.invitation.domain.model.aggregates.InvitationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationCodeRepository extends JpaRepository<InvitationCode, Long> {
    boolean existsByCode(String code);
    boolean existsByOrganization_Id(Long organizationId);
    InvitationCode findByOrganization_Id(Long organizationId);
}
