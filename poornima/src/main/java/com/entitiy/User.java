package com.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @NotBlank(message = "Username cannot be blank")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true)
    private String email;

    @Size(max = 15, message = "Contact number must be at most 15 characters")
    private String contactnumber;

    private String address;

    @NotBlank(message = "Password cannot be blank")
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews;

    @OneToMany(mappedBy = "user")
    private Set<Receipe> receipes;
}
