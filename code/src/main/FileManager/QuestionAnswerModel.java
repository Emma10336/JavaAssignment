package main.FileManager;

//Class Model of Row Contents
public class QuestionAnswerModel {
    private int id;
    private String question;
    private String answer;

    //Constructor
    public QuestionAnswerModel(int id, String question, String answer){
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    //Overloaded Constructor
    public QuestionAnswerModel(String question, String answer){
        this(-1, question, answer);
    }

    public QuestionAnswerModel(int id) {
        this.id = id;
        this.question = null;
        this.answer = null;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    //Check if answer is correct
    public boolean isAnswerCorrect(String userAnswer){
        return userAnswer.equalsIgnoreCase(this.answer);
    }

    //Convert object to string
    @Override
    public String toString() {
        return "\n" + this.id + ", " + this.question + ", " + this.answer ;
    }
}
