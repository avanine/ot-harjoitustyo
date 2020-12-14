package dao;

import pong.dao.PlayerScoreDao;
import java.io.FileInputStream;
import java.util.Properties;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class PlayerScoreDaoTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

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
}
