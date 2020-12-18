package domain;

import javafx.scene.shape.Rectangle;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import pong.domain.Ball;
import pong.domain.Paddle;
import static pong.ui.PongUi.WIDTH;
import pong.ui.Wall;

public class BallTest {

    Ball ball;
    
    @Before
    public void setUp() {
        ball = new Ball();
    }

    @Test
    public void initialSpeedIsCorrect() {
        assertTrue(ball.getMovementSpeed() == 2.0);
    }

    @Test
    public void increaseSpeedWorks() {
        ball.increaseMovementSpeed();
        assertTrue(ball.getMovementSpeed() > 2.0);
    }

    @Test
    public void resetSpeedChangesSpeedToInitialSpeed() {
        ball.increaseMovementSpeed();
        ball.resetMovementSpeed();
        assertTrue(ball.getMovementSpeed() == 2.0);
    }

    @Test
    public void ballMoves() {
        double initialLayOutX = ball.getLayoutX();
        ball.moveBall();
        assertTrue(initialLayOutX != ball.getLayoutX());
    }

    @Test
    public void ballDoesntGoThroughWalls() {
        Wall wall = new Wall();

        ball.setLayoutX(0);
        boolean layoutChanged = ball.wallBoundCheck(ball, wall.leftWall().getBoundsInParent());
        assertTrue(layoutChanged);

        ball.setLayoutX(850);
        layoutChanged = ball.wallBoundCheck(ball, wall.rightWall().getBoundsInParent());
        assertTrue(layoutChanged);

        ball.setLayoutY(0);
        layoutChanged = ball.wallBoundCheck(ball, wall.topWall().getBoundsInParent());
        assertTrue(layoutChanged);

        ball.setLayoutY(600);
        layoutChanged = ball.wallBoundCheck(ball, wall.bottomWall().getBoundsInParent());
        assertTrue(layoutChanged);
    }
    
    @Test
    public void ballDoesntGoThroughPaddles() {
        Paddle paddle = new Paddle();
        paddle.setLayoutX(WIDTH / 20);
        Paddle paddleTwo = new Paddle();
        
        ball.setLayoutX(paddle.getLayoutX());
        ball.setLayoutY(paddle.getLayoutY());
        ball.paddleBoundCheck(paddle.getBoundsInParent(), paddleTwo.getBoundsInParent());
        assertTrue(ball.getLayoutX() != paddle.getLayoutX());
        
        ball.setLayoutX(paddleTwo.getLayoutX());
        ball.setLayoutY(paddleTwo.getLayoutY());
        ball.paddleBoundCheck(paddle.getBoundsInParent(), paddleTwo.getBoundsInParent());
        assertTrue(ball.getLayoutX() != paddleTwo.getLayoutX());
    }
    
    @Test
    public void randomiseDirectionChangesYDirection() {
        for (int i = 1; i < 4; i++) {
            double oldYDirection = ball.getYDirection();
            ball.randomiseDirection(i);
            assertTrue(ball.getYDirection() != oldYDirection);
        }
    }
    
    @Test
    public void boundCheckWorks() {
        Paddle paddle = new Paddle();
        Wall wallBuilder = new Wall();
        Rectangle topWall = wallBuilder.topWall();
        Rectangle bottomWall = wallBuilder.bottomWall();
        
        ball.setLayoutX(paddle.getLayoutX());
        ball.setLayoutY(paddle.getLayoutY());
        assertTrue(ball.boundCheck(paddle.getBoundsInParent(), paddle.getBoundsInParent(), topWall, bottomWall.getBoundsInParent()));
        
        ball.setLayoutY(topWall.getLayoutY());
        assertTrue(ball.boundCheck(paddle.getBoundsInParent(), paddle.getBoundsInParent(), topWall, bottomWall.getBoundsInParent()));
    }
}
