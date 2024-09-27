package receipemanagement.poornima;

import java.util.Scanner;

import com.color.Color;
import com.daoimplement.Admindaoimple;

import com.daoimplement.Receipedaoimple;
import com.daoimplement.Reviewdaoimple;
import com.daoimplement.Userdaoimple;

public class App {
	public static void main(String args[]) {
		Color c=new Color();
		Admindaoimple admin = new Admindaoimple();
		Userdaoimple us = new Userdaoimple();
		Receipedaoimple receipe = new Receipedaoimple();
		Reviewdaoimple review = new Reviewdaoimple();
		Scanner sc = new Scanner(System.in);
		boolean loggedIn = false;

		// Admin Login
		while (!loggedIn) {
			System.out.print(c.BOLD_GREEN+"Enter admin username: "+c.RESET);
			String username = sc.next();

			System.out.print(c.BOLD_GREEN+"Enter admin password: "+c.RESET);
			String password = sc.next();

			loggedIn = admin.login(username, password);

			if (!loggedIn) {
				System.out.println("Invalid username or password. Please try again.");
			}
		}

		System.out.println(c.BOLD_CYAN+"Login successful. Welcome!");

		try {
			char mainMenuChoice;
			do {
				System.out.println(c.BOLD_PURPLE+"Main Menu");
				System.out.println(c.BOLD_PURPLE+"1. User");
				System.out.println(c.BOLD_PURPLE+"2. Receipe");
				System.out.println(c.BOLD_PURPLE+"3. Review");
				System.out.println(c.BOLD_PURPLE+"4. Exit");
				System.out.print("Enter choice: ");

				while (!sc.hasNextInt()) {
					System.out.println("Invalid input. Please enter a number between 1 and 4.");
					sc.next(); // clear invalid input
				}

				int mainChoice = sc.nextInt();
				sc.nextLine(); // consume the newline left-over
				switch (mainChoice) {
				case 1:
					// Student Submenu
					UserMenu(us);
					break;
				case 2:
					// Teacher Submenu
					ReceipeMenu(receipe, sc);
					break;
				case 3:
					// batch menu
					ReviewMenu(review, sc);
					break;
				case 4:
					System.out.println("Exiting... Thank you!");
					return;
				default:
					System.out.println("Incorrect choice entered. Please try again.");
					break;
				}
				System.out.print("Do you want to return to the main menu? (Y/N): ");
				String input = sc.nextLine().trim();
				mainMenuChoice = (input.isEmpty() || (input.charAt(0) != 'Y' && input.charAt(0) != 'y')) ? 'N' : 'Y';
			} while (mainMenuChoice == 'Y' || mainMenuChoice == 'y');

			System.out.println("Thank You..");

		} finally {
			sc.close(); // Ensure that the Scanner is closed
		}
	}

	// userMenu
	private static void UserMenu(Userdaoimple user) {
		char UserMenuChoice;
		do {
			Color c=new Color();
			System.out.println(c.BOLD_YELLOW+"User menu");
			System.out.println(c.BOLD_YELLOW+"1. Register User");
			System.out.println(c.BOLD_YELLOW+"2. View User by Id");
			System.out.println(c.BOLD_YELLOW+"3. Edit User");
			System.out.println(c.BOLD_YELLOW+"4. View All User");
			System.out.println(c.BOLD_YELLOW+"5. Back to Main Menu");
			System.out.print(c.BOLD_RED+"Enter choice: ");
			Scanner sc = new Scanner(System.in);
			while (!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number between 1 and 5.");
				sc.next(); // clear invalid input
			}
			int choice = sc.nextInt();
			sc.nextLine(); // consume the newline left-over

			switch (choice) {
			case 1:
				user.registeruser();
				break;
			case 2:
				user.viewuserById();
				break;
			case 3:
				user.edituser();
				break;
			case 4:
				user.getAlluser();
				break;
			case 5:
				return; // Back to main menu
			default:
				System.out.println("Incorrect choice entered. Please try again.");
				break;
			}

			System.out.print("Do you want to continue in the user  Menu? (Y/N): ");
			String input = sc.nextLine().trim();
			UserMenuChoice = (input.isEmpty() || (input.charAt(0) != 'Y' && input.charAt(0) != 'y')) ? 'N' : 'Y';

		} while (UserMenuChoice == 'Y' || UserMenuChoice == 'y');
	}

	// Teacher Menu
	private static void ReceipeMenu(Receipedaoimple receipe, Scanner sc) {
		char ReceipeMenuChoice;
		do {
			Color c=new Color();
			System.out.println(c.BOLD_CYAN+"Receipe Menu");
			System.out.println(c.BOLD_CYAN+"1. Add Recipe ");
			System.out.println(c.BOLD_CYAN+"2. find Receipe by Name");
			System.out.println(c.BOLD_CYAN+"3. View All  Recipes");
			System.out.println(c.BOLD_CYAN+"4. Update Recipe");
			System.out.println(c.BOLD_CYAN+"5. Delete Recipe");
			System.out.println(c.BOLD_CYAN+"6. Back to Main Menu");
			System.out.print("Enter choice: ");
			while (!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number between 1 and 6.");
				sc.next(); // clear invalid input
			}
			int choice = sc.nextInt();
			sc.nextLine(); // consume the newline left-over

			switch (choice) {
			case 1:
				receipe.AddRecipe();
				break;
			case 2:
				receipe.findReceipeByName();
				break;
			case 3:
				receipe.getAllRecipes();
				break;
			case 4:
				receipe.updateRecipe();
				break;
			case 5:
				receipe.deleteRecipe();
				break;
			case 6:
				return; // Back to main menu
			default:
				System.out.println("Incorrect choice entered. Please try again.");
				break;
			}

			System.out.print("Do you want to continue in the user  Menu? (Y/N): ");
			String input = sc.nextLine().trim();
			ReceipeMenuChoice = (input.isEmpty() || (input.charAt(0) != 'Y' && input.charAt(0) != 'y')) ? 'N' : 'Y';

		} while (ReceipeMenuChoice == 'Y' || ReceipeMenuChoice == 'y');
	}

	

	// userMenu
	private static void ReviewMenu(Reviewdaoimple review, Scanner sc) {
		char ReviewMenuChoice;
		do {
			Color c=new Color(); 
			System.out.println(c.BOLD_CYAN+"Review menu");
			System.out.println(c.BOLD_CYAN+"1. Add review");
			System.out.println(c.BOLD_CYAN+"2. View review by Id ");
			System.out.println(c.BOLD_CYAN+"3. View all review");
			System.out.println(c.BOLD_CYAN+"4. Delete review");
			System.out.println(c.BOLD_CYAN+"5. Back to Main Menu");
			System.out.print("Enter choice: ");

			while (!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number between 1 and 5.");
				sc.next(); // clear invalid input
			}
			int choice = sc.nextInt();
			sc.nextLine(); // consume the newline left-over

			switch (choice) {
			case 1:
				review.AddReview();
				break;
			case 2:
				review.getReviewById();
				break;
			case 3:
				review.getAllReviews();
				break;
			
			case 4:
				review.deleteReview();
				break;
			case 5:
				return; // Back to main menu
			default:
				System.out.println("Incorrect choice entered. Please try again.");
				break;
			}

			System.out.print("Do you want to continue in the user  Menu? (Y/N): ");
			String input = sc.nextLine().trim();
			ReviewMenuChoice = (input.isEmpty() || (input.charAt(0) != 'Y' && input.charAt(0) != 'y')) ? 'N' : 'Y';

		} while (ReviewMenuChoice == 'Y' || ReviewMenuChoice == 'y');
	}

}
