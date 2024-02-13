package main.FileManager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Writer {
    /**
     *
     * @param questionAnswer object of QuestionAnswerModel
     * @param fileName File Name needing to be updated
     * @return Boolean value according to method success / failure
     */
    public static boolean addQAToCsv(QuestionAnswerModel questionAnswer, String fileName) {
        //File Writer
        try (FileWriter writer = new FileWriter(Configuration.createFilePath(fileName), true)) {
            //Call method to get next RowId
            int nextRowID = getNextRowId(fileName);
            //Set ID of questionAnswer object to the next
            questionAnswer.setId(nextRowID);
            //Write the questionAnswer to the CSV file according to format specified in the
            //QuestionAnswerModel in class
            writer.write(questionAnswer.toString());
            //If writer manages to write to CSV file return boolean value of True
            return true;
        } catch (IOException e) {
            //If writer does not manage to write to CSV value return boolean value of False
            System.err.println("Error adding question to file: " + fileName + " with error message: " + e.getMessage());
            e.printStackTrace();

            return false;
        }
    }

    /**
     *
     * @param fileName File Name rowID will be taken from
     * @return
     */
    private static int getNextRowId(String fileName) {
        //Call reader method and create linked List with contents
        LinkedList<QuestionAnswerModel> currentFile = Reader.readAllFromCsv(fileName);
        //If current file is not null, and current file is not empty
        if (currentFile != null && !currentFile.isEmpty()) {
            //Calculate nextRowID by getting the size of the currentFile list
            //Subtract 1 as it starts with a 0, you will have the last element in the list
            //Get the id of that element
            //Increase it by one to determine the NEXT row ID
            return (currentFile.get(currentFile.size() - 1).getId()) + 1;
        }

        else return 1; //This would be the first item in the file
    }
}
