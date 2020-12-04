package ui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
/**
 * Scene abstraction for all the scenes
 *
 */
public abstract class AbstractScene extends Scene {

    public AbstractScene(Parent root, int width, int height) {
        super(root, width, height);
        setFill(Color.BLACK);
    }

    public abstract void tick();
}
