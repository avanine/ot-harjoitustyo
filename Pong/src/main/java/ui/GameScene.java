package ui;

import javafx.scene.Group;

public class GameScene extends AbstractScene {

    // !!! add ball, paddles, scores, names !!!
    public GameScene(WelcomeScene welcomeScene) {
        super(new Group(), 850, 600);
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
