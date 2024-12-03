package com.acme.mediaspherebackend.organization.domain.model.aggregates;

import com.acme.mediaspherebackend.aim.domain.model.aggregates.User;
import com.acme.mediaspherebackend.organization.domain.model.commands.CreateMembershipCommand;
import com.acme.mediaspherebackend.organization.domain.model.valueobjects.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long membership_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "organization_id", referencedColumnName = "id", nullable = false)
    private Organization organization;

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
        this.organization = command.organization();
    }
    @Override
    public String toString() {
        return "Membership{" +
                "membership_id=" + membership_id +
                ", organization=" + organization.getName() +
                ", user=" + user.getEmail() +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membership that = (Membership) o;
        return Objects.equals(membership_id, that.membership_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(membership_id);
    }

}
