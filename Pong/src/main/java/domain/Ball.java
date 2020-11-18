package domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ball extends Rectangle {
    
    double initialSpeed = 3.0;
    double speedIncrease = 0.5;
    double maxSpeed = 10.0;
    
    double movementSpeed;
    double xDirection;
    double yDirection;
    
    public Ball() {
        setFill(Color.PINK);
        setLayoutX((850/2)-((850/40)/2));
        setLayoutY((850/2)-((850/40)/2));
        setWidth(850/40);
        setHeight(850/40);
        
        this.movementSpeed = initialSpeed;
        this.xDirection = 1.0;
        this.yDirection = -1.0;
    }
    
    public double getMovementSpeed() {
        return movementSpeed;
    }
    
    public void resetMovementSpeed() {
        movementSpeed = initialSpeed;
    }
    
    public void increaseMovementSpeed() {
        movementSpeed += speedIncrease;
        movementSpeed = Math.min(movementSpeed, maxSpeed);
    }
    
    public double getXDirection() {
        return xDirection;
    }
    
    public double getYDirection() {
        return yDirection;
    }
    
    public void setXDirection(double direction) {
        xDirection = direction;
    }
    
    public void setYDirection(double direction) {
        yDirection = direction;
    }
}
