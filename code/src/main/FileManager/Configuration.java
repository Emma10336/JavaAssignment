package main.FileManager;

public class Configuration {
    final static String BASE_FILE_PATH = "/Users/emmamackay/Desktop/JavaSchoolResources/JavaAssignment/code/files/"; //Stores the base path of all the files
    final static String FILE_EXTENSION = ".csv";

    /**
     * @param fileName The name of the file (without extension)
     * @return The full absolute file path
     */
    public static String createFilePath(String fileName){
        return BASE_FILE_PATH + fileName + FILE_EXTENSION;
    }
}
