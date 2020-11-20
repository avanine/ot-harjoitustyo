
package domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Box extends Rectangle {
    
    public Box() {
        
    }
    
    public void createNameBox() {
        setFill(Color.BLACK);
        
    }
    
    public void createScoreBox() {
        setFill(Color.BLACK);
    }
    
    public void createTopBox() {
        setLayoutX(0);
        setLayoutY(0);
        setWidth(850);
        setHeight(850 / 40);
    }
    
    public void createBottomBox() {
        setLayoutX(0);
        setLayoutY(600 - 850 / 40);
        setWidth(850);
        setHeight(850 / 40);
    }
    
    public void createLeftBox() {
        setLayoutX(-850);
        setLayoutY(0);
        setWidth(850 - 850 / 40);
        setHeight(600);
    }
    
    public void createRightBox() {
        setLayoutX(850 + 850 / 40);
        setLayoutY(0);
        setWidth(850);
        setHeight(600);
    }
}
