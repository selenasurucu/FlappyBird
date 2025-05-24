import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // Sol üst köşe için logo
        Image logoImage = new Image("file:assets/topkapi_logo.png"); // logo
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(100);
        logoView.setPreserveRatio(true);

        // Ortadaki yazılar
        Label welcomeText = new Label("Welcome to Flappy Bird");
        welcomeText.setFont(new Font("Arial", 24));

        Label continueText = new Label("Press any key to continue...");
        continueText.setFont(new Font("Arial", 14));

        VBox centerBox = new VBox(10, welcomeText, continueText);
        centerBox.setStyle("-fx-alignment: center;");

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: white;");
        root.setTop(logoView);
        root.setCenter(centerBox);
        BorderPane.setAlignment(logoView, javafx.geometry.Pos.TOP_LEFT);

        Scene scene = new Scene(root, 600, 400);

        // giriş ekranına gecme
        scene.setOnKeyPressed(e -> {
            LoginRegisterScreen loginScreen = new LoginRegisterScreen();
            try {
                loginScreen.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        stage.setTitle("Flappy Bird - Topkapi University");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}