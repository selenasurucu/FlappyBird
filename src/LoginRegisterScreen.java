import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.io.*;

public class LoginRegisterScreen extends Application {

    @Override
    public void start(Stage stage) {
        Label nameLabel = new Label("Full Name:");
        TextField nameField = new TextField();

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label messageLabel = new Label();

        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            String name = nameField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();

            try {
                FileWriter writer = new FileWriter("userdata.txt", true);
                writer.write(username + "," + password + "\n");
                writer.close();
                messageLabel.setText("Registration successful!");
                goToLoginScreen(stage);
            } catch (IOException ex) {
                messageLabel.setText("Error saving data.");
                ex.printStackTrace();
            }
        });

        VBox registerBox = new VBox(10);
        registerBox.setPadding(new Insets(20));
        registerBox.getChildren().addAll(
                nameLabel, nameField,
                usernameLabel, usernameField,
                passwordLabel, passwordField,
                registerButton, messageLabel
        );

        Scene scene = new Scene(registerBox, 300, 250);
        stage.setTitle("Register - Flappy Bird");
        stage.setScene(scene);
        stage.show();
    }

    private void goToLoginScreen(Stage stage) {
        VBox loginBox = new VBox(10);
        loginBox.setPadding(new Insets(20));

        Label userLabel = new Label("Username:");
        TextField userField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();

        Label resultLabel = new Label();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String inputUser = userField.getText();
            String inputPass = passField.getText();
            boolean found = false;

            try {
                BufferedReader reader = new BufferedReader(new FileReader("userdata.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2 && parts[0].equals(inputUser) && parts[1].equals(inputPass)) {
                        found = true;
                        break;
                    }
                }
                reader.close();

                if (found) {
                    resultLabel.setText("Login successful!");
                    DifficultyScreen difficultyScreen = new DifficultyScreen();
                    difficultyScreen.show(stage);
                } else {
                    resultLabel.setText("Invalid username or password.");
                }
            } catch (IOException ex) {
                resultLabel.setText("Error reading file.");
            }
        });

        loginBox.getChildren().addAll(userLabel, userField, passLabel, passField, loginButton, resultLabel);
        Scene loginScene = new Scene(loginBox, 300, 200);
        stage.setScene(loginScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}