package com.ecommerce_bazar.entities;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 190)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public User(String email, @Nullable String password) {
        this.email = email;
        this.password = password;
    }
}
