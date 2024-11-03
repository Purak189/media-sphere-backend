package com.acme.mediaspherebackend.organization.domain.model.aggregates;

import com.acme.mediaspherebackend.organization.domain.model.commands.CreateOrganizationCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organization_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Membership> memberships =new ArrayList<>();

    public Organization(CreateOrganizationCommand command){
        this.name = command.name();
        this.description = command.description();
        this.memberships.add(command.membership());
    }

    public Organization() {

    }

    public void addMembership(Membership membership){
        this.memberships.add(membership);
    }

}
