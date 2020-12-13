package pong.ui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * Scene abstraction for all the scenes.
 *
 */
public abstract class AbstractScene extends Scene {

    /**
     * Constructor for all the scenes.
     *
     * @param root root
     * @param width scene width
     * @param height scene height
     */
    public AbstractScene(Parent root, int width, int height) {
        super(root, width, height);
        setFill(Color.BLACK);
    }

    /**
     * Tick method for changing scenes.
     */
    public abstract void tick();
}
