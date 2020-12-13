package pong.domain;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static pong.ui.PongUi.LAYOUT;
/**
 * Class for creating a paddle object.
 *
 */
public class Paddle extends Rectangle {
    
    private final double movementSpeed = 7.5;
    private double yDirection;
    /**
     * Initializes paddle.
     */
    public Paddle() {
        
        setWidth(LAYOUT);
        setHeight(LAYOUT * 5);
        setFill(Color.PINK);
        setLayoutY(300 - getHeight() / 2);
        
        this.yDirection = 0.0;
    }
    /**
     * Adds movement for paddles.
     */
    public void movePaddle() {
        setLayoutY(getLayoutY() + (getYDirection() * getMovementSpeed()));
    }
    /**
     * Makes sure that paddles stay within boundaries.
     * 
     * @param top top wall
     * @param bottom bottom wall
     */
    public void boundCheck(Rectangle top, Rectangle bottom) {
        if (getBoundsInParent().intersects(top.getBoundsInParent())) {
            setLayoutY(top.getLayoutY() + top.getHeight() + 0.1);
        } else if (getBoundsInParent().intersects(bottom.getBoundsInParent())) {
            setLayoutY(bottom.getBoundsInParent().getMinY() - getHeight() - 0.1);
        }
    }

    public double getYDirection() {
        return yDirection;
    }

    public void setYDirection(double y) {
        yDirection = y;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }
    /**
     * Activates movement for left paddle.
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
     * Activates movement for right paddle.
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
     * Disables paddle movement.
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
