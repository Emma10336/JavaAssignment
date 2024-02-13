package main.FileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Random;

public class Reader {
    //Method to read csv file contents
	public static LinkedList<QuestionAnswerModel> readAllFromCsv(String fileName) {
        //Create linked list for the file contents
        LinkedList<QuestionAnswerModel> file = new LinkedList<>();
        //try catch statement for reader
        try (BufferedReader reader = new BufferedReader(new FileReader(Configuration.createFilePath(fileName)))){
            reader.readLine(); //Skip the first line of the file
            String line;
            //loop until line is empty
            while ((line = reader.readLine()) != null) {
                //split line into parts according to comma delimeter
				String[] parts = line.split(",");
                //create variable to stor id
                int id;
                try {
                    //parse row id into id variable
                    id = Integer.parseInt(parts[0]);
                }catch (NumberFormatException e){
                    //if a row has no row id or row id can not be parsed, create a placeholder id
                    id = new Random().nextInt();
                }
                //if there are 3 parts in the line, add that to the linked list
				if (parts.length == 3) file.add(new QuestionAnswerModel(id, parts[1], parts[2]));
			}
            //error message
        }catch (Exception e){
            System.err.println("Error reading CSV File with name: " + fileName);
            e.printStackTrace();
            return null;
        }

        return file;
    }
}
