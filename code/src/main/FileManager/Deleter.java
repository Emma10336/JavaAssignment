package main.FileManager;

import main.FileManager.Reader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Deleter {

    /**
     *
     * @param questionAnswer is an instance of QuestionAnswerModel class
     * @param fileName file name of file which will be edited
     * @return
     */
    public static boolean deleteRow(QuestionAnswerModel questionAnswer, String fileName){
        String header = (fileName + "_ID,") + (fileName+ "_Q,") + (fileName + "_A,\n"); //Build the header of the file

        //Read selected CSV file
        LinkedList<QuestionAnswerModel> currentFile = Reader.readAllFromCsv(fileName);

        //If currentFile is not empty
        if (currentFile != null) {
            //If the currentQuestionAnswer's id is the same as the one selected, then remove
            if(!currentFile.removeIf(currentQuestionAnswer -> currentQuestionAnswer.getId() == questionAnswer.getId())) {
                System.out.println("The provided row ID is invalid");
                return false;
            }
            else {
                currentFile.removeIf(currentQuestionAnswer -> currentQuestionAnswer.getId() == questionAnswer.getId());

            }
        }

        StringBuilder csvAsString = new StringBuilder();
        //Append the header built above
        csvAsString.append(header);
        //If currentFile is not empty
        if (currentFile != null) {
            //For each remaining row in the file, append it to the csvAsString string builder
            currentFile.forEach(currentQuestionAnswer -> csvAsString.append(currentQuestionAnswer.toString()));
        }

        //Write the updated content of csvAsString to file
        //If writer manages to write to CSV value return boolean value of True
        try (FileWriter writer = new FileWriter(Configuration.createFilePath(fileName))) {
			writer.write(csvAsString.toString());
            return true;
		}catch (IOException e){
            //If writer throws exception, print return message
            //Also, return bool value of False
            System.err.println("Error deleting question from file: " + fileName + " with error message: " + e.getMessage());
            e.printStackTrace();

            return false;
        }
    }
}
