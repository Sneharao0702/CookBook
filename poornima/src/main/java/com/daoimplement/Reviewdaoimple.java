package com.daoimplement;


import java.util.List;
import java.util.Scanner;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.entitiy.Receipe;
import com.entitiy.Review;
import com.entitiy.User;
import com.helper.Helper;
import com.hibernate.dao.Reviewdao;
  // Utility class to get the Hibernate SessionFactory

public class Reviewdaoimple implements Reviewdao {
	
	Session session = Helper.getSessionFactory().openSession();
	Transaction t = session.beginTransaction();
	Scanner sc = new Scanner(System.in);	
	ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
	Validator v = vf.getValidator();
    SessionFactory sessionFactory = null;


	@Override
	public void AddReview() {
		// TODO Auto-generated method stub
		  try {
	            // Start the transaction
	            

	            // Prompt the user for input
	            
	            System.out.print("Enter title: ");
	            String title = sc.nextLine();
	            
	            System.out.print("Enter rating (1-5): ");
	            int rating = sc.nextInt();
	            sc.nextLine(); // Consume newline

	          

	            System.out.print("Enter comment: ");
	            String comment = sc.nextLine();

	            System.out.print("Enter review date (yyyy-mm-dd): ");
	            String reviewDateString = sc.nextLine();
	            
	            System.out.println("Enter your Userid:");
		        int userid = sc.nextInt();
		        User user = session.get(User.class, userid);
		        
		        System.out.println("Enter your Receipeid:");
		        int receipeid = sc.nextInt();
		        Receipe Receipeid = session.get(Receipe.class, receipeid);

	            // Parse reviewDateString into a Date object
	            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date reviewDate = sdf.parse(reviewDateString);

	            // Create a new Review object
	            Review review = new Review();
	            review.setRating(rating);
	            review.setTitle(title);
	            review.setComment(comment);
	            review.setReviewDate(reviewDate);
	            review.setUser(user);
	            review.setReceipe(Receipeid);

	            // Save the Review object
	            session.save(review);

	            // Commit the transaction
	            t.commit();

	            System.out.println("Review added successfully!");

	        } catch (Exception e) {
	            // Rollback the transaction if there is an exception
	            if (t != null) t.rollback();
	            e.printStackTrace();
	        } finally {
	            // Close the session
	            session.close();
	        }
		
	}
	
	 @Override
	    public void getReviewById() {
		 try {
	            // Prompt user to enter the ID
	            System.out.println("Enter Review ID to view:");
	            int reviewid = sc.nextInt();

	            // Fetch the user with the given ID
	            Review review = session.get(Review.class, reviewid);

	            // Check if the user is null
	            if (review == null) {
	                System.out.println("No Review found with ID: " + reviewid);
	            } else {
	                // Print the details of the user
	                System.out.println("ReviewID"+review.getReviewID() + "\n " +
	                		"Getrating:"+review.getRating()+ "\n " +
	                		"Title:"+review.getTitle()+ "\n " +
	                		"Comments:"+review.getComment()+ " \n"+
	                		"Date:"+review.getReviewDate()
	                                  );
	            }
	        }  catch (Exception e) {
	            // Print a more descriptive error message
	            System.out.println("An error occurred while retrieving user information: " + e.getMessage());
	        }
	      
	    }
   
    @Override
    public List<Review> getAllReviews() {
       
        try {
            Query<Review> query = session.createQuery("from Review", Review.class);
            List<Review> review = query.list();
            
            if(review.isEmpty()) {
            	System.out.println("No review found");
            }else {
            	for(Review r:review) {
            		System.out.println("ReviewID"+r.getReviewID()+ "\n "+ 
            				"Rating:"+r.getRating()+ "\n "+
            				"Title:"+r.getTitle()+ "\n "+
            				"Comments:"+r.getComment()+ "\n"+
            				"Date:"+r.getReviewDate()+"\n");
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    

    @Override
    public void deleteReview() {
       
    	try {
    	  System.out.println("Enter Review ID to delete:");
          int reviewId = sc.nextInt();
          
          // Fetch the review with the given ID
          Review review = session.get(Review.class, reviewId);
          
          if (review == null) {
              // Review not found
              System.out.println("No Review found with ID: " + reviewId);
          } else {
              // Delete the review
              session.delete(review);
              System.out.println("Review with ID " + reviewId + " deleted successfully.");
          }
          
          // Commit the transaction
          t.commit();
          
      } catch (Exception e) {
          // Rollback the transaction in case of an exception
          if (t!= null) {
              t.rollback();
          }
          e.printStackTrace();
          System.out.println("An error occurred while deleting the review: " + e.getMessage());
      } finally {
          // Close the session
          if (session != null) {
              session.close();
          }
      }

    }

   

	


}
