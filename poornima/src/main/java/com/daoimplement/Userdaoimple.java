package com.daoimplement;


	import java.util.List;
	import java.util.Scanner;
	import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entitiy.User;
import com.helper.Helper;
import com.hibernate.dao.Userdao;

import jakarta.persistence.TypedQuery;


	//create Dao impl- implement interface - logic of CRUD method
	public class Userdaoimple implements  Userdao{
		Session session = Helper.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Scanner sc = new Scanner(System.in);	
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator v = vf.getValidator();

		public void  registeruser() {
		    try {
		        // Get user input
		       
		        System.out.println("Enter your username:");
		        String username = sc.next();

		        System.out.println("Enter your email id:");
		        String email = sc.next();

		        System.out.println("Enter your password:");
		        String password = sc.next();

		        // To handle addresses with spaces
		        sc.nextLine(); // Consume newline left-over from nextLong()

		        System.out.println("Enter your Address:");
		        String addr = sc.nextLine();
		        
		        System.out.println("Enter your ContactNumber:");
		        String contact = sc.nextLine();
		        // Create and set values for Student entity
		        User u = new User();
		        u.setUsername(username);
		        u.setEmail(email);
		        u.setContactnumber(contact);
		        u.setAddress(addr);
		        u.setPassword(password);
		      
		        
		       
		        
		        // Validate the user object
		        Set<ConstraintViolation< User>> vl = v.validate(u);
		       
		        if (vl.isEmpty()) {
		            // Save and commit if no validation errors
		            session.save(u);
		            t.commit();
		            System.out.println("User registered successfully.");
		        } else {
		            // Print validation errors
		            for (ConstraintViolation< User > violation : vl) {
		                System.out.println(violation.getMessage());
		            }
		            t.rollback();// Rollback transaction if validation fails
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}



		
			 public void viewuserById() {
			        try {
			            // Prompt user to enter the ID
			            System.out.println("Enter user ID to view:");
			            int userId = sc.nextInt();

			            // Fetch the user with the given ID
			            User user = session.get(User.class, userId);

			            // Check if the user is null
			            if (user == null) {
			                System.out.println("No user found with ID: " + userId);
			            } else {
			                // Print the details of the user
			                System.out.println(user.getUserID() + " " +
			                                   user.getUsername() + " " +
			                                   user.getEmail() + " " +
			                                   user.getContactnumber()+ " "+
			                                   user.getAddress()+ " " +
			                                   user.getPassword()
			                                  );
			            }
			        }  catch (Exception e) {
			            // Print a more descriptive error message
			            System.out.println("An error occurred while retrieving user information: " + e.getMessage());
			        }
			    }





			 @Override
				public void edituser() 
			 {
				 boolean isTransactionActive = false;
				 try {
					System.out.println("Enter user id");
					int e1 = sc.nextInt();

					User u2 = session.get(User.class, e1);

					if (u2 != null) {
						System.out.println("What details you want to modify");
						System.out.println("1. contactNumber ");
						System.out.println("2.  Email");
						System.out.println("3. Address");

						int choice = sc.nextInt();
						sc.nextLine();
						switch (choice) {
						case 1:
							System.out.println("Enter update contactNumber");
							String nwcontactNumber = sc.nextLine();

							u2.setContactnumber(nwcontactNumber);
							break;

						case 2:
							System.out.println("Enter Email");
							String nwemail = sc.nextLine();

							u2.setEmail(nwemail);
							break;

						case 3:
							System.out.println("Enter updated Address");
							String Address = sc.nextLine();
							u2.setAddress(Address);
							break;

						default:
							System.out.println("invalid choice ");
							return;
						}
						session.update(u2);
						t.commit();
						System.out.println("details updated ");
					} else {
						System.out.println("No such User exists ");
					}
}
					catch (Exception e) {
				        if (t!= null) {
				            t.rollback(); // Rollback if an error occurs
				        } System.out.println("Error updating user: " + e.getMessage());
				        e.printStackTrace(); // Log the error
				    }

				}



			@Override
			public List<User> getAlluser() {
				// TODO Auto-generated method stub
				try {
					TypedQuery<User> query = session.createQuery("FROM User", User.class);
					List<User> user = query.getResultList();

					if (user.isEmpty()) {
						System.out.println("No user found.");
					} else {
						for (User b1 : user) {
							System.out.println(
									b1.getUserID() + " " + b1.getUsername() + " " + b1.getEmail() + " " + b1.getContactnumber()
											+ " " + b1.getAddress() + " " + b1.getPassword());
						}
					}
					return user;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
				
			
			}
	}

	