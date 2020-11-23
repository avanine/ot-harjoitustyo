package domain;

public class Player {

    String name;
    int points;

    public Player(String name) {
        this.name = name;
        this.points = 0;
        setName();

    }

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

    public void resetPoints() {
        this.points = 0;
    }

    public void newPoint() {
        this.points++;
    }
}
