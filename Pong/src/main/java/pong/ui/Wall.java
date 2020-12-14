
package pong.ui;

import javafx.scene.shape.Rectangle;
import static pong.ui.PongUi.HEIGHT;
import static pong.ui.PongUi.LAYOUT;
import static pong.ui.PongUi.WIDTH;

public class Wall {
    
    public Rectangle topWall() {
        return new Rectangle(0, 0, WIDTH, LAYOUT);
    }
    public Rectangle bottomWall() {
        return new Rectangle(0, HEIGHT - LAYOUT, WIDTH, LAYOUT);
    }
    public Rectangle leftWall() {
        return new Rectangle(-WIDTH, 0, WIDTH - LAYOUT, HEIGHT);
    }
    public Rectangle rightWall() {
        return new Rectangle(WIDTH + LAYOUT, 0, WIDTH, HEIGHT);
    }
}
