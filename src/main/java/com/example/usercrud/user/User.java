package com.example.usercrud.user;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "users")
@Data @NoArgsConstructor @AllArgsConstructor @Builder

public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true, length=50)
    private String username;

    @Column(nullable=false, unique=true, length=120)
    private String email;

    @Column(nullable=false, length=100)
    private String fullName;

}
