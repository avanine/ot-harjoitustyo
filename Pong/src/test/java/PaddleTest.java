import domain.Paddle;
import domain.Player;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaddleTest {
    
    Paddle paddle;
    
    public PaddleTest() {
        paddle = new Paddle();
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
    public void widthIsCorrect() {
        assertTrue(paddle.getWidth() == 850 / 40);
    }
    
    @Test
    public void heightIsCorrect() {
        assertTrue(paddle.getHeight() == (850 / 40) * 5);
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
    public void activateMovementWorks() {

    }
}
