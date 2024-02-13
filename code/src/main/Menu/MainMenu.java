package main.Menu;

import main.MenuManager;

import java.util.Scanner;

public class MainMenu {
    public void mainMenu(){
        Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Start a new game");
            System.out.println("2. Read questions");
            System.out.println("3. Write new questions");
            System.out.println("4. Delete a question");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				MenuManager.gameLogic();
				exit = true;
				break;
			case 2:
				MenuManager.callReadMethod();
				exit = true;
				break;
			case 3:
				MenuManager.addMultipleOrOneQuestionToCSV();
				exit = true;
				break;
			case 4:
				MenuManager.callDeleteMethod();
				exit = true;
				break;
			case 5:
				exit = true;
				break;
			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 5.");
			}
        }
    }
}
