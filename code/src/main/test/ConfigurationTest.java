package main.test;

import main.FileManager.Configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigurationTest {

    @org.junit.jupiter.api.Test
    public void testCreateFilePath_ValidFileName() {
        // Arrange
        String fileName = "data";

        // Act
        String filePath = Configuration.createFilePath(fileName);

        // Assert
        assertEquals("/Users/emmamackay/Desktop/JavaSchoolResources/JavaAssignment/code/files/data.csv", filePath);
    }

    @org.junit.jupiter.api.Test
    public void testCreateFilePath_FileNameWithSpaces() {
        // Arrange
        String fileName = "my data";

        // Act
        String filePath = Configuration.createFilePath(fileName);

        // Assert
        assertEquals("/Users/emmamackay/Desktop/JavaSchoolResources/JavaAssignment/code/files/my data.csv", filePath);
    }
}