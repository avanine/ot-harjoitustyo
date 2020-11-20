
import domain.Ball;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BallTest {
    
    Ball ball = new Ball();
    
    public BallTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
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
}
