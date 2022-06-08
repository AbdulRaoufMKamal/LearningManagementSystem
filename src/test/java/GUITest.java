import com.example.lms.Main;
import com.example.lms.TableItemCustom;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.Assert.*;

@ExtendWith(ApplicationExtension.class)
public class GUITest {
    @Nested
    public class loginFormTestSuite {
        @Start
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginForm.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Learning Management System");
            stage.setScene(scene);
            stage.getIcons().add(new Image(Main.class.getResource("LoginForm.fxml") + "..\\..\\..\\..\\..\\img\\lms.png"));
            stage.show();
        }

        @Test
        public void studentLoginTest(FxRobot robot) {
            TextField email = robot.lookup("#email").queryAs(TextField.class);
            assertNotNull(email);
            robot.clickOn(email);
            robot.write("19p4442");
            assertEquals("19p4442", email.getText());

            TextField password = robot.lookup("#password").queryAs(TextField.class);
            assertNotNull(password);
            robot.clickOn(password);
            robot.write("abdulraouf");
            assertEquals("abdulraouf", password.getText());

            robot.clickOn("#login");
            assertNotNull(robot.lookup("#studentSignOut").queryAs(Button.class));
        }

        @Test
        public void lecturerLoginTest(FxRobot robot) {
            TextField email = robot.lookup("#email").queryAs(TextField.class);
            assertNotNull(email);
            robot.clickOn(email);
            robot.write("hoda");
            assertEquals("hoda", email.getText());

            TextField password = robot.lookup("#password").queryAs(TextField.class);
            assertNotNull(password);
            robot.clickOn(password);
            robot.write("hoda");
            assertEquals("hoda", password.getText());

            robot.clickOn("#login");
            assertNotNull(robot.lookup("#adminimg").queryAs(ImageView.class));
        }
    }

    @Nested
    public class ChooseCoursesTestSuite {
        @Start
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginForm.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Learning Management System");
            stage.setScene(scene);
            stage.getIcons().add(new Image(Main.class.getResource("LoginForm.fxml") + "..\\..\\..\\..\\..\\img\\lms.png"));
            stage.show();
        }

        @Test
        public void signOutTest(FxRobot robot) {
            robot.clickOn("#email");
            robot.write("19p4442");
            robot.clickOn("#password");
            robot.write("abdulraouf");
            robot.clickOn("#login");

            robot.clickOn("#studentSignOut");
            assertNotNull(robot.lookup("#login").queryAs(Button.class));
        }

        @Test
        public void selectCourseTest(FxRobot robot) {
            robot.clickOn("#email");
            robot.write("19p4442");
            robot.clickOn("#password");
            robot.write("abdulraouf");
            robot.clickOn("#login");

            TableView<TableItemCustom> table = robot.lookup("#courseTable").queryAs(TableView.class);
            String columnID = table.getColumns().get(0).getId();

            Node node1 = robot.lookup("#" + columnID).nth(1).query();
            assertNotNull(node1);
            robot.clickOn(node1);
            robot.clickOn(robot.lookup("#selectCourseButton").queryAs(Button.class));
            assertNotNull(robot.lookup("#lecture").queryAs(ImageView.class));
            robot.clickOn(robot.lookup("#back_btn").queryAs(Button.class));

            Node node2 = robot.lookup("#" + columnID).nth(2).query();
            assertNotNull(node2);
            robot.clickOn(node2);
            robot.clickOn(robot.lookup("#selectCourseButton").queryAs(Button.class));
            assertNotNull(robot.lookup("#assignment").queryAs(ImageView.class));
            robot.clickOn(robot.lookup("#back_btn").queryAs(Button.class));

            Node node3 = robot.lookup("#" + columnID).nth(3).query();
            assertNotNull(node3);
            robot.clickOn(node3);
            robot.clickOn(robot.lookup("#selectCourseButton").queryAs(Button.class));
            assertNotNull(robot.lookup("#quiz").queryAs(ImageView.class));
            robot.clickOn(robot.lookup("#back_btn").queryAs(Button.class));

            Node node4 = robot.lookup("#" + columnID).nth(4).query();
            assertNotNull(node4);
            robot.clickOn(node4);
            robot.clickOn(robot.lookup("#selectCourseButton").queryAs(Button.class));
            assertNotNull(robot.lookup("#lmsLogo").queryAs(ImageView.class));
            robot.clickOn(robot.lookup("#back_btn").queryAs(Button.class));
        }
    }

    @Nested
    public class StudentDashboardTestSuite {
        @Start
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginForm.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Learning Management System");
            stage.setScene(scene);
            stage.getIcons().add(new Image(Main.class.getResource("LoginForm.fxml") + "..\\..\\..\\..\\..\\img\\lms.png"));
            stage.show();
        }

        @Test
        public void lecturesTest(FxRobot robot) {
            robot.clickOn("#email");
            robot.write("19p4442");
            robot.clickOn("#password");
            robot.write("abdulraouf");
            robot.clickOn("#login");

            TableView<TableItemCustom> table = robot.lookup("#courseTable").queryAs(TableView.class);
            String columnID = table.getColumns().get(0).getId();
            Node node = robot.lookup("#" + columnID).nth(1).query();
            robot.clickOn(node);
            robot.clickOn(robot.lookup("#selectCourseButton").queryAs(Button.class));

            robot.clickOn(robot.lookup("#lecture").queryAs(ImageView.class));
            assertNotNull(robot.lookup("#lecturesDownload").queryAs(Button.class));
        }

        @Test
        public void assignmentsTest(FxRobot robot) {
            robot.clickOn("#email");
            robot.write("19p4442");
            robot.clickOn("#password");
            robot.write("abdulraouf");
            robot.clickOn("#login");

            TableView<TableItemCustom> table = robot.lookup("#courseTable").queryAs(TableView.class);
            String columnID = table.getColumns().get(0).getId();
            Node node = robot.lookup("#" + columnID).nth(1).query();
            robot.clickOn(node);
            robot.clickOn(robot.lookup("#selectCourseButton").queryAs(Button.class));

            robot.clickOn(robot.lookup("#assignment").queryAs(ImageView.class));
            assertNotNull(robot.lookup("#feedbackButton").queryAs(Button.class));
        }

        @Test
        public void quizzesTest(FxRobot robot) {
            robot.clickOn("#email");
            robot.write("19p4442");
            robot.clickOn("#password");
            robot.write("abdulraouf");
            robot.clickOn("#login");

            TableView<TableItemCustom> table = robot.lookup("#courseTable").queryAs(TableView.class);
            String columnID = table.getColumns().get(0).getId();
            Node node = robot.lookup("#" + columnID).nth(1).query();
            robot.clickOn(node);
            robot.clickOn(robot.lookup("#selectCourseButton").queryAs(Button.class));

            robot.clickOn(robot.lookup("#quiz").queryAs(ImageView.class));
            assertNotNull(robot.lookup("#submitBtn1").queryAs(Button.class));
        }
    }

    @Nested
    public class StudentLecturesTestSuite {
        @Start
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginForm.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Learning Management System");
            stage.setScene(scene);
            stage.getIcons().add(new Image(Main.class.getResource("LoginForm.fxml") + "..\\..\\..\\..\\..\\img\\lms.png"));
            stage.show();
        }

        @Test
        public void backButtonTest(FxRobot robot) {
            robot.clickOn("#email");
            robot.write("19p4442");
            robot.clickOn("#password");
            robot.write("abdulraouf");
            robot.clickOn("#login");

            TableView<TableItemCustom> table = robot.lookup("#courseTable").queryAs(TableView.class);
            String columnID = table.getColumns().get(0).getId();
            Node node = robot.lookup("#" + columnID).nth(1).query();
            robot.clickOn(node);
            robot.clickOn(robot.lookup("#selectCourseButton").queryAs(Button.class));

            robot.clickOn(robot.lookup("#lecture").queryAs(ImageView.class));

            robot.clickOn(robot.lookup("#lecturesBackButton").queryAs(Button.class));
            assertNotNull(robot.lookup("#assignment").queryAs(ImageView.class));
        }
    }

    @Nested
    public class StudentAssignmentsTestSuite {
        @Start
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginForm.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Learning Management System");
            stage.setScene(scene);
            stage.getIcons().add(new Image(Main.class.getResource("LoginForm.fxml") + "..\\..\\..\\..\\..\\img\\lms.png"));
            stage.show();
        }

        @Test
        public void backButtonTest(FxRobot robot) {
            robot.clickOn("#email");
            robot.write("19p4442");
            robot.clickOn("#password");
            robot.write("abdulraouf");
            robot.clickOn("#login");

            TableView<TableItemCustom> table = robot.lookup("#courseTable").queryAs(TableView.class);
            String columnID = table.getColumns().get(0).getId();
            Node node = robot.lookup("#" + columnID).nth(3).query();
            robot.clickOn(node);
            robot.clickOn(robot.lookup("#selectCourseButton").queryAs(Button.class));

            robot.clickOn(robot.lookup("#assignment").queryAs(ImageView.class));

            robot.clickOn(robot.lookup("#assignmentsBackButton").queryAs(Button.class));
            assertNotNull(robot.lookup("#quiz").queryAs(ImageView.class));
        }

        @Test
        public void viewFeedbackTest(FxRobot robot){
            robot.clickOn("#email");
            robot.write("19p4442");
            robot.clickOn("#password");
            robot.write("abdulraouf");
            robot.clickOn("#login");

            TableView<TableItemCustom> table = robot.lookup("#courseTable").queryAs(TableView.class);
            String columnID = table.getColumns().get(0).getId();
            Node node = robot.lookup("#" + columnID).nth(2).query();
            robot.clickOn(node);
            robot.clickOn(robot.lookup("#selectCourseButton").queryAs(Button.class));

            robot.clickOn(robot.lookup("#assignment").queryAs(ImageView.class));

            robot.clickOn(robot.lookup("#feedbackButton").queryAs(Button.class));
            assertNotNull(robot.lookup("#sub-table").tryQueryAs((TableView.class)));
        }
    }

    @Nested
    public class StudentQuizzesTestSuite {
        @Start
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginForm.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Learning Management System");
            stage.setScene(scene);
            stage.getIcons().add(new Image(Main.class.getResource("LoginForm.fxml") + "..\\..\\..\\..\\..\\img\\lms.png"));
            stage.show();
        }

        @Test
        public void backButtonTest(FxRobot robot) {
            robot.clickOn("#email");
            robot.write("19p4442");
            robot.clickOn("#password");
            robot.write("abdulraouf");
            robot.clickOn("#login");

            TableView<TableItemCustom> table = robot.lookup("#courseTable").queryAs(TableView.class);
            String columnID = table.getColumns().get(0).getId();
            Node node = robot.lookup("#" + columnID).nth(4).query();
            robot.clickOn(node);
            robot.clickOn(robot.lookup("#selectCourseButton").queryAs(Button.class));

            robot.clickOn(robot.lookup("#quiz").queryAs(ImageView.class));

            robot.clickOn(robot.lookup("#quizzesBackButton").queryAs(Button.class));
            assertNotNull(robot.lookup("#lecture").queryAs(ImageView.class));
        }

        @Test
        public void viewFeedbackTest(FxRobot robot){
            robot.clickOn("#email");
            robot.write("19p4442");
            robot.clickOn("#password");
            robot.write("abdulraouf");
            robot.clickOn("#login");

            TableView<TableItemCustom> table = robot.lookup("#courseTable").queryAs(TableView.class);
            String columnID = table.getColumns().get(0).getId();
            Node node = robot.lookup("#" + columnID).nth(2).query();
            robot.clickOn(node);
            robot.clickOn(robot.lookup("#selectCourseButton").queryAs(Button.class));

            robot.clickOn(robot.lookup("#quiz").queryAs(ImageView.class));

            robot.clickOn(robot.lookup("#submitBtn1").queryAs(Button.class));
            assertNotNull(robot.lookup("#selectFileButton").tryQueryAs((TableView.class)));
        }
    }
}

