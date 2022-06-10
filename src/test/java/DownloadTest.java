import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class DownloadTest {
    File file = new File("src/main/resources/Downloads/National ID.pdf");
    String text = "Hello World";

    public byte[] readFile(File file) throws IOException {
        Path filePath = Paths.get(file.getAbsolutePath());
        byte[] arr = Files.readAllBytes(filePath);
        System.out.println("File has been read");
        return arr;
    }

    public File writeFile(byte[] data, String fileName) throws IOException {
        String path =  "src/main/resources/TestingDownload/" + fileName;
        OutputStream out = new FileOutputStream(path);
        out.write(data);
        out.close();
        File f = new File(path);
        System.out.println("File has been created");
        return f;
    }

    public String submitFeedback(String text) {
        String submit = "";
        submit += text;
        return submit;
    }

    @Test
    public void testDownload() throws IOException {
            byte[] arr = readFile(file);
            File test = writeFile(arr, "test");
            Assertions.assertEquals(true, test.exists());
    }

    @Test
    public void testSubmitFile() throws IOException {
        byte[] arr1 = readFile(file);
        byte[] arr2 = Files.readAllBytes(Path.of(file.getAbsolutePath()));
        Assertions.assertArrayEquals(arr2, arr1);
    }

    @Test
    public void testSubmitFeedback() throws IOException {
       String submit = submitFeedback(text);
       Assertions.assertEquals(text,submit);
    }

}
