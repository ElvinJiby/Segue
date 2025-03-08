package org.elvinjiby.marketplace.model;

import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UserRole role;

    public Role() {}

    public Role(UserRole role) {
        this.role = role;
    }

    public Long getId() { return id; }
    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
}
