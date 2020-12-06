package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Class that saves high scores.
 *
 */
public class PlayerScoreDao {
    
    private final String file;
    /**
     * Initializes file for saving scores.
     * 
     * @param file file
     * @throws Exception 
     */
    public PlayerScoreDao(String file) throws Exception {
        this.file = file;
    }
    /**
     * Clears all saved scores.
     */
    public void clearScores() {

        try (PrintWriter writer = new PrintWriter(new File(file))) {
            writer.print("");
            writer.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlayerScoreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Checks if file is empty.
     * 
     * @return true if file is empty
     */
    public boolean isEmpty() {
        if (file.length() == 0) {
            return true;
        }
        return false;
    }
}
