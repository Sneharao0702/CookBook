package com.entitiy;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "review")
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewID;

    @NotNull(message = "Rating cannot be null")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    private int rating;

    @NotBlank(message = "Title cannot be blank")
    private String title; 

    @Lob
    private String comment;

    @NotNull(message = "Review date cannot be null")
    @Temporal(TemporalType.DATE)
    private Date reviewDate = new Date(); // Initialize to current date

    @NotNull(message = "User cannot be null")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull(message = "Recipe cannot be null")
    @ManyToOne
    @JoinColumn(name = "receipe_id", nullable = false)
    private Receipe receipe;

    // Optionally, you can add a constructor to set required fields
}
