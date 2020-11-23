package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class PlayerScoreDao {
    
    private HashMap<String, Integer> scores;
    
    public PlayerScoreDao() {
        scores = new HashMap<>();
    }
    
    public int getScore(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("scores.txt")))) {
            // find line with given name
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public void saveNewWin(String name) {
        
    }
}
