package dao;

import pong.dao.PlayerScoreDao;
import java.io.FileInputStream;
import java.util.Properties;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class PlayerScoreDaoTest {

    PlayerScoreDao dao;

    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        dao = new PlayerScoreDao(properties.getProperty("test"));

        dao.addNewWin("testPlayer");
    }

    @After
    public void tearDown() {
        dao.clearScores();
    }

    @Test
    public void getTopScoresWorks() throws Exception {
        assertEquals(dao.getTopScores(1), "testPlayer    -   1\n");
    }

    @Test
    public void chosenAmountOfPlayersReturnsRightAmount() {
        for (int i = 0; i <= 3; i++) {
            dao.addNewWin("testPlayer");
        }
        dao.addNewWin("abc");
        dao.addNewWin("abc");
        dao.addNewWin("asdfg");
        assertEquals(dao.getTopScores(2), "testPlayer    -   5\nabc    -   2\n");
    }

    @Test
    public void clearScoresEmptiesFile() {
        dao.clearScores();
        assertEquals(dao.getTopScores(5), "");
    }
}
