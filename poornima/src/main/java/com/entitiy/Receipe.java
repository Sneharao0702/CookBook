package com.entitiy;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Setter
@Table(name = "recipe")
public class Receipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeID;

    @NotBlank(message = "Title cannot be blank")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Description cannot be blank")
    @Column(nullable = false)
    private String description;

    @NotBlank(message = "Ingredients cannot be blank")
    @Lob
    @Column(nullable = false)
    private String ingredients;

    @NotBlank(message = "Instructions cannot be blank")
    @Lob
    @Column(nullable = false)
    private String instructions;

    @NotNull(message = "Cooking time cannot be null")
    @Positive(message = "Cooking time must be a positive number")
    @Column(nullable = false)
    private int cookingTime;

    @NotNull(message = "Created date cannot be null")
    @Column(nullable = false)
    private Date createdDate ;

    @NotNull(message = "User cannot be null")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "receipe")
    private Set<Review> review;
}
