package com.acme.mediaspherebackend.organization.domain.model.aggregates;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.organization.domain.model.commands.CreateMembershipCommand;
import com.acme.mediaspherebackend.organization.domain.model.valueobjects.Role;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membership_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public Membership(User user, Role role){
        this.user = user;
        this.role = role;
    }
    public Membership(){

    }
    public Membership(CreateMembershipCommand command){
        this.user = command.user();
        this.role = command.role();
    }
}
