package pong.domain;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import static pong.ui.PongUi.LAYOUT;

/**
 *
 * Class for creating a ball object.
 */
public class Ball extends Rectangle {

    private final double initialSpeed = 2.0;
    private final double speedIncrease = 0.2;
    private final double maxSpeed = 10.0;

    private double movementSpeed;
    private double xDirection;
    private double yDirection;

    /**
     * Constructor that initializes ball's speed and direction.
     */
    public Ball() {
        setFill(Color.PINK);
        setLayoutX(425 - (LAYOUT / 2));
        setLayoutY(425 - (LAYOUT / 2));
        setWidth(LAYOUT);
        setHeight(LAYOUT);

        this.movementSpeed = initialSpeed;
        this.xDirection = 1.0;
        this.yDirection = -1.0;
    }

    /**
     * Creates movement for the ball.
     */
    public void moveBall() {
        setLayoutX(getLayoutX() + movementSpeed * xDirection);
        setLayoutY(getLayoutY() + movementSpeed * yDirection);
    }
    
    public void setColor(Color color) {
        setFill(color);
    }

    /**
     * Checks if the ball intersects any bounds.
     *
     * @param p1PaddleBounds left paddle bounds
     * @param p2PaddleBounds right paddle bounds
     * @param top top wall
     * @param bottomBounds bottom wall bounds
     * @return true if ball hits walls/paddles
     */
    public boolean boundCheck(Bounds p1PaddleBounds, Bounds p2PaddleBounds, Rectangle top, Bounds bottomBounds) {

        if (paddleBoundCheck(p1PaddleBounds, p2PaddleBounds)) {
            return true;
        } else if (wallBoundCheck(top, bottomBounds)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the ball hits walls and changes its direction accordingly.
     *
     * @param top top wall
     * @param bottomBounds bottom wall bounds
     * @return true if the ball intersects bounds
     */
    public boolean wallBoundCheck(Rectangle top, Bounds bottomBounds) {

        if (getBoundsInParent().intersects(top.getBoundsInParent())) {
            setLayoutY(top.getLayoutY() + top.getHeight() + 0.01);
            setYDirection(1.0);
            return true;

        } else if (getBoundsInParent().intersects(bottomBounds)) {
            setLayoutY(bottomBounds.getMinY() - getHeight() - 0.01);
            setYDirection(-1.0);
            return true;
        }
        return false;
    }

    /**
     * Checks if the ball hits paddles. If true, changes ball's direction and
     * speed.
     *
     * @param p1PaddleBounds left paddle bounds
     * @param p2PaddleBounds right paddle bounds
     * @return true if the ball intersects bounds
     */
    public boolean paddleBoundCheck(Bounds p1PaddleBounds, Bounds p2PaddleBounds) {

        if (getBoundsInParent().intersects(p1PaddleBounds)) {
            setLayoutX(p1PaddleBounds.getMaxX() + 0.1);
            setXDirection(1.0);
            increaseMovementSpeed();
            return true;

        } else if (getBoundsInParent().intersects(p2PaddleBounds)) {
            setLayoutX(p2PaddleBounds.getMinX() - getWidth() - 0.1);
            setXDirection(-1.0);
            increaseMovementSpeed();
            return true;
        }
        return false;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * Resets ball's movement speed.
     */
    public void resetMovementSpeed() {
        movementSpeed = initialSpeed;
    }

    /**
     * Increases ball's movement speed.
     */
    public void increaseMovementSpeed() {
        movementSpeed += speedIncrease;
        movementSpeed = Math.min(movementSpeed, maxSpeed);
    }

    public void setXDirection(double direction) {
        xDirection = direction;
    }

    public void setYDirection(double direction) {
        yDirection = direction;
    }

    /**
     * Randomises ball's direction.
     *
     * @param randomValue random value between 0-3
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
