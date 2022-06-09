import com.example.lms.Main;
import com.example.lms.TableItemCustom;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

        @Test
        public void emptyEmailOrPasswordErrorTest(FxRobot robot) {
            Button login = robot.lookup("#login").queryAs(Button.class);
            assertNotNull(login);
            Label invalidInputLabel = robot.lookup("#wrongInput").queryAs(Label.class);
            assertNotNull(invalidInputLabel);

            assertEquals("", invalidInputLabel.getText());
            robot.clickOn(login);
            assertEquals("Please enter email and password", invalidInputLabel.getText());
        }

        @Test
        public void invalidUserErrorTest(FxRobot robot) {
            TextField email = robot.lookup("#email").queryAs(TextField.class);
            assertNotNull(email);
            robot.clickOn(email);
            robot.write("anyEmail");
            assertEquals("anyEmail", email.getText());

            TextField password = robot.lookup("#password").queryAs(TextField.class);
            assertNotNull(password);
            robot.clickOn(password);
            robot.write("anyPassword");
            assertEquals("anyPassword", password.getText());

            Label invalidInputLabel = robot.lookup("#wrongInput").queryAs(Label.class);
            robot.clickOn("#login");
            assertEquals("Invalid email or password", invalidInputLabel.getText());
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

        @Test
        public void downloadErrorTest(FxRobot robot) {
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

            robot.clickOn(robot.lookup("#lecturesDownload").queryAs(Button.class));
            Node alert = robot.lookup(".dialog-pane").query();
            DialogPane pane = (DialogPane) alert;
            assertTrue(pane.isVisible());
            assertEquals("You must select a file", pane.getContentText());
            Button okButton = (Button) pane.lookup(".button");
            robot.clickOn(okButton);
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

        @Test
        public void downloadErrorTest(FxRobot robot) {
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

            robot.clickOn(robot.lookup("#downloadBtn").queryAs(Button.class));
            Node alert = robot.lookup(".dialog-pane").query();
            DialogPane pane = (DialogPane) alert;
            assertTrue(pane.isVisible());
            assertEquals("You must select a file", pane.getContentText());
            Button okButton = (Button) pane.lookup(".button");
            robot.clickOn(okButton);
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

        @Test
        public void downloadErrorTest(FxRobot robot) {
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

            robot.clickOn(robot.lookup("#downloadBtn").queryAs(Button.class));
            Node alert = robot.lookup(".dialog-pane").query();
            DialogPane pane = (DialogPane) alert;
            assertTrue(pane.isVisible());
            assertEquals("You must select a file", pane.getContentText());
            Button okButton = (Button) pane.lookup(".button");
            robot.clickOn(okButton);
        }
    }

    @Nested
    public class ViewSubmissionsTestSuite {
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
        public void backToAssignmentsTest(FxRobot robot) {
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

            robot.clickOn(robot.lookup("#viewSubmissionsBackButton").queryAs(Button.class));
            assertNotNull(robot.lookup("#feedbackButton").queryAs(Button.class));
        }

        @Test
        public void backToQuizzesTest(FxRobot robot) {
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

            robot.clickOn(robot.lookup("#viewSubmissionsBackButton").queryAs(Button.class));
            assertNotNull(robot.lookup("#submitBtn1").queryAs(Button.class));
        }

        @Test
        public void selectedFileFeedbackTest(FxRobot robot){
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

            TableView<TableItemCustom> subTable = robot.lookup("#sub_table").queryAs(TableView.class);
            if(!subTable.getColumns().isEmpty()){
                String subColumnID = subTable.getColumns().get(0).getId();
                Node subNode = robot.lookup("#" + subColumnID).nth(1).query();
                assertNotNull(subNode);
                robot.clickOn(subNode);
                robot.clickOn(robot.lookup("#selectFileButton").queryAs(Button.class));
                assertNotNull(robot.lookup("#feedback").queryAs(TextArea.class));
                robot.clickOn(robot.lookup("#feedbackBackButton").queryAs(Button.class));
                assertNotNull(robot.lookup("#selectFileButton").queryAs(Button.class));
            }
        }
        @Test
        public void noFileSelectedErrorTest(FxRobot robot){
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

            robot.clickOn(robot.lookup("#selectFileButton").queryAs(Button.class));
            Node alert = robot.lookup(".dialog-pane").query();
            DialogPane pane = (DialogPane) alert;
            assertTrue(pane.isVisible());
            assertEquals("You must select a file", pane.getContentText());
            Button okButton = (Button) pane.lookup(".button");
            robot.clickOn(okButton);
        }
    }

    @Nested
    public class LecturerDashboardTestSuite {
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
            robot.write("hoda");
            robot.clickOn("#password");
            robot.write("hoda");
            robot.clickOn("#login");

            robot.clickOn(robot.lookup("#lecture").queryAs(ImageView.class));
            assertTrue(robot.lookup("#lect_table").queryAs(TableView.class).isVisible());
        }

        @Test
        public void assignmentsTest(FxRobot robot) {
            robot.clickOn("#email");
            robot.write("hoda");
            robot.clickOn("#password");
            robot.write("hoda");
            robot.clickOn("#login");

            robot.clickOn(robot.lookup("#assignment").queryAs(ImageView.class));
            assertTrue(robot.lookup("#assignmentTable").queryAs(TableView.class).isVisible());
        }

        @Test
        public void quizzesTest(FxRobot robot) {
            robot.clickOn("#email");
            robot.write("hoda");
            robot.clickOn("#password");
            robot.write("hoda");
            robot.clickOn("#login");

            robot.clickOn(robot.lookup("#quiz").queryAs(ImageView.class));
            assertTrue(robot.lookup("#quiz_table_user").queryAs(TableView.class).isVisible());
        }
    }

    @Nested
    public class LecturerLecturesTestSuite {
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
            robot.write("hoda");
            robot.clickOn("#password");
            robot.write("hoda");
            robot.clickOn("#login");

            robot.clickOn(robot.lookup("#lecture").queryAs(ImageView.class));

            robot.clickOn(robot.lookup("#lectLectBackBtn").queryAs(Button.class));
            assertNotNull(robot.lookup("#assignment").queryAs(ImageView.class));
        }
    }

    @Nested
    public class LecturerAssignmentsTestSuite {
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
            robot.write("hoda");
            robot.clickOn("#password");
            robot.write("hoda");
            robot.clickOn("#login");

            robot.clickOn(robot.lookup("#assignment").queryAs(ImageView.class));

            robot.clickOn(robot.lookup("#lectAssBackBtn").queryAs(Button.class));
            assertNotNull(robot.lookup("#quiz").queryAs(ImageView.class));
        }

        @Test
        public void provideFeedbackTest(FxRobot robot){
            robot.clickOn("#email");
            robot.write("hoda");
            robot.clickOn("#password");
            robot.write("hoda");
            robot.clickOn("#login");

            robot.clickOn(robot.lookup("#assignment").queryAs(ImageView.class));

            TableView<TableItemCustom> subTable = robot.lookup("#assignmentTable").queryAs(TableView.class);
            if(!subTable.getColumns().isEmpty()){
                String assColumnID = subTable.getColumns().get(0).getId();
                Node assNode = robot.lookup("#" + assColumnID).nth(1).query();
                assertNotNull(assNode);
                robot.clickOn(assNode);
                robot.clickOn(robot.lookup("#submitBtn1").queryAs(Button.class));
                assertNotNull(robot.lookup("#charactersCount").tryQueryAs((Label.class)));
            }
        }

        @Test
        public void provideFeedbackErrorTest(FxRobot robot){
            robot.clickOn("#email");
            robot.write("hoda");
            robot.clickOn("#password");
            robot.write("hoda");
            robot.clickOn("#login");

            robot.clickOn(robot.lookup("#assignment").queryAs(ImageView.class));

            robot.clickOn(robot.lookup("#submitBtn1").queryAs(Button.class));
            Node alert = robot.lookup(".dialog-pane").query();
            DialogPane pane = (DialogPane) alert;
            assertTrue(pane.isVisible());
            assertEquals("You must select a file", pane.getContentText());
            Button okButton = (Button) pane.lookup(".button");
            robot.clickOn(okButton);
        }

        @Test
        public void downloadErrorTest(FxRobot robot) {
            robot.clickOn("#email");
            robot.write("hoda");
            robot.clickOn("#password");
            robot.write("hoda");
            robot.clickOn("#login");

            robot.clickOn(robot.lookup("#assignment").queryAs(ImageView.class));

            robot.clickOn(robot.lookup("#downloadBtn").queryAs(Button.class));
            Node alert = robot.lookup(".dialog-pane").query();
            DialogPane pane = (DialogPane) alert;
            assertTrue(pane.isVisible());
            assertEquals("You must select a file", pane.getContentText());
            Button okButton = (Button) pane.lookup(".button");
            robot.clickOn(okButton);
        }
    }

    @Nested
    public class LecturerQuizzesTestSuite {
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
            robot.write("hoda");
            robot.clickOn("#password");
            robot.write("hoda");
            robot.clickOn("#login");

            robot.clickOn(robot.lookup("#quiz").queryAs(ImageView.class));

            robot.clickOn(robot.lookup("#lectQuizBackBtn").queryAs(Button.class));
            assertNotNull(robot.lookup("#lecture").queryAs(ImageView.class));
        }

        @Test
        public void provideFeedbackTest(FxRobot robot){
            robot.clickOn("#email");
            robot.write("hoda");
            robot.clickOn("#password");
            robot.write("hoda");
            robot.clickOn("#login");

            robot.clickOn(robot.lookup("#quiz").queryAs(ImageView.class));

            TableView<TableItemCustom> subTable = robot.lookup("#quiz_table_user").queryAs(TableView.class);
            if(!subTable.getColumns().isEmpty()){
                String assColumnID = subTable.getColumns().get(0).getId();
                Node quizNode = robot.lookup("#" + assColumnID).nth(1).query();
                assertNotNull(quizNode);
                robot.clickOn(quizNode);
                robot.clickOn(robot.lookup("#submitBtn1").queryAs(Button.class));
                assertNotNull(robot.lookup("#charactersCount").tryQueryAs((Label.class)));
            }
        }

        @Test
        public void provideFeedbackErrorTest(FxRobot robot){
            robot.clickOn("#email");
            robot.write("hoda");
            robot.clickOn("#password");
            robot.write("hoda");
            robot.clickOn("#login");

            robot.clickOn(robot.lookup("#quiz").queryAs(ImageView.class));

            robot.clickOn(robot.lookup("#submitBtn1").queryAs(Button.class));
            Node alert = robot.lookup(".dialog-pane").query();
            DialogPane pane = (DialogPane) alert;
            assertTrue(pane.isVisible());
            assertEquals("You must select a file", pane.getContentText());
            Button okButton = (Button) pane.lookup(".button");
            robot.clickOn(okButton);
        }

        @Test
        public void downloadErrorTest(FxRobot robot) {
            robot.clickOn("#email");
            robot.write("hoda");
            robot.clickOn("#password");
            robot.write("hoda");
            robot.clickOn("#login");

            robot.clickOn(robot.lookup("#quiz").queryAs(ImageView.class));

            robot.clickOn(robot.lookup("#downloadBtn").queryAs(Button.class));
            Node alert = robot.lookup(".dialog-pane").query();
            DialogPane pane = (DialogPane) alert;
            assertTrue(pane.isVisible());
            assertEquals("You must select a file", pane.getContentText());
            Button okButton = (Button) pane.lookup(".button");
            robot.clickOn(okButton);
        }
    }

    @Nested
    public class FeedbackTestSuite {
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
        public void backToAssignmentsTest(FxRobot robot) {
            robot.clickOn("#email");
            robot.write("hoda");
            robot.clickOn("#password");
            robot.write("hoda");
            robot.clickOn("#login");

            robot.clickOn(robot.lookup("#assignment").queryAs(ImageView.class));

            TableView<TableItemCustom> subTable = robot.lookup("#assignmentTable").queryAs(TableView.class);
            if(!subTable.getColumns().isEmpty()){
                String assColumnID = subTable.getColumns().get(0).getId();
                Node assNode = robot.lookup("#" + assColumnID).nth(1).query();
                robot.clickOn(assNode);
                robot.clickOn(robot.lookup("#submitBtn1").queryAs(Button.class));
                robot.clickOn("#feedbackBackButton");
                assertTrue(robot.lookup("#assignmentTable").queryAs(TableView.class).isVisible());
            }
        }

        @Test
        public void backToQuizzesTest(FxRobot robot) {
            robot.clickOn("#email");
            robot.write("hoda");
            robot.clickOn("#password");
            robot.write("hoda");
            robot.clickOn("#login");

            robot.clickOn(robot.lookup("#quiz").queryAs(ImageView.class));

            TableView<TableItemCustom> subTable = robot.lookup("#quiz_table_user").queryAs(TableView.class);
            if(!subTable.getColumns().isEmpty()){
                String assColumnID = subTable.getColumns().get(0).getId();
                Node quizNode = robot.lookup("#" + assColumnID).nth(1).query();
                robot.clickOn(quizNode);
                robot.clickOn(robot.lookup("#submitBtn1").queryAs(Button.class));
                robot.clickOn("#feedbackBackButton");
                assertTrue(robot.lookup("#quiz_table_user").queryAs(TableView.class).isVisible());
            }
        }

        @Test
        public void textAreaTest(FxRobot robot){
            robot.clickOn("#email");
            robot.write("hoda");
            robot.clickOn("#password");
            robot.write("hoda");
            robot.clickOn("#login");

            robot.clickOn(robot.lookup("#assignment").queryAs(ImageView.class));

            TableView<TableItemCustom> subTable = robot.lookup("#assignmentTable").queryAs(TableView.class);
            if(!subTable.getColumns().isEmpty()){
                String assColumnID = subTable.getColumns().get(0).getId();
                Node assNode = robot.lookup("#" + assColumnID).nth(1).query();
                robot.clickOn(assNode);
                robot.clickOn(robot.lookup("#submitBtn1").queryAs(Button.class));
                TextArea txt = robot.lookup("#feedback").queryAs(TextArea.class);
                robot.clickOn(txt);
                robot.write("Well done, keep on the good work (9/10)");
                assertEquals("Well done, keep on the good work (9/10)", txt.getText());
            }
        }
    }
}