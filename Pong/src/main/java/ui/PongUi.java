package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;

public class PongUi extends Application {

    public Stage stage;
    public Scene scene;
    private AnimationTimer loop;
    public static final int WIDTH = 850;
    public static final int HEIGHT = 600;

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
