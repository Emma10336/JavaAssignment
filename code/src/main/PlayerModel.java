package main;

public class PlayerModel {
    private String name;
    private int questionsCorrect;
    private int numberOfLives;

    public int MAX_NUMBER_OF_LIVES;
    public int MAX_QUESTION_LIMIT;

    public PlayerModel(String name, int MAX_NUMBER_OF_LIVES, int MAX_QUESTION_LIMIT) {
        this.name = name;
        this.numberOfLives = MAX_NUMBER_OF_LIVES;
        questionsCorrect = 0;
        this.MAX_QUESTION_LIMIT = MAX_QUESTION_LIMIT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfLives() {
        return numberOfLives;
    }

    public void decrementNumberOfLives() {
        --numberOfLives;
    }

    public int getQuestionsCorrect() {
        return questionsCorrect;
    }

    public void incrementQuestionsCorrect() {
        ++questionsCorrect;
    }

}
