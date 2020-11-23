import domain.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    
    Player player;
    
    public PlayerTest() {
        player = new Player("abc");
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
    public void setNameDoesntAllowSemicolon() {
        Player semicolon = new Player("name;;player");
        assertTrue("nameplayer".equals(semicolon.getName()));
    }
    
    @Test
    public void nameCantBeTooLong() {
        Player toolong = new Player("thisnameistoolonggggggggabcdefg");
        assertTrue("thisnameistoolongggggggg".equals(toolong.getName()));
    }
    
    @Test
    public void noEmptyNames() {
        Player empty = new Player("");
        assertTrue("He Who Must Not Be Named".equals(empty.getName()));
    }
    
    @Test
    public void newPlayerHasZeroPoints() {
        assertTrue(player.getPoints() == 0);
    }
    
    @Test
    public void newPointIncreasesPoints() {
        player.newPoint();
        assertTrue(player.getPoints() == 1);
        
        for (int i = 0; i < 50; i++) {
            player.newPoint();
        }
        assertTrue(player.getPoints() == 51);
    }
    
    @Test
    public void resetPointsResetsToZero() {
        player.newPoint();
        player.resetPoints();
        assertTrue(player.getPoints() == 0);
    }
}
