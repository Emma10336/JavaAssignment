package main;

import main.FileManager.Deleter;
import main.FileManager.QuestionAnswerModel;
import main.FileManager.Reader;
import main.FileManager.Writer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Class responsible for managing the menu and overall game logic.
 */
public class MenuManager {

    // Scanner for user input
    static Scanner scanner = new Scanner(System.in);

    /**
     * Initiates the game logic, including player information input and game loop execution.
     */
    static public void gameLogic() {
        // Get player information
        System.out.println("Kindly enter your name");
        String playerName = scanner.nextLine();
        System.out.println("Kindly enter amount of desired lives, limited to 10");
        int MAX_NUMBER_OF_LIVES = scanner.nextInt();
        if(MAX_NUMBER_OF_LIVES <= 0 || MAX_NUMBER_OF_LIVES > 10) {
            System.out.println("Invalid input: " + MAX_NUMBER_OF_LIVES );
            System.out.println(("Enter again"));
            MAX_NUMBER_OF_LIVES = scanner.nextInt();
        }

        System.out.println("Kindly entered max amount of questions to be answered correctly, limited to 20");
        int MAX_QUESTION_LIMIT = scanner.nextInt();
        if(MAX_QUESTION_LIMIT <= 0 || MAX_QUESTION_LIMIT > 10) {
            System.out.println("Invalid input: " + MAX_QUESTION_LIMIT );
            System.out.println(("Enter again"));
            MAX_QUESTION_LIMIT = scanner.nextInt();
        }

        PlayerModel player = new PlayerModel(playerName, MAX_NUMBER_OF_LIVES, MAX_QUESTION_LIMIT);

        // Create GameManager and run the game loop
        GameManager gameManager = new GameManager(player);
        boolean continueGame;

        do {
            continueGame = gameManager.runGame();
        } while (continueGame);

        // Close the scanner
        scanner.close();
    }

    // Array of available categories
    static String[] categories = new String[]{"geography", "history", "movie", "science", "sport"};

    /**
     * Reads all entries from a specified file and prints them to the console.
     */
    static public void callReadMethod() {
        //Ask user what file they would like to read from
        System.out.println("What file would you like to read from? " + Arrays.toString(categories));
        String fileName = scanner.nextLine();
        //Create linked list
        LinkedList<QuestionAnswerModel> file = new LinkedList<>();
        //Populate linked list and print contents
        file = Reader.readAllFromCsv(fileName);
        System.out.println(file);
    }

    /**
     * Adds one or multiple questions to a specified file based on user input.
     */
    static public void addMultipleOrOneQuestionToCSV() {
        //Ask user which file they would like to add to
        System.out.println("Which file would you like to write to? " + Arrays.toString(categories));
        String fileName = scanner.nextLine();
        //Ask user how many questions they would like to be added
        //This determines if the method is called once, or multiple times
        System.out.println("How many questions would you like to add to your file?");
        int numberOfQuestionsToBeEntered = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        //Number of questions to be answered is greater than 1 1
        if (numberOfQuestionsToBeEntered > 1) {
            for (int i = 0; i < numberOfQuestionsToBeEntered; i++) {
                //Set current QuestionAnswerModel instance according to values returned from setQuestionAndAnswer() function
                QuestionAnswerModel questionAnswer = setQuestionAndAnswer();
                //If addQAToCsv returns true (meaning successful addition) print success message
                if (Writer.addQAToCsv(questionAnswer, fileName)) {
                    System.out.println("Question has been successfully added");
                }
                //Writer method handles question not being successfully added and returns own message
            }
            //One question to be added
        } else if (numberOfQuestionsToBeEntered == 1) {
            //Set QuestionAnswerModel instance according to values returned from setQuestionAndAnswer() function
            QuestionAnswerModel questionAnswer = setQuestionAndAnswer();
            //Same as above, if return is true there is a successful addition
            if (Writer.addQAToCsv(questionAnswer, fileName)) {
                System.out.println("Question has been successfully added");
            }
            //If number with minus value or 0 is entered, return invalid input method
        } else {
            System.out.println("Invalid number");
        }
    }

    /**
     * Sets the question and answer based on user input.
     *
     * @return The QuestionAnswerModel containing the user-provided question and answer.
     */
    static private QuestionAnswerModel setQuestionAndAnswer() {
        //Ask user for question input
        System.out.println("What is your question?");
        String question = scanner.nextLine();
        //Check that question is not empty
        if ((question == null) || question.isEmpty()) {
            System.out.println("Empty inputs are not allowed, Kindly re-enter your question");
            question = scanner.nextLine();
        }
        //if question is not empty, ask user for answer input
        System.out.println("What is your answer?");
        String answer = scanner.nextLine();
        //Check that answer is not empty
        if (answer == null || answer.isEmpty()) {
            System.out.println("Empty inputs are not allowed, Kindly re-enter your answer");
            question = scanner.nextLine();
        }
        //Create the QuestionAnswerModel through constructor and return
        return new QuestionAnswerModel(question, answer);
    }

    /**
     * Deletes a row from a specified file based on user input.
     */
    static public void callDeleteMethod() {
        //Choose file
        System.out.println("What file would you like to delete a row from?");
        String fileName = scanner.nextLine();
        //Check that input is not empty
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("Input must not be empty");
            System.out.println("Enter again.");
            fileName = scanner.nextLine();
        }

        //Ask user for Row ID input
        System.out.println("What row would you like to delete? Provide Row ID");
        int id = scanner.nextInt();
        if (id <= 0) {
            //Check that row id is higher than 0
            System.out.println("Row ID must be higher than 0");
            System.out.println("Enter again.");
            id = scanner.nextInt();
        }
        //Create QuestionAnswerModel with the id entered
        QuestionAnswerModel questionAnswer = new QuestionAnswerModel(id);
        //Call deleter, and check if returns true
        //Deleter method implements error handling
        if (Deleter.deleteRow(questionAnswer, fileName)) {
            //If yes return successful message
            System.out.println("Successfully deleted question with Row ID: " + id +
                    " from file name: " + fileName + ".");
        }
    }
}