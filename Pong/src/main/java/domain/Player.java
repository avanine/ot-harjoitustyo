package domain;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Player {

    String name;
    int points;

    public Player(String name) {
        this.name = name;
        this.points = 0;
        setName();
        
    }
    
    public void setName() {       
        if (this.name.isEmpty()) {
            this.name = "He Who Must Not Be Named";
        }
        if (this.name.length() > 24) {
            this.name = this.name.substring(0, 23);
        }
    }
    
    public Text getTextName() {
        Text nameText = new Text();
        nameText.setFill(Color.WHITE);
        nameText.setFont(Font.font("Impact", 40));
        return nameText;
    }
    
    public Text getTextScore() {
        Text scoreText = new Text();
        scoreText.setFill(Color.WHITE);
        scoreText.setFont(Font.font("Impact", 40));
        return scoreText;
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
