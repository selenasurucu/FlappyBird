import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DifficultyScreen {

    public void show(Stage stage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        // Arka plan
        BackgroundImage bgImage = new BackgroundImage(
                new Image("file:assets/difficulty_bg.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(600, 400, false, false, false, false)
        );
        root.setBackground(new Background(bgImage));

        // Başlık
        Text title = new Text("SELECT DIFFICULTY");
        title.setFont(Font.font("Arial", 28));

        // Butonlar
        Button easyButton = new Button("EASY");
        Button mediumButton = new Button("MEDIUM");
        Button hardButton = new Button("HARD");

        // Buton stili
        String style = "-fx-padding: 10px 20px; -fx-background-color: #ffffff; -fx-text-fill: #000000; -fx-font-size: 16px;";
        easyButton.setStyle(style);
        mediumButton.setStyle(style);
        hardButton.setStyle(style);

        // Buton işlevleri
        easyButton.setOnAction(e -> new GameScreen(2).start(stage));    // yavaş kuş
        mediumButton.setOnAction(e -> new GameScreen(4).start(stage));  // normal kuş
        hardButton.setOnAction(e -> new GameScreen(6).start(stage));    // hızlı kuş

        root.getChildren().addAll(title, easyButton, mediumButton, hardButton);

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Select Difficulty");
        stage.show();
    }
}
