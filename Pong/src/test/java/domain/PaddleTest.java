package domain;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import pong.domain.Paddle;
import static pong.ui.PongUi.HEIGHT;
import static pong.ui.PongUi.LAYOUT;
import pong.ui.Wall;

public class PaddleTest {

    Paddle paddle;
    
    @Before
    public void setUp() {
        paddle = new Paddle();
    }

    @Test
    public void widthIsCorrect() {
        assertTrue(paddle.getWidth() == LAYOUT);
    }

    @Test
    public void heightIsCorrect() {
        assertTrue(paddle.getHeight() == LAYOUT * 5);
    }

    @Test
    public void initialYDirectionIsCorrect() {
        assertTrue(paddle.getYDirection() == 0.0);
    }

    @Test
    public void movementSpeedIsCorrect() {
        assertTrue(paddle.getMovementSpeed() == 7.5);
    }

    @Test
    public void setYDirectionWorks() {
        paddle.setYDirection(1.0);
        assertTrue(paddle.getYDirection() == 1.0);
    }

    @Test
    public void layoutYIsCorrect() {
        assertTrue(paddle.getLayoutY() == 300 - paddle.getHeight() / 2);
    }

    @Test
    public void paddleMoves() {
        paddle.setYDirection(1);
        paddle.movePaddle();
        assertTrue(paddle.getLayoutY() != 300 - paddle.getHeight() / 2);
    }

    @Test
    public void boundCheckWorks() {
        Wall wallBuilder = new Wall();
        paddle.setLayoutY(HEIGHT - LAYOUT + 1);
        paddle.boundCheck(wallBuilder.topWall(), wallBuilder.bottomWall());
        assertTrue(paddle.getLayoutY() != HEIGHT - LAYOUT + 1);
        paddle.setLayoutY(0);
        paddle.boundCheck(wallBuilder.topWall(), wallBuilder.bottomWall());
        assertTrue(paddle.getLayoutY() != 0);
    }
}
