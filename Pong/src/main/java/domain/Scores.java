
package domain;

public class Scores {
    
    private int playerOneScore;
    private int playerTwoScore;
    
    public Scores() {
        this.playerOneScore = 0;
        this.playerTwoScore = 0;
    }
    
    public void resetScore() {
        playerOneScore = 0;
        playerTwoScore = 0;
    }
    
    public int getPlayerOneScore() {
        return playerOneScore;
    }
    
    public int getPlayerTwoScore() {
        return playerTwoScore;
    }
    
    public int playerOneGetsPoint() {
        playerOneScore++;
        return playerOneScore;
    }
    
    public int playerTwoGetsPoint() {
        playerTwoScore++;
        return playerTwoScore;
    }
}
