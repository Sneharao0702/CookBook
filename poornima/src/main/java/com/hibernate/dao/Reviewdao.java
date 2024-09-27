package com.hibernate.dao;

import java.util.List;
import com.entitiy.Review;

public interface Reviewdao {

    void AddReview();
    
    // Get review by ID
    void getReviewById( );
    
    // Get all reviews
    List<Review> getAllReviews();
    
    
    // Delete a review by ID
    void deleteReview();
    
  
}
