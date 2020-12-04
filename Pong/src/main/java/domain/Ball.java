package domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static ui.PongUi.LAYOUT;
/**
 * 
 * Class for creating ball object
 */
public class Ball extends Rectangle {
    
    private final double INITIAL_SPEED = 2.0;
    private final double SPEED_INCREASE = 0.2;
    private final double MAX_SPEED = 10.0;
    
    private double movementSpeed;
    private double xDirection;
    private double yDirection;
    
    public Ball() {
        setFill(Color.PINK);
        setLayoutX(425 - (LAYOUT / 2));
        setLayoutY(425 - (LAYOUT / 2));
        setWidth(LAYOUT);
        setHeight(LAYOUT);
        
        this.movementSpeed = INITIAL_SPEED;
        this.xDirection = 1.0;
        this.yDirection = -1.0;
    }
    /**
     * 
     * @return ball's movement speed
     */
    public double getMovementSpeed() {
        return movementSpeed;
    }
    /**
     * resets ball's movement speed
     */
    public void resetMovementSpeed() {
        movementSpeed = INITIAL_SPEED;
    }
    /**
     * increases ball's movement speed
     */
    public void increaseMovementSpeed() {
        movementSpeed += SPEED_INCREASE;
        movementSpeed = Math.min(movementSpeed, MAX_SPEED);
    }
    /**
     * 
     * @return ball's x-direction
     */
    public double getXDirection() {
        return xDirection;
    }
    /**
     * 
     * @return ball's y-direction
     */
    public double getYDirection() {
        return yDirection;
    }
    /**
     * method for setting ball's x-direction
     * used when ball hits paddles
     * 
     * @param direction new x-direction
     */
    public void setXDirection(double direction) {
        xDirection = direction;
    }
    /**
     * method for setting ball's y-direction
     * used when ball hits walls
     * 
     * @param direction new y-direction
     */
    public void setYDirection(double direction) {
        yDirection = direction;
    }
    /**
     * method for randomising ball's direction
     * used when ball hits paddles/walls, and after ball returns to the center
     * 
     * @param randomValue random value
     */
    public void randomiseDirection(int randomValue) {
        switch (randomValue) {
            case 0:
                setXDirection(-1.0);
                setYDirection(-1.0);
                break;
            case 1:
                setXDirection(-1.0);
                setYDirection(1.0);
                break;
            case 2:
                setXDirection(1.0);
                setYDirection(-1.0);
                break;
            case 3:
                setXDirection(1.0);
                setYDirection(1.0);
                break;
        }
    }
}
