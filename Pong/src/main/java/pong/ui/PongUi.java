package pong.ui;

import pong.dao.PlayerScoreDao;
import java.io.FileInputStream;
import java.util.Properties;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import pong.dao.ThemeColorDao;

/**
 * Class that contains the stage that is used for all the scenes.
 *
 */
public class PongUi extends Application {

    public static final int WIDTH = 850;
    public static final int HEIGHT = 600;
    public static final Font SMALL_FONT = Font.font("Impact", 30);
    public static final Font BIG_FONT = Font.font("Impact", 40);

    public static final double LAYOUT = 850 / 40;

    public static final Insets INSETS_20 = new Insets(20);
    public static final Insets INSETS_30 = new Insets(30);
    public static final Insets INSETS_50 = new Insets(50);

    public Stage stage;
    private AnimationTimer loop;

    public static PlayerScoreDao dao;
    public static ThemeColorDao colorDao;

    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        dao = new PlayerScoreDao(properties.getProperty("scores"));
        colorDao = new ThemeColorDao(properties.getProperty("color"));
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

    /**
     * Main method to launch the app.
     *
     * @param args args
     */
    public static void main(String args[]) {
        launch(args);
    }

}
