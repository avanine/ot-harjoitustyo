package domain;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import pong.domain.Ball;

public class BallTest {

    Ball ball = new Ball();

    public BallTest() {
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
}
