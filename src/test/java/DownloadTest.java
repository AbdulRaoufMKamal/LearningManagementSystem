import com.example.lms.Feedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

import com.example.lms.AssignmentsController;


public class DownloadTest {
    File folder = new File("src/main/resources/TestingDownload");
    File file = new File("src/main/resources/Downloads/DB-Lect2.pdf");
    @Test
    public void testDownload() throws IOException {
        AssignmentsController assignmentsController = new AssignmentsController();
        byte[] arr = assignmentsController.readFile(file);
        File test = assignmentsController.writeFile(arr, "test",folder);
        Assertions.assertTrue(test.exists());
    }

    @Test
    public void testSubmitFile() throws IOException {
        AssignmentsController assignmentsController = new AssignmentsController();
        byte [] arr = assignmentsController.readFile(file);
        File f = assignmentsController.writeFile(arr,"test.pdf",folder);
        Assertions.assertArrayEquals(arr,Files.readAllBytes(Path.of(f.getAbsolutePath())));
    }

    @Test
    public void testSubmitFeedback() throws IOException, SQLException {
        Feedback feedback = new Feedback();
        String ans = feedback.temp("123");
        String not_ans = "123";
        Assertions.assertEquals(not_ans,ans);
    }

}
