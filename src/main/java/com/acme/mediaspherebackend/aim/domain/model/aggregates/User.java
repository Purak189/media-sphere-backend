package com.acme.mediaspherebackend.aim.domain.model.aggregates;

import com.acme.mediaspherebackend.aim.domain.model.commands.SignUpCommand;
import com.acme.mediaspherebackend.organization.domain.model.aggregates.Membership;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String full_name;

    @Column(nullable = false)
    private String hashed_password;

    @OneToMany(mappedBy = "user")
    private List<Membership> membership = new ArrayList<>();

    public User(SignUpCommand command){
        this.email = command.email();
        this.full_name = command.full_name();
        this.hashed_password = command.hashed_password();
    }
    public User(){
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(user_id, user.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id);
    }
}

