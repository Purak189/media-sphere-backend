package com.acme.mediaspherebackend.invitation.domain.model.aggregates;

import com.acme.mediaspherebackend.organization.domain.model.aggregates.Organization;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class InvitationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invitation_code_id;

    @Column(nullable = false, unique = true, length = 6)
    private String code;

    @OneToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @Column(nullable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private LocalDateTime expired_at;

    public InvitationCode(){

    }
}
