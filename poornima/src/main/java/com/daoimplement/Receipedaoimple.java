package com.daoimplement;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.entitiy.Receipe;
import com.entitiy.Review;
import com.entitiy.User;
import com.helper.Helper;
import com.hibernate.dao.Receipedao;

import jakarta.persistence.TypedQuery;

public class Receipedaoimple implements Receipedao {

	Session session = Helper.getSessionFactory().openSession();
	Transaction t = session.beginTransaction();
	Scanner sc = new Scanner(System.in);	
	ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
	Validator v = vf.getValidator();
    SessionFactory sessionFactory = null;

    @Override
    public void AddRecipe() {
        Transaction transaction = null;
        try (Session session = Helper.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Get user input
            System.out.println("Enter your Recipe Title:");
            String title = sc.nextLine();

            System.out.println("Enter your Description:");
            String description = sc.nextLine();

            System.out.println("Enter your Ingredients:");
            String ingredients = sc.nextLine();

            System.out.println("Enter your Instructions:");
            String instructions = sc.nextLine();

            System.out.println("Enter your CookingTime:");
            int cookingTime = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter your CreatedDate:");
            String createdDate = sc.next();

            System.out.println("Enter your Userid:");
            int userid = sc.nextInt();
            User user = session.get(User.class, userid);

            System.out.println("Enter your Reviewid:");
            int reviewid = sc.nextInt();
            Review review = session.get(Review.class, reviewid);

            // Create and set values for Receipe entity
            Receipe r = new Receipe();
            r.setTitle(title);
            r.setDescription(description);
            r.setIngredients(ingredients);
            r.setInstructions(instructions);
            r.setCookingTime(cookingTime);
            r.setCreatedDate(new Date());
            r.setUser(user);
            r.setReview(Set.of(review)); // Use Set.of() for single review

            // Validate the Receipe object
            Set<ConstraintViolation<Receipe>> vl = v.validate(r);

            if (vl.isEmpty()) {
                // Save and commit if no validation errors
                session.save(r);
                transaction.commit();
                System.out.println("Recipe registered successfully.");
            } else {
                // Print validation errors
                for (ConstraintViolation<Receipe> violation : vl) {
                    System.out.println(violation.getMessage());
                }
                transaction.rollback(); // Rollback transaction if validation fails
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void findReceipeByName() {
        try (Session session = Helper.getSessionFactory().openSession()) {
            System.out.println("Enter the Recipe Name:");
            String title = sc.nextLine();
            TypedQuery<Receipe> query = session.createQuery(
                "SELECT r FROM Receipe r WHERE r.title = :title", Receipe.class);
            query.setParameter("title", title);

            List<Receipe> receipes = query.getResultList();

            if (receipes.isEmpty()) {
                System.out.println("No Recipe found with Name: " + title);
            } else {
                for (Receipe r : receipes) {
                    if (r != null) {
                        System.out.println( "RecipeID:"+r.getRecipeID() + "\n " +
                        		"Title:"+r.getTitle() + "\n " +
                        		"Description:"+r.getDescription() + "\n" +
                           "Ingredients:"+ r.getIngredients() + "\n " +
                           "Instructions:"+ r.getInstructions() + "\n" +
                          "CookingTime:"+ r.getCookingTime() + "\n " +
                           "CreatedDate:"+ r.getCreatedDate());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving recipe information: " + e.getMessage());
        }
    }

    @Override
    public List<Receipe> getAllRecipes() {
        try (Session session = Helper.getSessionFactory().openSession()) {
            Query<Receipe> query = session.createQuery("FROM Receipe", Receipe.class);
            List<Receipe> receipes = query.list();

            if (receipes.isEmpty()) {
                System.out.println("No Recipe found");
            } else {
                for (Receipe r1 : receipes) {
                	 System.out.println( "RecipeID:"+r1.getRecipeID() + "\n " +
                     		"Title:"+r1.getTitle() + "\n " +
                     		"Description:"+r1.getDescription() + "\n" +
                        "Ingredients:"+ r1.getIngredients() + "\n " +
                        "Instructions:"+ r1.getInstructions() + "\n" +
                       "CookingTime:"+ r1.getCookingTime() + "\n " +
                        "CreatedDate:"+ r1.getCreatedDate()+"\n");
                }
            }
            return receipes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateRecipe() {
        Transaction transaction = null;

        try (Session session = Helper.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            System.out.println("Enter the Recipe ID to update:");
            int recipeID = sc.nextInt();
            sc.nextLine(); // Consume the leftover newline

            Receipe existingRecipe = session.get(Receipe.class, recipeID);

            if (existingRecipe != null) {
                System.out.println("Enter the new Recipe Name:");
                String newTitle = sc.nextLine();

                System.out.println("Enter the new Description:");
                String newDescription = sc.nextLine();
               
                System.out.println("Enter the new Recipe Ingredients:");
                String newIngredients = sc.nextLine();

                System.out.println("Enter the new Recipe Instructions:");
                String newInstructions = sc.nextLine();
                
                System.out.println("Enter the new Cooking Time (in minutes):");
                int newCookingTime = sc.nextInt();
                
                sc.nextLine();
                
                System.out.println("Enter your CreatedDate:");
                String createdDate = sc.nextLine();


                existingRecipe.setTitle(newTitle);
                existingRecipe.setDescription(newDescription);
                existingRecipe.setIngredients(newIngredients);
                existingRecipe.setInstructions(newInstructions);
              
                existingRecipe.setCookingTime(newCookingTime);
                existingRecipe.setCreatedDate(new Date());
                

                session.update(existingRecipe);
                transaction.commit();

                System.out.println("Recipe updated successfully.");
            } else {
                System.out.println("Recipe with ID " + recipeID + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

	@Override
	public void deleteRecipe() {
		Transaction transaction = null;
        try (Session session = Helper.getSessionFactory().openSession()) {
            // Start the transaction
            transaction = session.beginTransaction();
            System.out.println("Enter the Recipe ID:");
            int receipeId = sc.nextInt();
            // Find the recipe by ID
            Receipe recipe = session.get(Receipe.class, receipeId);
            if (recipe != null) {
                session.delete(recipe); // Delete the recipe
                System.out.println("Recipe deleted successfully");
            } else {
                System.out.println("Recipe not found");
            }

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback in case of error
            }
            System.out.println("Error deleting recipe: " + e.getMessage());
        }
    
	}
}
