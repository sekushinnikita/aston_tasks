import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.astondevs.week3.MyIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MyIOTest {
    private static final String TEST_FILE_NAME = "week3_task1.txt";

    @BeforeAll
    static void setup() {
        try {
            Files.deleteIfExists(Path.of(TEST_FILE_NAME));
        } catch (IOException e) {
        }
    }

    @AfterEach
    void cleanup() throws IOException {
        Files.deleteIfExists(Path.of(TEST_FILE_NAME));
    }

    @Test
    void testWriteToFile_NormalInput() throws Exception {
        String data = "Week3, Task1!";
        MyIO.writeToFile(data);
        String content = MyIO.readFromFile();
        assertTrue(content.contains(data));
    }

    @Test
    void testRewriteToFile_NormalInput() throws Exception {
        String data1 = "First line";
        String data2 = "Second line";

        MyIO.writeToFile(data1);
        MyIO.rewriteToFile(data2);

        String content = MyIO.readFromFile();
        assertTrue(content.contains(data2));
        assertFalse(content.contains(data1));
    }

    @Test
    void testReadFromFile_EmptyFile() throws Exception {
        Files.createFile(Path.of(TEST_FILE_NAME));
        String content = MyIO.readFromFile();
        assertEquals("", content);
    }

    @Test
    void testWriteToFile_NullData() {
        assertThrows(NullPointerException.class, () -> {
            MyIO.writeToFile(null);
        });
    }

    @Test
    void testIsDataValid_WrongFormat_UserChoosesY() throws Exception {}

    @Test
    void testIsDataValid_WrongFormat_UserChoosesN() throws Exception {}

    @Test
    void testIsDataValid_WrongFormat_UserInputInvalid() {}

    @Test
    void testWriteToFile_WhenIOExceptionOccurs() throws Exception {}

    @Test
    void testReadFromFile_WhenIOExceptionOccurs() {}

    @Test
    void testEmptyStringInput() throws Exception {
        String data = "";
        MyIO.writeToFile(data);
        String content = MyIO.readFromFile();
        assertTrue(content.contains(data));
    }

    @Test
    void testLargeInputData() throws Exception {
        StringBuilder largeData = new StringBuilder();
        for (int i = 0; i < 10_000; i++) {
            largeData.append("Line ").append(i).append("\n");
        }
        MyIO.writeToFile(largeData.toString());
        String content = MyIO.readFromFile();
        assertTrue(content.contains("Line 9999"));
    }

    @Test
    void testMultipleWritesAndRewrites() throws Exception {
        MyIO.writeToFile("First");
        MyIO.writeToFile("Second");
        String content = MyIO.readFromFile();
        assertTrue(content.contains("First"));
        assertTrue(content.contains("Second"));
        MyIO.rewriteToFile("Rewritten");
        String newContent = MyIO.readFromFile();
        assertTrue(newContent.contains("Rewritten"));
        assertFalse(newContent.contains("First"));
        assertFalse(newContent.contains("Second"));
    }
}
