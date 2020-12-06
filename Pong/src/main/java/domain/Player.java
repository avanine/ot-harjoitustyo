package domain;

import javafx.scene.Group;

/**
 * Class for creating a player object.
 *
 */
public class Player {

    private String name;
    private int points;
    /**
     * Constructor to set the name and initial points.
     * 
     * @param name name
     */
    public Player(String name) {
        this.name = name;
        this.points = 0;
        setName();
    }

    /**
     * Method to set an acceptable name for player.
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

    public String getName() {
        return this.name;
    }

    public int getPoints() {
        return this.points;
    }

    /**
     * Resets player's points to zero.
     *
     * Used when players want a rematch.
     */
    public void resetPoints() {
        this.points = 0;
    }

    /**
     * Adds one point for the player.
     * @param scoreGroup group that displays score
     */
    public void newPoint(Group scoreGroup) {
        this.points++;

        if (points < 10) {
            setVisibleScore(scoreGroup);
        }
    }

    /**
     * Method for checking whether the player won.
     *
     * @return true if player won
     */
    public boolean win() {
        if (points >= 10) {
            return true;
        }
        return false;
    }
    /**
     * Sets visible score for player.
     * 
     * @param playerScore group containing rectangles
     */
    public void setVisibleScore(Group playerScore) {
        playerScore.getChildren().clear();
        playerScore.getChildren().add(new NumberGroup(points));
    }
}
