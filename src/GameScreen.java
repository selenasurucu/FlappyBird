import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameScreen extends Application {
    private double birdY;
    private double velocity = 0;
    private double gravity = 0.2;
    private final double jumpStrength = -4.2;

    private int score = 0;
    private boolean gameOver = false;
    private int speed = 3;

    private int pipeWidth = 60;
    private int pipeGap = 140;

    private int pipe1X = 600;
    private int pipe2X = 900;

    private int pipe1TopHeight = (int)(Math.random() * 170 + 50);
    private int pipe2TopHeight = (int)(Math.random() * 170 + 50);

    private Image birdImage = new Image("file:assets/bird.png");
    private Image bg = new Image("file:assets/difficulty_bg.png");

    public GameScreen() {}
    public GameScreen(int speed) {
        this.speed = speed;
    }

    public void start(Stage stage) {
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(600, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        resetGame();

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                if (gameOver) {
                    new DifficultyScreen().show(stage); // Ana menüye dön
                } else {
                    velocity = jumpStrength;
                }
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            public void handle(long now) {
                gc.drawImage(bg, 0, 0, 600, 400);

                velocity += gravity;
                birdY += velocity;

                gc.drawImage(birdImage, 100, birdY, 40, 30);

                pipe1X -= speed;
                pipe2X -= speed;

                if (pipe1X + pipeWidth < 0) {
                    pipe1X = 600;
                    pipe1TopHeight = (int)(Math.random() * 170 + 50);
                    score += 5;
                }
                if (pipe2X + pipeWidth < 0) {
                    pipe2X = 600;
                    pipe2TopHeight = (int)(Math.random() * 170 + 50);
                    score += 5;
                }

                drawPipe(gc, pipe1X, pipe1TopHeight);
                drawPipe(gc, pipe2X, pipe2TopHeight);

                gc.setFill(Color.WHITE);
                gc.setFont(Font.font(24));
                gc.fillText("Score: " + score, 20, 30);

                if (checkCollision(pipe1X, pipe1TopHeight) || checkCollision(pipe2X, pipe2TopHeight) || birdY < 0 || birdY > 340) {
                    gc.setFont(Font.font("Arial", 28));
                    gc.fillText("Game Over", 220, 180);
                    gc.setFont(Font.font("Arial", 16));
                    gc.fillText("Press SPACE to Restart", 180, 210);
                    gameOver = true;
                    stop();
                }
            }
        };
        timer.start();

        stage.setScene(scene);
        stage.setTitle("Flappy Bird - Game");
        stage.show();
    }

    private void drawPipe(GraphicsContext gc, int x, int topHeight) {
        int bottomY = topHeight + pipeGap;
        gc.setFill(Color.GREEN);
        gc.fillRect(x, 0, pipeWidth, topHeight); // üst boru
        gc.fillRect(x, bottomY, pipeWidth, 330 - bottomY); // alt boru çimenin üstünden başlar
    }

    private boolean checkCollision(int pipeX, int topHeight) {
        int bottomY = topHeight + pipeGap;
        return (100 + 40 > pipeX && 100 < pipeX + pipeWidth) &&
                (birdY < topHeight || birdY + 30 > bottomY);
    }

    private void resetGame() {
        birdY = 135;
        velocity = 0;
        score = 0;
        pipe1X = 600;
        pipe2X = 900;
        pipe1TopHeight = (int)(Math.random() * 170 + 50);
        pipe2TopHeight = (int)(Math.random() * 170 + 50);
        gameOver = false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}