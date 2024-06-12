package com.todorkrastev.krastevsgym.model.entity;

import com.todorkrastev.krastevsgym.model.enums.UserRolesEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private UserRolesEnum name;

    public RoleEntity() {}

    public long getId() {
        return id;
    }

    public RoleEntity setId(long id) {
        this.id = id;
        return this;
    }

    public UserRolesEnum getName() {
        return name;
    }

    public RoleEntity setName(UserRolesEnum name) {
        this.name = name;
        return this;
    }
}
