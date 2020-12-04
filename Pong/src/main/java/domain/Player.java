package domain;
/**
 * Class for creating player object
 *
 */
public class Player {

    private String name;
    private int points;

    public Player(String name) {
        this.name = name;
        this.points = 0;
        setName();

    }
    /**
     * method to set acceptable name for player
     */
    public void setName() {
        
        if (this.name.contains(";")) {
            this.name = this.name.replaceAll(";", "");
        }
        if (this.name.isEmpty()) {
            this.name = "He Who Must Not Be Named";
        }
        if (this.name.length() > 24) {
            this.name = this.name.substring(0, 24);
        }        
    }
    /**
     * 
     * @return player's name
     */
    public String getName() {
        return this.name;
    }
    /**
     * 
     * @return player's points
     */
    public int getPoints() {
        return this.points;
    }
    /**
     * resets player's points to zero
     * 
     * used when players want a rematch
     */
    public void resetPoints() {
        this.points = 0;
    }
    /**
     * adds one point for the player
     */
    public void newPoint() {
        this.points++;
    }
}
