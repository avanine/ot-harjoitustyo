import dao.PlayerScoreDao;
import java.io.File;
import java.io.FileWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.rules.TemporaryFolder;

public class PlayerScoreDaoTest {
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File scoreFile;
    PlayerScoreDao dao;
    
    @Before
    public void setUp() throws Exception {
        scoreFile = testFolder.newFile("testfile_scores.txt");
        
        try (FileWriter file = new FileWriter(scoreFile.getAbsolutePath())) {
            file.write("testplayer;5\n");
        }
        
        dao = new PlayerScoreDao(scoreFile.getAbsolutePath());
    }
    
    @After
    public void tearDown() {
        scoreFile.delete();
    }
/*
    @Test
    public void clearScoresWorks() {
        //dao.clearScores();
        assertTrue(dao.isEmpty());
    }*/
}
