package domain;

public class Player {

    String name;
    int points;

    public Player(String name) {
        this.name = name;
        this.points = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getPoints() {
        return this.points;
    }

    public void resetPoints() {
        this.points = 0;
    }

    public void newPoint() {
        this.points++;
    }
}
