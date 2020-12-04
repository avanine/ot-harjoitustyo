package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.text.Font;
/**
 * Class that contains the stage that is used for all the scenes
 *
 */
public class PongUi extends Application {

    public static final int WIDTH = 850;
    public static final int HEIGHT = 600;
    public static final Font SMALL_FONT = Font.font("Impact", 30);
    public static final Font BIG_FONT = Font.font("Impact", 40);
    
    /** width of smaller "helper" boxes that are used for setting layout */
    public static final double LAYOUT = 850 / 40;

    public Stage stage;
    private AnimationTimer loop;

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        stage.setTitle("Pong");
        stage.setResizable(false);
        stage.setScene(new WelcomeScene(this));
        stage.show();

        loop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Scene scene = primaryStage.getScene();
                if (scene instanceof AbstractScene) {
                    ((AbstractScene) scene).tick();
                }
            }
        };
        loop.start();
    }

    @Override
    public void stop() throws Exception {
        loop.stop();
        super.stop();
    }

    public Stage getPrimaryStage() {
        return stage;
    }

    public static void main(String args[]) {
        launch(args);
    }

}
