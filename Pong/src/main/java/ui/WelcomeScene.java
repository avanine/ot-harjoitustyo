package ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WelcomeScene extends Application {
    
    private final Hyperlink startGame;
    private final Hyperlink scores;
    private final Hyperlink exit;
    private static final int WIDTH = 850;
    private static final int HEIGHT = 600;
    private final BorderPane bp;
    private Stage stage;
    
    public WelcomeScene() {
        
        bp = new BorderPane();
        
        startGame = new Hyperlink("New Game");
        startGame.setFont(Font.font("Impact", 40));
        
        scores = new Hyperlink("High Scores");
        scores.setFont(Font.font("Impact", 40));
        
        exit = new Hyperlink("Exit");
        exit.setFont(Font.font("Impact", 40));
        
        Node newTitle = createTitle("Pong");
        FlowPane fp = new FlowPane(startGame, scores);
        fp.setAlignment(Pos.CENTER);
        Insets gap = new Insets(5);
        FlowPane.setMargin(startGame, gap);
        FlowPane.setMargin(scores, gap);
        
        bp.setTop(newTitle);
        bp.setCenter(fp);
        bp.setBottom(exit);
        BorderPane.setAlignment(exit, Pos.CENTER);
        
        BorderPane.setMargin(newTitle, new Insets(30));
        
    }
    
    @Override
    public void start(Stage stage) {
        
        stage.setTitle("Pong");
        Group root = new Group(bp);
        root.setTranslateX(210);
        root.setTranslateY(30);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        
        stage.show();
        
        startGame.setOnAction(event -> {
            try {
                stage.setScene(new NameScene(this));
            } catch (Exception ex) {
                Logger.getLogger(WelcomeScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    private Node createTitle(String title) {
        final double movement = 25;
        HBox letters = new HBox();
        letters.setAlignment(Pos.CENTER);
        letters.setPadding(new Insets(movement, 0, 0, 0));
        
        for (int i = 0; i < title.length(); i++) {
            Text letter = new Text(title.charAt(i) + "");
            letter.setFont(Font.font("Impact", FontWeight.BOLD, 80));
            letter.setFill(Color.WHITE);
            letters.getChildren().add(letter);
            
            TranslateTransition tt = new TranslateTransition(Duration.seconds(2), letter);
            tt.setDelay(Duration.millis(i * 50));
            tt.setToY(-movement);
            tt.setAutoReverse(true);
            tt.setCycleCount(TranslateTransition.INDEFINITE);
            tt.play();
        }
        
        return letters;
    }
    
    public Stage getPrimaryStage() {
        return stage;
    }
    
    public void changeToGameScene() {
        stage.setScene(new GameScene(this));
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}