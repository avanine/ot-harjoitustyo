package domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ball extends Rectangle {
    
    double initialSpeed = 2.0;
    double speedIncrease = 0.2;
    double maxSpeed = 10.0;
    
    double movementSpeed;
    double xDirection;
    double yDirection;
    
    public Ball() {
        setFill(Color.PINK);
        setLayoutX(425 - ((850 / 40) / 2));
        setLayoutY(425 - ((850 / 40) / 2));
        setWidth(850 / 40);
        setHeight(850 / 40);
        
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
