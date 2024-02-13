package main.test;

import main.FileManager.Deleter;
import main.FileManager.QuestionAnswerModel;
import main.FileManager.Writer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleterTest {

    @Test
    public void testDeleteRow_ValidDeletion() {
        // Arrange
        QuestionAnswerModel questionAnswer = new QuestionAnswerModel(1, "Question 1", "Answer 1");
        String fileName = "testFile";
        // Add the questionAnswer to the file before deletion
        Writer.addQAToCsv(questionAnswer, fileName);

        // Act
        boolean result = Deleter.deleteRow(questionAnswer, fileName);

        // Assert
        assertTrue(result, "Deletion should be successful");
    }

    @Test
    public void testDeleteRow_InvalidDeletion() {
        // Arrange
        QuestionAnswerModel questionAnswer = new QuestionAnswerModel(999, "Non-existent Question", "Non-existent Answer");
        String fileName = "testFile";

        // Act
        boolean result = Deleter.deleteRow(questionAnswer, fileName);

        // Assert
        assertFalse(result, "Deletion should be unsuccessful");
    }

    @Test
    public void testDeleteRow_DeleteFromEmptyFile() {
        // Arrange
        QuestionAnswerModel questionAnswer = new QuestionAnswerModel(1, "Question 1", "Answer 1");
        String fileName = "music";

        // Act
        boolean result = Deleter.deleteRow(questionAnswer, fileName);

        // Assert
        assertFalse(result, "Deletion should be unsuccessful from an empty file");
    }

    @Test
    public void testDeleteRow_DeleteLastRow() {
        // Arrange
        QuestionAnswerModel questionAnswer = new QuestionAnswerModel(1, "Question 1", "Answer 1");
        String fileName = "music";
        Writer.addQAToCsv(questionAnswer, fileName);

        // Act
        boolean result = Deleter.deleteRow(questionAnswer, fileName);

        // Assert
        assertTrue(result, "Deletion should be successful when deleting the last row");
    }
}
