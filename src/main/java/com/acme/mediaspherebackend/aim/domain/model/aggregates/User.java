package com.acme.mediaspherebackend.aim.domain.model.aggregates;

import com.acme.mediaspherebackend.aim.domain.model.commands.SignUpCommand;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String full_name;

    @Column(nullable = false)
    private String hashed_password;

//    @OneToMany(mappedBy = "user")
//    private List<Membership> memberships = new ArrayList<>()

    public User(SignUpCommand command){
        this.email = command.email();
        this.full_name = command.full_name();
        this.hashed_password = command.hashed_password();
    }
    public User(){

    }
}

