package domain;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import pong.domain.Player;

public class PlayerTest {

    Player player;

    public PlayerTest() {
        player = new Player("abc");
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
