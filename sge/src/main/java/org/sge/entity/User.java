package org.sge.entity;

import jakarta.persistence.*;
import lombok.*;
import org.sge.enums.AuthProvider;
import org.sge.enums.Role;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean active = true;

    @OneToOne(mappedBy = "user")
    private Client client;
}
