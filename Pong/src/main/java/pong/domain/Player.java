package pong.domain;

/**
 * Class for creating a player object.
 *
 */
public final class Player {

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
        checkName();
    }

    /**
     * Method to set an acceptable name for player.
     */
    public void checkName() {

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
     */
    public void newPoint() {
        this.points++;
    }

    /**
     * Method for checking whether the player won. If true, saves new win.
     *
     * @return true if player won
     */
    public boolean win() {
        if (points >= 10) {
            return true;
        }
        return false;
    }
}
