package main;

import main.FileManager.QuestionAnswerModel;
import main.FileManager.Reader;
import java.util.Scanner;
import java.util.*;

public class GameManager {


    PlayerModel player;
    String[] categories = new String[]{"geography", "history", "movie", "science", "sport"};
    HashMap<String, LinkedList<QuestionAnswerModel>> categoryQuestionAnswerHashMap = new HashMap<>();
    private Random random = new Random();

    /**
     * @param player object from PlayerModel class
     *               Constructor of GameManager class where each respective category's list is populated before game start
     */
    public GameManager(PlayerModel player) {
        this.player = player;

        /**
         * For Loop which goes through the categories specified in categories
         */
        for (int i = 0; i < this.categories.length; i++) {
            //Takes the 'current category'
            String currentCategory = this.categories[i];
            //Creates a Linked List for the 'current category'
            LinkedList<QuestionAnswerModel> categoryQA = Reader.readAllFromCsv(currentCategory);
            /**
             * @key Current iterated 'category' from categories array
             * Add the LinkedList to a hashmap with the key to differentiate the different categories
             */
            categoryQuestionAnswerHashMap.put(currentCategory, categoryQA);
        }
    }

    /**
     * This method is called if list is not empty
     */
    public QuestionAnswerModel getRandomQuestion() {
        return this.getRandomQuestion(-1);
    }

    private QuestionAnswerModel getRandomQuestion(int count) {
        //Increment count for every run
        ++count;
        /*
         * Purpose of this if condition is to prevent infinite looping if all CSV files are empty
         * Loops to twice the amount of categories there are
         */
        if (count > (categories.length * 2)) return null;

        //Generate random number with a maximum value according to how many categories are present
        int randomCategoryIndex = generateRandomNumber(this.categories.length);
        //Get that current category and set it to a string
        String currentCategory = this.categories[randomCategoryIndex];

        //Get current category list from hash map
        LinkedList<QuestionAnswerModel> currentQuestionAnswerModelList = categoryQuestionAnswerHashMap.get(currentCategory);
        //If List is empty re-call the method that increments the count
        if (currentQuestionAnswerModelList.isEmpty()) return getRandomQuestion(count);

        //Generating random question index according to list length
        int randomQuestionIndex = generateRandomNumber(currentQuestionAnswerModelList.size() - 1);

        ///Get random question according to that index
        QuestionAnswerModel currentQuestionAnswer = currentQuestionAnswerModelList.get(randomQuestionIndex);

        //Remove from list so question is not returned again
        currentQuestionAnswerModelList.remove(currentQuestionAnswer);

        //Return question
        return currentQuestionAnswer;
    }

    /**
     * @param maxValue param which specifies the maximum value that the random can generate
     *                 Generation of Random Number
     */
    private int generateRandomNumber(int maxValue) {
        return random.nextInt(maxValue);
    }

    Scanner scn = new Scanner(System.in);

    public boolean runGame() {
            QuestionAnswerModel currentQuestionAnswer = this.getRandomQuestion();
            System.out.println(currentQuestionAnswer.getQuestion());
            System.out.println("Enter your answer");
            String userAnswer = scn.nextLine();
            if(currentQuestionAnswer.isAnswerCorrect(userAnswer)) {
                player.incrementQuestionsCorrect();
                System.out.println("You have gotten a question correct! You now have " + player.getQuestionsCorrect() + " correct questions.");
                if(player.getQuestionsCorrect() >= player.MAX_QUESTION_LIMIT) {
                    System.out.println("Congrats! You have won the game");
                    return false;
                }
            } else {
                player.decrementNumberOfLives();
                System.out.println("You have gotten a question wrong!The correct answer was " + currentQuestionAnswer.getAnswer() + " You now have " + player.getNumberOfLives() + " lives left.");
                if(player.getNumberOfLives() <= 0 ) {
                    System.out.println("You have unfortunately lost the game");
                    return false;
                }
            }

        return true;
    }

    public void gameLoop() {
        boolean continueGame;
        {
            continueGame = runGame();
            runGame();
        }while(continueGame);
        scn.close();
    }




}