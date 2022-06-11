import com.example.lms.DatabaseConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.lms.*;

public class IntegrationTest { // This integration test is based connecting the login, download, upload
                               // and feedback with the database

    @Test
    public void testDownloadWithDatabase() throws IOException, SQLException {
        String fileName;
        byte [] data;
        File file = new File("src/main/resources/TestingDownload");
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();
        PreparedStatement pst = connect.databaseLink.prepareStatement("select file_name, file_data from file limit 4");
        pst.execute();
        ResultSet rs = pst.getResultSet();
        while(rs.next()) {
            fileName = rs.getString(1);
            data = rs.getBytes(2);
            AssignmentsController assignmentsController = new AssignmentsController();
            assignmentsController.writeFile(data, fileName, file);
            Assertions.assertTrue(file.exists());
        }
    }



    @Test
    public void testSubmitFileWithDatabase() throws IOException, SQLException {
        AssignmentsController assignmentsController = new AssignmentsController();
        String fileName = "DB-Lect2.pdf";
        File file = new File("src/main/resources/Downloads/DB-Lect2.pdf");
        byte [] data = assignmentsController.readFile(file);
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();
        PreparedStatement pst = connect.databaseLink.prepareStatement("insert into file (file_name,file_data) values (?,?) ");
        pst.setString(1,fileName);
        pst.setBytes(2,data);
        pst.execute();
        pst = connect.databaseLink.prepareStatement("select file_data from file where file_name = '"+fileName+"'");
        pst.execute();
        ResultSet rs = pst.getResultSet();
        while(rs.next()) {
            byte [] arr = rs.getBytes(1);
            Assertions.assertArrayEquals(data,arr);
        }
    }

    @Test
    public void testSubmitFeedback() throws IOException, SQLException {
        AssignmentsController assignmentsController = new AssignmentsController();
        Feedback feedback = new Feedback();
        String ans1 = feedback.temp("123");
        String fileName = "DB-Lect2.pdf";
        File file = new File("src/main/resources/Downloads/DB-Lect2.pdf");
        byte [] data = assignmentsController.readFile(file);
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();
        PreparedStatement pst = connect.databaseLink.prepareStatement("insert into file (file_name,file_data,feedback) values (?,?,?)");
        pst.setString(1,fileName);
        pst.setBytes(2,data);
        pst.setString(3,ans1);
        pst.execute();
        pst = connect.databaseLink.prepareStatement("select feedback from file where file_name = '"+fileName+"' and feedback = '"+ans1+"'");
        pst.execute();
        ResultSet rs = pst.getResultSet();
        while(rs.next()) {
            String ans2 = rs.getString(1);
            Assertions.assertEquals(ans1,ans2);
        }
    }

}
