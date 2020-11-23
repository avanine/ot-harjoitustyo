package domain;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Paddle extends Rectangle {
    
    double yDirection;
    double movementSpeed;
    
    public Paddle() {
        
        setWidth(850 / 40);
        setHeight((850 / 40) * 5);
        setFill(Color.PINK);
        setLayoutY(300 - getHeight() / 2);
        
        this.yDirection = 0.0;
        this.movementSpeed = 7.5;
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
