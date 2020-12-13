package domain;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import pong.domain.Paddle;
import static pong.ui.PongUi.LAYOUT;

public class PaddleTest {

    Paddle paddle;

    public PaddleTest() {
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
    }
}
