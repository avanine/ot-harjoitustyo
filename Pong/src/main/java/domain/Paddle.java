package domain;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static ui.PongUi.LAYOUT;
/**
 * Class for creating paddle object
 *
 */
public class Paddle extends Rectangle {
    
    private final double MOVEMENT_SPEED = 7.5;
    private double yDirection;
    
    public Paddle() {
        
        setWidth(LAYOUT);
        setHeight(LAYOUT * 5);
        setFill(Color.PINK);
        setLayoutY(300 - getHeight() / 2);
        
        this.yDirection = 0.0;
    }
    /**
     * 
     * @return paddle's y-direction
     */
    public double getYDirection() {
        return yDirection;
    }
    /**
     * method for setting paddle's y-direction
     * 
     * @param y y-direction 
     */
    public void setYDirection(double y) {
        yDirection = y;
    }
    /**
     * 
     * @return paddle's movement speed
     */
    public double getMovementSpeed() {
        return MOVEMENT_SPEED;
    }
    /**
     * method for activating movement for left paddle
     * 
     * @param x key event
     */
    public void p1ActivateMovement(KeyEvent x) {
        switch (x.getCode()) {
            case W:
                yDirection = -1.0;
                break;
            case S:
                yDirection = 1.0;
                break;
            default:
                break;
        }
        
    }
    /**
     * method for activating movement for right paddle
     * 
     * @param x key event
     */
    public void p2ActivateMovement(KeyEvent x) {
        switch (x.getCode()) {
            case UP:
                yDirection = -1.0;
                break;
            case DOWN:
                yDirection = 1.0;
                break;
            default:
                break;
        }
    }
    /**
     * method for disabling paddle movement
     * 
     * @param x key event
     */
    public void disableMovement(KeyEvent x) {
        switch (x.getCode()) {
            case UP: case W:
                if (yDirection == -1.0) {
                    yDirection = 0.0;
                }
                break;
            case DOWN: case S:
                if (yDirection == 1.0) {
                    yDirection = 0.0;
                }
                break;
            default:
                break;
        }
    }
}
