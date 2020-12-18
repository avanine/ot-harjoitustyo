
package pong.ui;

import javafx.scene.shape.Rectangle;
import static pong.ui.PongUi.HEIGHT;
import static pong.ui.PongUi.LAYOUT;
import static pong.ui.PongUi.WIDTH;
/**
 * Creates walls for the game.
 *
 */
public class Wall {
    /**
     * Creates top wall.
     * 
     * @return rectangle
     */
    public Rectangle topWall() {
        return new Rectangle(0, 0, WIDTH, LAYOUT);
    }
    /**
     * Creates bottom wall.
     * 
     * @return rectangle
     */
    public Rectangle bottomWall() {
        return new Rectangle(0, HEIGHT - LAYOUT, WIDTH, LAYOUT);
    }
    /**
     * Creates left wall.
     * 
     * @return rectangle
     */
    public Rectangle leftWall() {
        return new Rectangle(-WIDTH, 0, WIDTH - LAYOUT, HEIGHT);
    }
    /**
     * Creates right wall.
     * 
     * @return rectangle
     */
    public Rectangle rightWall() {
        return new Rectangle(WIDTH + LAYOUT, 0, WIDTH, HEIGHT);
    }
}
